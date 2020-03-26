package cn.ucmed.admin.controller;

import cn.ucmed.common.dbplus.entity.BlogContent;
import cn.ucmed.common.dbplus.service.IBlogContentService;
import cn.ucmed.common.utils.PageUtils;
import cn.ucmed.common.utils.result.Result;
import cn.ucmed.common.utils.result.ResultUtils;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 博客后台
 * @author zhch
 * @since 2020/3/25 21:53
 */
@Slf4j
@Controller
@RequestMapping("/admin/blog/bContent")
public class BlogController {

    @Autowired
    private IBlogContentService blogContentService;

    @RequestMapping()
    @RequiresPermissions("blog:bContent:bContent")
    private String bContent() {
        return "admin/blog/bContent";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("blog:bContent:bContent")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        log.info("list param:"+ JSONObject.toJSONString(params));
        List<BlogContent> bContentList = blogContentService.list(null);
        return new PageUtils(bContentList, (long) bContentList.size());
    }

    @GetMapping("/add")
    @RequiresPermissions("blog:bContent:add")
    private String add() {
        return "admin/blog/add";
    }

    @GetMapping("/edit/{cid}")
    @RequiresPermissions("blog:bContent:edit")
    public String edit(@PathVariable("cid") Long cid, Model model) {
        BlogContent bContentDO = blogContentService.getById(cid);
        model.addAttribute("bContent", bContentDO);
        return "admin/blog/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @RequiresPermissions("blog:bContent:add")
    @PostMapping("/save")
    public Result save(BlogContent bContent) {

        if (bContent.getAllowComment() == null) {
            bContent.setAllowComment(0);
        }
        if (bContent.getAllowFeed() == null) {
            bContent.setAllowFeed(0);
        }
        if(null==bContent.getType()) {
            bContent.setType("article");
        }
        bContent.setGtmCreate(new Date());
        bContent.setGtmModified(new Date());
        boolean count = blogContentService.saveOrUpdate(bContent);
        if (count) {
            return ResultUtils.success(bContent.getCid());
        }
        return ResultUtils.error("失败");
    }

    /**
     * 修改
     */
    @RequiresPermissions("blog:bContent:edit")
    @ResponseBody
    @RequestMapping("/update")
    public Result update( BlogContent bContent) {

        bContent.setGtmCreate(new Date());
        blogContentService.updateById(bContent);
        return ResultUtils.success();
    }

    /**
     * 删除
     */
    @RequiresPermissions("blog:bContent:remove")
    @PostMapping("/remove")
    @ResponseBody
    public Result remove(Long id) {

        if (blogContentService.removeById(id)) {
            return ResultUtils.success();
        }
        return ResultUtils.error("失败");
    }

    /**
     * 删除
     */
    @RequiresPermissions("blog:bContent:batchRemove")
    @PostMapping("/batchRemove")
    @ResponseBody
    public Result remove(@RequestParam("ids[]") Long[] cids) {

        blogContentService.removeByIds(Arrays.asList(cids));
        return ResultUtils.success();
    }
}
