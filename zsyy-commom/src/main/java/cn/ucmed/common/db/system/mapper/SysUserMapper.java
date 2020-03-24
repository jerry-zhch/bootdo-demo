package cn.ucmed.common.db.system.mapper;

import cn.ucmed.common.db.system.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @auther 郄彦腾
 * @create 2019-05-09 20:13:58
 * @describe mapper类
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    @Select({
            "select DISTINCT dept_id from sys_user"
    })
    Long[] listAllDept();
}
