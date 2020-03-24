package cn.ucmed.admin.controller;

import cn.ucmed.common.db.system.entity.*;
import cn.ucmed.common.db.system.service.ISysRoleService;
import cn.ucmed.common.db.system.service.ISysUserService;
import cn.ucmed.common.utils.MD5Utils;
import cn.ucmed.common.utils.PageUtils;
import cn.ucmed.common.utils.result.Result;
import cn.ucmed.common.utils.result.ResultUtils;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 用户管理
 */
@Controller
@RequestMapping("/admin/sys-user")
public class SysUserController extends SysBaseController{
    private String prefix = "admin/sysUser";
    @Autowired
    ISysUserService userService;
    @Autowired
    ISysRoleService roleService;

    @RequiresPermissions("sys:user:user")
    @GetMapping("")
    public String user() {
        return prefix + "/user";
    }

    @PostMapping("/list")
    @ResponseBody
    public PageUtils list(@RequestBody JSONObject query) {
        String name = query.getString("name");
        long offset = query.getLong("offset");
        long limit = query.getLong("limit");
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper();
        String deptId = query.getString("deptId");
        if(!"".equals(deptId)) {
            queryWrapper.eq("dept_id",query.getLong("deptId"));
        }
        long pages = offset / limit + 1;
        IPage page = new Page<SysUser>(pages, limit);
        queryWrapper.eq(StringUtils.isNotBlank(name),"name",name);
        queryWrapper.eq("hospital_id",getHospitalId());
        if("1".equals(getUser().getIsSuperman()) && "".equals(name) && "".equals(deptId)) {
            queryWrapper.or();
            queryWrapper.eq( "project_id",getProjectId());
        }
        IPage<SysUser> sysUserIPage = userService.page(page,queryWrapper);
        int total = userService.count(queryWrapper);
        return new PageUtils(sysUserIPage.getRecords(), (long)total);
    }

    @RequiresPermissions("sys:user:add")
    @GetMapping("/add")
    public String add(Model model) {
        String hospitalId = getHospitalId();
        List<SysRole> roles = roleService.list(new QueryWrapper<SysRole>()
            .eq("hospital_id",hospitalId));
        model.addAttribute("roles", roles);
        model.addAttribute("user",getUser());
        return prefix + "/add";
    }

    @RequiresPermissions("sys:user:edit")
    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        SysUser userDO = userService.get(id);
        model.addAttribute("user", userDO);
        model.addAttribute("record", getUser());
        List<SysRole> roles = roleService.list(id,getHospitalId());
        model.addAttribute("roles", roles);
        return prefix+"/edit";
    }

    @RequiresPermissions("sys:user:add")
    @PostMapping("/save")
    @ResponseBody
    public Result save(SysUser user) {
        user.setPassword(MD5Utils.encrypt(user.getUsername(), user.getPassword()));
        SysUser record = userService.getOne(new QueryWrapper<SysUser>().eq("username",user.getUsername()).last("limit 1"));
        if(record != null) {
            return ResultUtils.error("用户名已存在");
        }
        user.setCreatedTime(new Date());
        user.setUpdateTime(new Date());
        if(!"1".equals(user.getIsProject())) {
            user.setHospitalId(getHospitalId());
        }
        user.setProjectId(getProjectId());
        if (userService.saveData(user) > 0) {
            return ResultUtils.success();
        }
        return ResultUtils.error("保存失败");
    }

    @RequiresPermissions("sys:user:edit")
    @PostMapping("/update")
    @ResponseBody
    public Result update(SysUser user) {
        user.setUpdateTime(new Date());
        if (userService.update(user) > 0) {
            return ResultUtils.success();
        }
        return ResultUtils.error("修改失败");
    }

    @RequiresPermissions("sys:user:edit")
    @PostMapping("/updatePeronal")
    @ResponseBody
    public Result updatePeronal(SysUser user) {
        if (userService.updatePersonal(user) > 0) {
            return ResultUtils.success();
        }
        return ResultUtils.error("修改失败");
    }

    @RequiresPermissions("sys:user:remove")
    @PostMapping("/remove")
    @ResponseBody
    public Result remove(Long id) {
        if (userService.remove(id) > 0) {
            return ResultUtils.success();
        }
        return ResultUtils.error("操作失败");
    }

    @RequiresPermissions("sys:user:resetPwd")
    @GetMapping("/resetPwd/{id}")
    public String resetPwd(@PathVariable("id") Long userId, Model model) {
        SysUser userDO = new SysUser();
        userDO.setUserId(userId);
        model.addAttribute("user", userDO);
        return prefix + "/reset_pwd";
    }

    @PostMapping("/resetPwd")
    @ResponseBody
    public Result resetPwd(SysUserVO userVO) {
        try{
            userService.resetPwd(userVO,getUser());
            return ResultUtils.success();
        }catch (Exception e){
            return ResultUtils.error(e.getMessage());
        }

    }

    @RequiresPermissions("sys:user:resetPwd")
    @PostMapping("/adminResetPwd")
    @ResponseBody
    public Result adminResetPwd(HttpServletRequest request) {
        try{
            String userId = request.getParameter("userId");
            String newPwd = request.getParameter("pwdNew");
            userService.adminResetPwd(userId,newPwd);
            return ResultUtils.success();
        }catch (Exception e){
            return ResultUtils.error(e.getMessage());
        }

    }

    @GetMapping("/tree")
    @ResponseBody
    public Tree<SysDept> tree() {
        return userService.getTree();
    }

    @GetMapping("/treeView")
    public String treeView() {
        return  prefix + "/userTree";
    }

    @GetMapping("/personal")
    public String personal(Model model) {
        SysUser userDO  = userService.get(getUserId());
        model.addAttribute("user",userDO);
        return prefix + "/personal";
    }

}

