package cn.ucmed.admin.controller;

import cn.ucmed.common.db.system.entity.SysMenu;
import cn.ucmed.common.db.system.entity.Tree;
import cn.ucmed.common.db.system.service.ISysMenuService;
import cn.ucmed.common.utils.result.Result;
import cn.ucmed.common.utils.result.ResultUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 菜单管理
 */
@Slf4j
@Controller
@RequestMapping("/admin/sys-menu")
public class SysMenuController extends SysBaseController{
    String prefix = "admin/menu";

    @Autowired
    private ISysMenuService menuService;

    @RequiresPermissions("sys:menu:menu")
    @GetMapping()
    public String menu() {
        return prefix+"/menu";
    }

    @RequiresPermissions("sys:menu:menu")
    @RequestMapping("/list")
    @ResponseBody
    public List<SysMenu> list(@RequestParam Map<String, Object> params) {
        log.info("list==>"+params);
        return menuService.list(new QueryWrapper<SysMenu>()
                .orderByAsc("order_num"));
    }

    @RequiresPermissions("sys:menu:add")
    @GetMapping("/add/{pId}")
    public String add(Model model, @PathVariable("pId") Long pId) {
        addPId(model, pId);
        return prefix + "/add";
    }

    @RequiresPermissions("sys:menu:edit")
    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        SysMenu mdo = menuService.getById(id);
        Long pId = mdo.getParentId();
        addPId(model, pId);
        model.addAttribute("menu", mdo);
        return prefix+"/edit";
    }

    private void addPId(Model model, @PathVariable("pId") Long pId) {
        model.addAttribute("pId", pId);
        if (pId == 0) {
            model.addAttribute("pName", "根目录");
            model.addAttribute("type", -1);
        } else {
            SysMenu menu  = menuService.getById(pId);
            model.addAttribute("pName", menu.getName());
            model.addAttribute("type", menu.getType());
        }
    }

    @RequiresPermissions("sys:menu:add")
    @PostMapping("/save")
    @ResponseBody
    public Result save(SysMenu menu) {
        if (menuService.save(menu)) {
            return ResultUtils.success();
        } else {
            return ResultUtils.error( "保存失败");
        }
    }

    @RequiresPermissions("sys:menu:edit")
    @PostMapping("/update")
    @ResponseBody
    public Result update(@RequestBody SysMenu menu) {
        if (menuService.updateById(menu)) {
            return ResultUtils.success();
        } else {
            return ResultUtils.error( "更新失败");
        }
    }

    @RequiresPermissions("sys:menu:remove")
    @PostMapping("/remove")
    @ResponseBody
    public Result remove(Long id) {
        if (menuService.removeById(id)) {
            return ResultUtils.success();
        } else {
            return ResultUtils.error( "删除失败");
        }
    }

    @GetMapping("/tree")
    @ResponseBody
    public Tree<SysMenu> tree() {
        Tree<SysMenu>  tree = menuService.getTree();
        return tree;
    }

    @GetMapping("/tree/{roleId}")
    @ResponseBody
    public Tree<SysMenu> tree(@PathVariable("roleId") Long roleId) {
        return menuService.getTree(roleId);
    }

}

