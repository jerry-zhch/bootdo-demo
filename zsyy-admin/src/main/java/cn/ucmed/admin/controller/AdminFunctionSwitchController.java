package cn.ucmed.admin.controller;

import cn.ucmed.common.db.function.entity.FunctionSwitch;
import cn.ucmed.common.db.function.service.IFunctionSwitchService;
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

import java.util.Date;
import java.util.Map;

/**
 * 后台：功能开关
 */
@Controller
@RequestMapping("/admin/functionSwitch")
public class AdminFunctionSwitchController extends SysBaseController {
    @Autowired
    private IFunctionSwitchService functionSwitchService;

    /**
     * 支付配置主页面
     */
    @GetMapping()
    @RequiresPermissions("admin:functionSwitch:functionSwitch")
    public String functionSwitch() {
        return "admin/functionSwitch/functionSwitch";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("admin:functionSwitch:functionSwitch")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        JSONObject myParms = JSONObject.parseObject(JSONObject.toJSONString(params));
        long offset = myParms.getLong("offset");
        long limit = myParms.getLong("limit");
        long pages = offset / limit + 1;
        IPage page = new Page<FunctionSwitch>(pages, limit);
        QueryWrapper<FunctionSwitch> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(getHospitalId()), "hospital_id", getHospitalId());
        IPage<FunctionSwitch> userPage = functionSwitchService.page(page, wrapper);
        Long total = userPage.getTotal();
        PageUtils pageUtils = new PageUtils(userPage.getRecords(), total);
        return pageUtils;
    }

    @GetMapping("/add")
    @RequiresPermissions("admin:functionSwitch:add")
    public String add() {
        return "admin/functionSwitch/add";
    }

    @GetMapping("/edit/{hospitalId}")
    @RequiresPermissions("admin:functionSwitch:edit")
    public String edit(@PathVariable("hospitalId") String  hospitalId, Model model) {
        FunctionSwitch functionSwitch = functionSwitchService.getById(hospitalId);
        model.addAttribute("functionSwitch", functionSwitch);
        return "admin/functionSwitch/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("admin:functionSwitch:add")
    public Result save(FunctionSwitch functionSwitch) {
        FunctionSwitch list = functionSwitchService.getById(getHospitalId());
        if(list != null) {
            return ResultUtils.error("已存在功能开关配置");
        }
        functionSwitch.setCreateTime(new Date());
        functionSwitch.setUpdateTime(new Date());
        functionSwitch.setHospitalId(getHospitalId());
        if (functionSwitchService.save(functionSwitch)) {
            return ResultUtils.success();
        }
        return ResultUtils.error("操作失败");
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("admin:functionSwitch:edit")
    public Result update(@RequestBody FunctionSwitch functionSwitch) {
        functionSwitch.setUpdateTime(new Date());
        functionSwitchService.updateById(functionSwitch);
        return ResultUtils.success();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("admin:functionSwitch:remove")
    public Result remove(String hospitalId) {
        if (functionSwitchService.removeById(hospitalId)) {
            return ResultUtils.success();
        }
        return ResultUtils.error("操作失败");
    }

}
