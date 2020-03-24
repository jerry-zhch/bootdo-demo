package cn.ucmed.common.db.system.service;

import cn.ucmed.common.db.system.entity.SysDept;
import cn.ucmed.common.db.system.entity.SysUser;
import cn.ucmed.common.db.system.entity.SysUserVO;
import cn.ucmed.common.db.system.entity.Tree;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @auther 郄彦腾
 * @create 2019-05-09 20:13:58
 * @describe 服务类
 */
public interface ISysUserService extends IService<SysUser> {

    SysUser get(Long id);

    int saveData(SysUser user);

    int update(SysUser user);

    int remove(Long userId);



    Set<String> listRoles(Long userId);

    int resetPwd(SysUserVO userVO, SysUser sysUser) throws Exception;

    int adminResetPwd(String userId,String newPwd) throws Exception;

    Tree<SysDept> getTree();

    /**
     * 更新个人信息
     * @param SysUser
     * @return
     */
    int updatePersonal(SysUser SysUser);

    boolean isSuperman(Long userId);

}
