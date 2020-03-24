package cn.ucmed.common.db.system.service.impl;

import cn.ucmed.common.db.system.entity.*;
import cn.ucmed.common.db.system.mapper.SysDeptMapper;
import cn.ucmed.common.db.system.mapper.SysUserMapper;
import cn.ucmed.common.db.system.mapper.SysUserRoleMapper;
import cn.ucmed.common.db.system.service.ISysDeptService;
import cn.ucmed.common.db.system.service.ISysUserService;
import cn.ucmed.common.utils.BuildTree;
import cn.ucmed.common.utils.MD5Utils;
import cn.ucmed.common.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @auther 郄彦腾
 * @create 2019-05-09 20:13:58
 * @describe 服务实现类
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
    SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    SysDeptMapper sysDeptMapper;
    @Autowired
    ISysDeptService sysDeptService;

    @Override
    public SysUser get(Long id) {
        List<Long> roleIds = new ArrayList<>();
        List<SysUserRole> list = sysUserRoleMapper.selectList(new QueryWrapper<SysUserRole>().eq("user_id",id));
        for(SysUserRole sysUserRole : list) {
            roleIds.add(sysUserRole.getRoleId());
        }
        SysUser user = sysUserMapper.selectById(id);
        if ("0".equals(user.getIsSuperman()) && "0".equals(user.getIsProject())){
            user.setDeptName(sysDeptMapper.selectById(user.getDeptId()).getName());
        }
        user.setRoleIds(roleIds);
        return user;
    }

    @Transactional
    @Override
    public int saveData(SysUser user) {
        int count = sysUserMapper.insert(user);
        Long userId = user.getUserId();
        List<Long> roles = user.getRoleIds();
        sysUserRoleMapper.delete(new QueryWrapper<SysUserRole>().eq("user_id",userId));
        List<SysUserRole> list = new ArrayList<>();
        for (Long roleId : roles) {
            SysUserRole ur = new SysUserRole();
            ur.setUserId(userId);
            ur.setRoleId(roleId);
            list.add(ur);
        }
        if (list.size() > 0) {
            for(SysUserRole sysUserRole : list){
                sysUserRoleMapper.insert(sysUserRole);
            }
        }
        return count;
    }

    @Override
    public int update(SysUser user) {
        int r = sysUserMapper.updateById(user);
        Long userId = user.getUserId();
        List<Long> roles = user.getRoleIds();
        sysUserRoleMapper.delete(new QueryWrapper<SysUserRole>().eq("user_id",userId));
        List<SysUserRole> list = new ArrayList<>();
        for (Long roleId : roles) {
            SysUserRole ur = new SysUserRole();
            ur.setUserId(userId);
            ur.setRoleId(roleId);
            list.add(ur);
        }
        if (list.size() > 0) {
            for(SysUserRole sysUserRole : list){
                sysUserRoleMapper.insert(sysUserRole);
            }
        }
        return r;
    }

    @Override
    public int remove(Long userId) {
        sysUserRoleMapper.delete(new QueryWrapper<SysUserRole>().eq("user_id",userId));
        return sysUserMapper.deleteById(userId);
    }

    @Override
    public Set<String> listRoles(Long userId) {
        return null;
    }

    @Override
    public int resetPwd(SysUserVO userVO, SysUser userDO) throws Exception {
        if (Objects.equals(userVO.getUserDO().getUserId(), userDO.getUserId())) {
            if (Objects.equals(MD5Utils.encrypt(userDO.getUsername(), userVO.getPwdOld()), userDO.getPassword())) {
                userDO.setPassword(MD5Utils.encrypt(userDO.getUsername(), userVO.getPwdNew()));
                return sysUserMapper.updateById(userDO);
            } else {
                throw new Exception("输入的旧密码有误！");
            }
        } else {
            throw new Exception("你修改的不是你登录的账号！");
        }
    }

    @Override
    public int adminResetPwd(String userId,String newPwd) throws Exception {
        SysUser userDO = sysUserMapper.selectById(userId);
        if ("admin".equals(userDO.getUsername())) {
            throw new Exception("超级管理员的账号不允许直接重置！");
        }
        userDO.setPassword(MD5Utils.encrypt(userDO.getUsername(), newPwd));
        return sysUserMapper.updateById(userDO);
    }


    @Override
    public Tree<SysDept> getTree() {
        List<Tree<SysDept>> trees = new ArrayList<Tree<SysDept>>();
        List<SysDept> depts = sysDeptMapper.list(new HashMap<String, Object>(16));
        Long[] pDepts = sysDeptMapper.listParentDept();
        Long[] uDepts = sysUserMapper.listAllDept();
        Long[] allDepts = (Long[]) ArrayUtils.addAll(pDepts, uDepts);
        for (SysDept dept : depts) {
            if (!ArrayUtils.contains(allDepts, dept.getDeptId())) {
                continue;
            }
            Tree<SysDept> tree = new Tree<SysDept>();
            tree.setId(dept.getDeptId().toString());
            tree.setParentId(dept.getParentId().toString());
            tree.setText(dept.getName());
            Map<String, Object> state = new HashMap<>(16);
            state.put("opened", true);
            state.put("mType", "dept");
            tree.setState(state);
            trees.add(tree);
        }
        List<SysUser> users = sysUserMapper.selectList(new QueryWrapper<>());
        for (SysUser user : users) {
            Tree<SysDept> tree = new Tree<SysDept>();
            tree.setId(user.getUserId().toString());
            tree.setParentId(user.getDeptId().toString());
            tree.setText(user.getName());
            Map<String, Object> state = new HashMap<>(16);
            state.put("opened", true);
            state.put("mType", "user");
            tree.setState(state);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<SysDept> t = BuildTree.build(trees);
        return t;
    }

    @Override
    public int updatePersonal(SysUser userDO) {
        return sysUserMapper.updateById(userDO);
    }

    @Override
    public boolean isSuperman(Long userId) {
        SysUser user = sysUserMapper.selectById(userId);
        if ("1".equals(user.getIsSuperman())){
            return true;
        }
        return false;
    }
}
