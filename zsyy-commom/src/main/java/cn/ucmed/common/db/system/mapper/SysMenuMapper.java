package cn.ucmed.common.db.system.mapper;

import cn.ucmed.common.db.system.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @auther 郄彦腾
 * @create 2019-01-24 13:21:17
 * @describe 菜单管理mapper类
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    SysMenu get(Long menuId);

    List<SysMenu> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int saveData(SysMenu menu);

    int update(SysMenu menu);

    int remove(Long menuId);

    int batchRemove(Long[] menuIds);

    List<String> listUserPerms(@Param("id") Long id, @Param("hospitalId") String hospitalId);

    List<SysMenu> listMenuByUserId(@Param("id") Long id, @Param("hospitalId") String hospitalId);
}
