package cn.ucmed.common.db.system.mapper;

import cn.ucmed.common.db.system.entity.SysUserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * @auther 郄彦腾
 * @create 2019-01-24 13:23:18
 * @describe 用户与角色对应关系mapper类
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    SysUserRole get(Long id);

    List<SysUserRole> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int saveData(SysUserRole userRole);

    int update(SysUserRole userRole);

    int remove(Long id);

    int batchRemove(Long[] ids);

    List<Long> listRoleId(Long userId);

    int removeByUserId(Long userId);

    int removeByRoleId(Long roleId);

    int batchSave(List<SysUserRole> list);

    int batchRemoveByUserId(Long[] ids);
}
