package cn.ucmed.common.db.system.service;

import cn.ucmed.common.db.system.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @auther 郄彦腾
 * @create 2019-01-24 13:21:42
 * @describe 角色服务类
 */
public interface ISysRoleService extends IService<SysRole> {

    int saveData(SysRole role);

    int update(SysRole role);

    int remove(Long id);

    List<SysRole> list(Long userId, String hospitalId);

    boolean batchremove(Long[] ids);
}
