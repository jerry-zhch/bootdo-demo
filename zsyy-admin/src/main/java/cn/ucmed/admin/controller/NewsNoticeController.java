package cn.ucmed.admin.controller;

import cn.ucmed.common.db.news.entity.Notice;
import cn.ucmed.common.db.news.service.INoticeService;
import cn.ucmed.common.utils.PageUtils;
import cn.ucmed.common.utils.result.Result;
import cn.ucmed.common.utils.result.ResultUtils;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 新闻公告
 */
@Controller
@RequestMapping("/admin/news-notice")
public class NewsNoticeController extends SysBaseController {

    @Autowired
    private INoticeService iNoticeService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @RequiresPermissions("admin:news:notice")
    public String index() {
        return "admin/newNotice/list";
    }

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    @RequiresPermissions("admin:news:notice")
    public PageUtils list(@RequestBody JSONObject myParms) {
        long offset = myParms.getLong("offset");
        long limit = myParms.getLong("limit");
        long pages = offset / limit + 1;
        IPage page = new Page<Notice>(pages, limit);
        IPage<Notice> userPage = iNoticeService.page(page, new QueryWrapper<Notice>()
                .eq("hospital_id", getHospitalId())
                .orderByDesc("sort")
                .orderByDesc("release_date")
        );
        Long total = userPage.getTotal();
        PageUtils pageUtils = new PageUtils(userPage.getRecords(), total);
        return pageUtils;
    }

    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @RequiresPermissions("admin:news:add")
    String add() {
        return "admin/newNotice/add";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("admin:news:add")
    public Result save(@RequestBody Notice record) {
        record.setHospitalId(getHospitalId());
        record.setReleaseDate(new Date());
        record.setUpdateTime(new Date());
        Notice notice = iNoticeService.getOne(new QueryWrapper<Notice>().eq("channel_type",record.getChannelType())
        .eq("hospital_id",getHospitalId()));
        if(notice != null) {
            return ResultUtils.error("已存在该类型内容，添加失败");
        }
        if (iNoticeService.save(record)) {
            return ResultUtils.success();
        }
        return ResultUtils.error("保存记录失败");
    }

    /**
     * 编辑
     */

    @GetMapping("/edit/{id}")
    @RequiresPermissions("admin:news:edit")
    public String edit(@PathVariable("id") String id, HttpServletRequest request, Model model) {
        Notice record = iNoticeService.getById(id);
        model.addAttribute("record", record);
        //其他
        return "admin/newNotice/edit";
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("admin:news:edit")
    public Result update(@RequestBody Notice record) {
        record.setUpdateTime(new Date());
        if (iNoticeService.updateById(record)) {
            return ResultUtils.success();
        }
        return ResultUtils.error("修改记录失败");
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("admin:news:delete")
    public Result remove(String id) {
        if (iNoticeService.removeById(id)) {
            return ResultUtils.success();
        }
        return ResultUtils.error("删除记录失败");
    }

    @RequiresPermissions("admin:news:batchRemove")
    @PostMapping("/batchRemove")
    @ResponseBody
    public Result batchRemove(@RequestParam("ids[]") String[] ids) {
        List<String> idList = Arrays.asList(ids);
        if (iNoticeService.removeByIds(idList)) {
            return ResultUtils.success();
        }
        return ResultUtils.error("操作失败");
    }

}
