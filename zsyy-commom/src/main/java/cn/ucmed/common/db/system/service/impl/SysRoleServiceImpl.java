package cn.ucmed.common.db.system.service.impl;

import cn.ucmed.common.db.system.entity.SysRole;
import cn.ucmed.common.db.system.entity.SysRoleMenu;
import cn.ucmed.common.db.system.entity.SysUserRole;
import cn.ucmed.common.db.system.mapper.SysRoleMapper;
import cn.ucmed.common.db.system.mapper.SysRoleMenuMapper;
import cn.ucmed.common.db.system.mapper.SysUserMapper;
import cn.ucmed.common.db.system.mapper.SysUserRoleMapper;
import cn.ucmed.common.db.system.service.ISysRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 角色服务实现类
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Autowired
    SysRoleMapper sysRoleMapper;
    @Autowired
    SysRoleMenuMapper sysRoleMenuMapper;
    @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
    SysUserRoleMapper userRoleMapper;

    @Override
    public List<SysRole> list(Long userId,String hospitalId) {
        List<Long> rolesIds = new ArrayList<>();
        List<SysUserRole> list = userRoleMapper.selectList(new QueryWrapper<SysUserRole>().eq("user_id",userId));
        for(SysUserRole sysUserRole : list) {
            rolesIds.add(sysUserRole.getRoleId());
        }
        List<SysRole> roles = sysRoleMapper.selectList(new QueryWrapper<SysRole>()
                .eq("hospital_id",hospitalId));
        for (SysRole role : roles) {
            role.setRoleSign("false");
            for (Long roleId : rolesIds) {
                if (Objects.equals(role.getRoleId(), roleId)) {
                    role.setRoleSign("true");
                    break;
                }
            }
        }
        return roles;
    }

    @Transactional
    @Override
    public int saveData(SysRole role) {
        int count = sysRoleMapper.insert(role);
        List<Long> menuIds = role.getMenuIds();
        Long roleId = role.getRoleId();
        List<SysRoleMenu> rms = new ArrayList<>();
        for (Long menuId : menuIds) {
            SysRoleMenu rmDo = new SysRoleMenu();
            rmDo.setRoleId(roleId);
            rmDo.setMenuId(menuId);
            rms.add(rmDo);
        }
        sysRoleMenuMapper.delete(new QueryWrapper<SysRoleMenu>().eq("role_id",roleId));
        if (rms.size() > 0) {
            for(SysRoleMenu sysRoleMenu : rms) {
                sysRoleMenuMapper.insert(sysRoleMenu);
            }
        }
        return count;
    }

    @Transactional
    @Override
    public int remove(Long id) {
        int count = sysRoleMapper.deleteById(id);
        sysRoleMenuMapper.delete(new QueryWrapper<SysRoleMenu>().eq("role_id",id));
        return count;
    }

    @Override
    public int update(SysRole role) {
        int r = sysRoleMapper.updateById(role);
        List<Long> menuIds = role.getMenuIds();
        Long roleId = role.getRoleId();
        sysRoleMenuMapper.delete(new QueryWrapper<SysRoleMenu>().eq("role_id",roleId));
        List<SysRoleMenu> rms = new ArrayList<>();
        for (Long menuId : menuIds) {
            SysRoleMenu rmDo = new SysRoleMenu();
            rmDo.setRoleId(roleId);
            rmDo.setMenuId(menuId);
            rms.add(rmDo);
        }
        if (rms.size() > 0) {
            for(SysRoleMenu sysRoleMenu : rms) {
                sysRoleMenuMapper.insert(sysRoleMenu);
            }
        }
        return r;
    }

    @Transactional
    @Override
    public boolean batchremove(Long[] ids) {
        for(Long id: ids) {
            sysRoleMapper.deleteById(id);
            sysRoleMenuMapper.delete(new QueryWrapper<SysRoleMenu>().eq("role_id",id));
        }
        return true;
    }
}
