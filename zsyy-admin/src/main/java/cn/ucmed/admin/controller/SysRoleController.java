package cn.ucmed.admin.controller;

import cn.ucmed.common.db.hospital.entity.SysHospital;
import cn.ucmed.common.db.hospital.service.ISysHospitalService;
import cn.ucmed.common.db.system.entity.SysRole;
import cn.ucmed.common.db.system.service.ISysRoleService;
import cn.ucmed.common.utils.PageUtils;
import cn.ucmed.common.utils.result.Result;
import cn.ucmed.common.utils.result.ResultUtils;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 角色管理
 */
@Controller
@RequestMapping("/admin/sys-role")
public class  SysRoleController extends SysBaseController{

    private String prefix = "admin/role";

    @Autowired
    private ISysRoleService iSysRoleService;

    @Autowired
    private ISysHospitalService hospitalService;

    @RequiresPermissions("sys:role:role")
    @GetMapping()
    public String role() {
        return prefix + "/role";
    }

    @RequiresPermissions("sys:role:role")
    @PostMapping("/list")
    @ResponseBody()
    public PageUtils list(@RequestBody JSONObject data) {
        IPage page = PageUtils.getIPage(data);
        String hospitalId = getHospitalId();
        IPage<SysRole> result = iSysRoleService.page(page,new QueryWrapper<SysRole>()
                .eq("hospital_id",hospitalId)
                .orderByDesc("gmt_modified"));
        return new PageUtils(result.getRecords(),result.getTotal());
    }

    @RequiresPermissions("sys:role:add")
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    @RequiresPermissions("sys:role:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        SysRole roleDO = iSysRoleService.getById(id);
        List<SysHospital> hospitals = hospitalService.list(new QueryWrapper<SysHospital>());
        model.addAttribute("hospitals", hospitals);
        model.addAttribute("role", roleDO);
        return prefix + "/edit";
    }

    @RequiresPermissions("sys:role:add")
    @PostMapping("/save")
    @ResponseBody()
    public Result save(@RequestBody SysRole role) {
        role.setHospitalId(getHospitalId());
        role.setHospitalName(getHospitalName());
        role.setUserIdCreate(getUserId());
        role.setGmtCreate(new Date());
        role.setGmtModified(new Date());
        if (iSysRoleService.saveData(role) > 0 ) {
            return ResultUtils.success();
        } else {
            return ResultUtils.error( "保存失败");
        }
    }

    @RequiresPermissions("sys:role:edit")
    @PostMapping("/update")
    @ResponseBody()
    public Result update(@RequestBody SysRole role) {
        role.setGmtModified(new Date());
        if (iSysRoleService.update(role) > 0) {
            return ResultUtils.success();
        } else {
            return ResultUtils.error( "保存失败");
        }
    }

    @RequiresPermissions("sys:role:remove")
    @PostMapping("/remove")
    @ResponseBody()
    public Result save(Long id) {
        if (iSysRoleService.remove(id) > 0) {
            return ResultUtils.success();
        } else {
            return ResultUtils.error("删除失败");
        }
    }

    @RequiresPermissions("sys:role:batchRemove")
    @PostMapping("/batchRemove")
    @ResponseBody
    public Result batchRemove(@RequestParam("ids[]") Long[] ids) {
        if (iSysRoleService.batchremove(ids)) {
            return ResultUtils.success();
        }
        return ResultUtils.error("删除失败");
    }

}
