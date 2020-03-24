package cn.ucmed.admin.controller;

import cn.ucmed.common.constant.ProjectLevelEnum;
import cn.ucmed.common.db.hospital.entity.Project;
import cn.ucmed.common.db.hospital.service.IProjectService;
import cn.ucmed.common.utils.CommonUtil;
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
 * 项目管理
 */
@Controller
@RequestMapping("/admin/hospital/project")
public class ProjectController extends SysBaseController {

    private String prefix = "admin/hospital/project";

    @Autowired
    private IProjectService projectService;

    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("admin:hospital:project")
    public String index() {
        return prefix + "/index";
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    @RequiresPermissions("admin:hospital:project")
    public PageUtils list(@RequestBody JSONObject data) {
        IPage page = PageUtils.getIPage(data);
        IPage<Project> result = projectService.page(page, new QueryWrapper<Project>());
        List<Project> projects = result.getRecords();
        for (Project project : projects) {
            project.setProjectLevel(ProjectLevelEnum.getDesc(project.getProjectLevel()));
        }
        return new PageUtils(projects, result.getTotal());
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @RequiresPermissions("admin:hospital:project:add")
    String add(Model model) {
        model.addAttribute("projectLevels", CommonUtil.enumToJson(ProjectLevelEnum.values()));
        return prefix + "/add";
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("admin:hospital:project:edit")
    public String edit(@PathVariable("id") String id, Model model) {
        Project record = projectService.getById(id);
        model.addAttribute("projectLevels", CommonUtil.enumToJson(ProjectLevelEnum.values()));
        model.addAttribute("record", record);
        return prefix + "/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("admin:hospital:project:add")
    public Result save(Project record) {
        record.setCreatedby(getUsername());
        record.setCreatedon(new Date());
        if (projectService.save(record)) {
            return ResultUtils.success();
        }
        return ResultUtils.error("保存记录失败");
    }

    /**
     * 修改
     */
    @ResponseBody
    @PostMapping("/update")
    @RequiresPermissions("admin:hospital:project:edit")
    public Result update(@RequestBody Project record) {
        record.setModifiedon(new Date());
        record.setModifiedby(getUsername());
        if (projectService.updateById(record)) {
            return ResultUtils.success();
        }
        return ResultUtils.error("修改记录失败");
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("admin:hospital:project:remove")
    public Result remove(String id) {
        if (projectService.removeById(id)) {
            return ResultUtils.success();
        }
        return ResultUtils.error("删除记录失败");
    }

}

