package cn.ucmed.admin.controller;

import cn.ucmed.common.db.hospital.entity.Project;
import cn.ucmed.common.db.hospital.entity.SysHospital;
import cn.ucmed.common.db.hospital.service.IProjectService;
import cn.ucmed.common.db.hospital.service.ISysHospitalService;
import cn.ucmed.common.db.system.entity.SysUser;
import cn.ucmed.common.db.system.entity.Tree;
import cn.ucmed.common.db.system.service.ISysUserService;
import cn.ucmed.common.shiro.ShiroUtils;
import cn.ucmed.common.utils.result.Result;
import cn.ucmed.common.utils.result.ResultUtils;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 医院管理、项目管理
 */
@Controller
@RequestMapping("/admin/hospital/hospital")
public class HospitalController extends SysBaseController {

    private String prefix = "admin/hospital/hospital";

    @Autowired
    private IProjectService projectService;

    @Autowired
    private ISysHospitalService hospitalService;

    @Autowired
    private ISysUserService sysUserService;

    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("admin:hospital:hospital")
    public String index() {
        return prefix + "/index";
    }

    @ResponseBody
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @RequiresPermissions("admin:hospital:hospital")
    public List<SysHospital> list() {
        return hospitalService.list(new QueryWrapper<SysHospital>()
                .eq("delete_state","0").orderByAsc("seq"));
    }

    @RequestMapping(value = "/add/{pId}",method = RequestMethod.GET)
    @RequiresPermissions("admin:hospital:hospital:add")
    String add(Model model,@PathVariable("pId") String pId) {
        model.addAttribute("pId", pId);
        if (pId.equals("0")) {
            model.addAttribute("pName", "后台管理demo");
        } else {
            SysHospital hospital  = hospitalService.getById(pId);
            model.addAttribute("pName", hospital.getHospitalName());
        }
        List<Project> projects = projectService.list(new QueryWrapper<>());
        model.addAttribute("projects", projects);
        return  prefix + "/add";
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("admin:hospital:hospital:edit")
    public String edit(@PathVariable("id") String id, Model model) {
        SysHospital record = hospitalService.getById(id);
        String pId = record.getParentId();
        model.addAttribute("pId", pId);
        if (pId.equals("0")) {
            model.addAttribute("pName", "bootdo项目");
        } else {
            SysHospital pRecord  = hospitalService.getById(pId);
            model.addAttribute("pName", pRecord.getHospitalName());
        }
        List<Project> projects = projectService.list(new QueryWrapper<>());
        model.addAttribute("record", record);
        model.addAttribute("projects", projects);
        return  prefix + "/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("admin:hospital:hospital:add")
    public Result save(@RequestBody SysHospital record) {
        JSONObject extent = JSONObject.parseObject(record.getExtend());
        record.setExtend(extent.toJSONString());
        record.setCreateTime(new Date());
        if (hospitalService.save(record)) {
            return ResultUtils.success();
        }
        return ResultUtils.error("保存记录失败");
    }

    /**
     * 修改
     */
    @ResponseBody
    @PostMapping("/update")
    @RequiresPermissions("admin:hospital:hospital:edit")
    public Result update(@RequestBody SysHospital record) {
        JSONObject extent = JSONObject.parseObject(record.getExtend());
        record.setExtend(extent.toJSONString());
        record.setUpdateTime(new Date());
        if (hospitalService.updateById(record)) {
            return ResultUtils.success();
        }
        return ResultUtils.error("修改记录失败");
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("admin:hospital:hospital:remove")
    public Result remove(String id) {
        if (hospitalService.removeById(id)) {
            return ResultUtils.success();
        }
        return ResultUtils.error("删除记录失败");
    }

    @RequestMapping(value = "treeView",method = RequestMethod.GET)
    public String changeHospital() {
        return prefix + "/hospitalTree";
    }

    /**
     *
     */
    @GetMapping("/tree")
    @ResponseBody
    public Tree<SysHospital> Hospitals() {
        SysUser sysUser = ShiroUtils.getUser();
        if (sysUserService.isSuperman(getUserId())){
            return hospitalService.getTreeSuper();
        }
        return hospitalService.getTree(sysUser.getUserId());
    }

    @GetMapping("/yhTree")
    @ResponseBody
    public Tree<JSONObject> tree() {
       Tree<JSONObject> tree = hospitalService.getTree();
        return tree;
    }

    @GetMapping("/yhTreeView")
    public String treeView() {
        return  prefix + "/yhHospitalTree";
    }

}

