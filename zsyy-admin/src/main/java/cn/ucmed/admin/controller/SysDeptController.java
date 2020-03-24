package cn.ucmed.admin.controller;

import cn.ucmed.common.db.system.entity.SysDept;
import cn.ucmed.common.db.system.entity.SysUser;
import cn.ucmed.common.db.system.entity.Tree;
import cn.ucmed.common.db.system.service.ISysDeptService;
import cn.ucmed.common.db.system.service.ISysUserService;
import cn.ucmed.common.utils.result.Result;
import cn.ucmed.common.utils.result.ResultUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 部门管理
 */
@Controller
@RequestMapping("/admin/dept")
public class SysDeptController extends SysBaseController {

    private String prefix = "admin/dept";
    @Autowired
    private ISysDeptService sysDeptService;
    @Autowired
    private ISysUserService userService;

    @GetMapping()
    @RequiresPermissions("admin:dept:dept")
    public String dept() {
        return prefix + "/dept";
    }

    @ApiOperation(value = "获取部门列表", notes = "")
    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("admin:dept:dept")
    public List<SysDept> list() {
        String hospitalId = getHospitalId();
        return sysDeptService.list(new QueryWrapper<SysDept>()
                .eq("hospital_id", hospitalId)
                .orderByAsc("order_num"));
    }

    @GetMapping("/add/{pId}")
    @RequiresPermissions("admin:dept:add")
    public String add(@PathVariable("pId") Long pId, Model model) {
        model.addAttribute("pId", pId);
        if (pId == 0) {
            model.addAttribute("pName", "总部门");
        } else {
            model.addAttribute("pName", sysDeptService.getById(pId).getName());
        }
        return prefix + "/add";
    }

    @GetMapping("/edit/{deptId}")
    @RequiresPermissions("admin:dept:edit")
    public String edit(@PathVariable("deptId") Long deptId, Model model) {
        SysDept sysDept = sysDeptService.getById(deptId);
        model.addAttribute("sysDept", sysDept);
        if (sysDept.getParentId() == 0) {
            model.addAttribute("parentDeptName", "总部门");
        } else {
            SysDept parDept = sysDeptService.getById(sysDept.getParentId());
            model.addAttribute("parentDeptName", parDept.getName());
        }
        return prefix + "/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("admin:dept:add")
    public Result save(SysDept sysDept) {
        sysDept.setHospitalId(getHospitalId());
        if (sysDeptService.save(sysDept)) {
            return ResultUtils.success();
        }
        return ResultUtils.error("保存数据失败");
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("admin:dept:edit")
    public Result update(SysDept sysDept) {
        if (sysDeptService.updateById(sysDept)) {
            return ResultUtils.success();
        }
        return ResultUtils.error("修改数据失败");
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("admin:dept:remove")
    public Result remove(Long deptId) {
        if (sysDeptService.count(new QueryWrapper<SysDept>()
                .eq("parent_id", deptId)) > 0) {
            return ResultUtils.error("包含下级部门,不允许修改");
        }
        if (userService.count(new QueryWrapper<SysUser>()
                .eq("status", deptId)) > 0) {
            return ResultUtils.error("部门包含用户,不允许修改");
        }
        if (!sysDeptService.removeById(deptId)) {
            return ResultUtils.error("删除部门失败");
        }
        return ResultUtils.success();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("admin:dept:batchRemove")
    public Result remove(@RequestParam("ids[]") Long[] deptIds) {
        if (sysDeptService.removeByIds(Arrays.asList(deptIds))) {
            return ResultUtils.success();
        }
        return ResultUtils.error("删除部门失败");
    }

    @GetMapping("/tree")
    @ResponseBody
    public Tree<SysDept> tree() {
        return sysDeptService.getTree(getHospitalId());
    }

    @GetMapping("/treeView")
    public String treeView() {
        return prefix + "/deptTree";
    }
}

