package cn.ucmed.common.db.system.service.impl;

import cn.ucmed.common.db.system.entity.SysMenu;
import cn.ucmed.common.db.system.entity.SysRoleMenu;
import cn.ucmed.common.db.system.entity.Tree;
import cn.ucmed.common.db.system.mapper.SysMenuMapper;
import cn.ucmed.common.db.system.mapper.SysRoleMenuMapper;
import cn.ucmed.common.db.system.service.ISysMenuService;
import cn.ucmed.common.utils.BuildTree;
import cn.ucmed.common.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @auther 郄彦腾
 * @create 2019-01-24 13:21:17
 * @describe 菜单管理服务实现类
 */
@Service
@Transactional(readOnly = true,rollbackFor = Exception.class)
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    @Autowired
    SysMenuMapper sysMenuMapper;
    @Autowired
    SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public Tree<SysMenu> getTree() {
        List<Tree<SysMenu>> trees = new ArrayList<Tree<SysMenu>>();
        List<SysMenu> menus = sysMenuMapper.selectList(new QueryWrapper<>());
        for (SysMenu sysSysMenu : menus) {
            Tree<SysMenu> tree = new Tree<SysMenu>();
            tree.setId(sysSysMenu.getMenuId().toString());
            tree.setParentId(sysSysMenu.getParentId().toString());
            tree.setText(sysSysMenu.getName());
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<SysMenu> t = BuildTree.build(trees);
        return t;
    }

    @Override
    public Tree<SysMenu> getTree(Long id) {
        // 根据roleId查询权限
        List<SysMenu> menus = sysMenuMapper.selectList(new QueryWrapper<>());
        List<SysRoleMenu> sysRoleMenus = sysRoleMenuMapper.selectList(new QueryWrapper<SysRoleMenu>().eq("role_id",id));
        List<Long> menuIds = new ArrayList<>();
        for(SysRoleMenu sysRoleMenu : sysRoleMenus) {
            menuIds.add(sysRoleMenu.getMenuId());
        }

        List<Long> temp = menuIds;
        for (SysMenu menu : menus) {
            if (temp.contains(menu.getParentId())) {
                menuIds.remove(menu.getParentId());
            }
        }
        List<Tree<SysMenu>> trees = new ArrayList<Tree<SysMenu>>();
        List<SysMenu> sysMenus = sysMenuMapper.selectList(new QueryWrapper<>());
        for (SysMenu sysSysMenu : sysMenus) {
            Tree<SysMenu> tree = new Tree<SysMenu>();
            tree.setId(sysSysMenu.getMenuId().toString());
            tree.setParentId(sysSysMenu.getParentId().toString());
            tree.setText(sysSysMenu.getName());
            Map<String, Object> state = new HashMap<>(16);
            Long menuId = sysSysMenu.getMenuId();
            if (menuIds.contains(menuId)) {
                state.put("selected", true);
            } else {
                state.put("selected", false);
            }
            tree.setState(state);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<SysMenu> t = BuildTree.build(trees);
        return t;
    }

    /**
     * @param
     * @return 树形菜单
     */
    @Cacheable
    @Override
    public Tree<SysMenu> getSysMenuTree(Long id,String hospitalId) {
        List<Tree<SysMenu>> trees = new ArrayList<Tree<SysMenu>>();
        List<SysMenu> menus = sysMenuMapper.listMenuByUserId(id,hospitalId);
        for (SysMenu sysSysMenu : menus) {
            Tree<SysMenu> tree = new Tree<SysMenu>();
            tree.setId(sysSysMenu.getMenuId().toString());
            tree.setParentId(sysSysMenu.getParentId().toString());
            tree.setText(sysSysMenu.getName());
            Map<String, Object> attributes = new HashMap<>(16);
            attributes.put("url", sysSysMenu.getUrl());
            attributes.put("icon", sysSysMenu.getIcon());
            tree.setAttributes(attributes);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<SysMenu> t = BuildTree.build(trees);
        return t;
    }

    @Override
    public Set<String> listPerms(Long userId,String hospitalId) {
        List<String> perms = sysMenuMapper.listUserPerms(userId,hospitalId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms) {
            if (StringUtils.isNotBlank(perm)) {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    @Override
    public Set<String> superPerms() {
        List<SysMenu> perms = sysMenuMapper.selectList(new QueryWrapper<SysMenu>().orderByAsc("order_num"));
        Set<String> permsSet = new HashSet<>();
        for (SysMenu sysMenu : perms) {
            if (StringUtils.isNotBlank(sysMenu.getPerms())) {
                permsSet.addAll(Arrays.asList(sysMenu.getPerms().trim().split(",")));
            }
        }
        return permsSet;
    }

    @Override
    public List<Tree<SysMenu>> listMenuTree(Long id,String hospitalId) {
        List<Tree<SysMenu>> trees = new ArrayList<Tree<SysMenu>>();
        List<SysMenu> menus = sysMenuMapper.listMenuByUserId(id,hospitalId);
        for (SysMenu sysSysMenu : menus) {
            Tree<SysMenu> tree = new Tree<SysMenu>();
            tree.setId(sysSysMenu.getMenuId().toString());
            tree.setParentId(sysSysMenu.getParentId().toString());
            tree.setText(sysSysMenu.getName());
            Map<String, Object> attributes = new HashMap<>(16);
            attributes.put("url", sysSysMenu.getUrl());
            attributes.put("icon", sysSysMenu.getIcon());
            tree.setAttributes(attributes);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        List<Tree<SysMenu>> list = BuildTree.buildList(trees, "0");
        return list;
    }

    @Override
    public List<Tree<SysMenu>> superMenuTree() {
        List<Tree<SysMenu>> trees = new ArrayList<Tree<SysMenu>>();
        List<SysMenu> menus = list(new QueryWrapper<SysMenu>().orderByAsc("order_num"));
        for (SysMenu sysSysMenu : menus) {
            Tree<SysMenu> tree = new Tree<SysMenu>();
            tree.setId(sysSysMenu.getMenuId().toString());
            tree.setParentId(sysSysMenu.getParentId().toString());
            tree.setText(sysSysMenu.getName());
            Map<String, Object> attributes = new HashMap<>(16);
            attributes.put("url", sysSysMenu.getUrl());
            attributes.put("icon", sysSysMenu.getIcon());
            tree.setAttributes(attributes);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        List<Tree<SysMenu>> list = BuildTree.buildList(trees, "0");
        return list;
    }
}
