package cn.ucmed.admin.controller;

import cn.ucmed.common.db.news.entity.MsgFlow;
import cn.ucmed.common.db.news.service.IMsgFlowService;
import cn.ucmed.common.utils.PageUtils;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 发送消息记录
 */
@Controller
@RequestMapping("/admin/news/msgFlow")
public class MsgFlowController extends SysBaseController {

    private String prefix = "admin/news/msgFlow";

    @Autowired
    private IMsgFlowService msgFlowService;

    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("admin:news:msgFlow")
    public String index() {
        return prefix + "/index";
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    @RequiresPermissions("admin:news:msgFlow")
    public PageUtils list(@RequestBody JSONObject data) {
        IPage page = PageUtils.getIPage(data);
        IPage<MsgFlow> result = msgFlowService.page(page, new QueryWrapper<MsgFlow>()
                .eq("hospital_id", getHospitalId())
                .like("recipient", data.getString("recipient"))
                .orderByDesc("create_time"));
        return new PageUtils(result.getRecords(), result.getTotal());
    }

    @GetMapping("/detail/{id}")
    @RequiresPermissions("admin:news:msgFlow:detail")
    public String detail(@PathVariable("id") String id, Model model) {
        MsgFlow record = msgFlowService.getById(id);
        model.addAttribute("record", record);
        return prefix + "/detail";
    }

}
