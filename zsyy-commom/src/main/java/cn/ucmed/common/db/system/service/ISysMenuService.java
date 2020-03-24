package cn.ucmed.common.db.system.service;

import cn.ucmed.common.db.system.entity.SysMenu;
import cn.ucmed.common.db.system.entity.Tree;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @auther 郄彦腾
 * @create 2019-01-24 13:21:17
 * @describe 菜单管理服务类
 */
public interface ISysMenuService extends IService<SysMenu> {

    Tree<SysMenu> getTree();

    Tree<SysMenu> getTree(Long id);


    Tree<SysMenu> getSysMenuTree(Long id, String hospitalId);

    Set<String> listPerms(Long userId, String hospitalId);

    Set<String> superPerms();

    List<Tree<SysMenu>> listMenuTree(Long id, String hospitalId);

    List<Tree<SysMenu>> superMenuTree();

}
