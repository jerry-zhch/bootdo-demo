package cn.ucmed.admin.controller;

import cn.ucmed.common.dbplus.entity.BlogContent;
import cn.ucmed.common.dbplus.service.IBlogContentService;
import cn.ucmed.common.utils.DateUtil;
import cn.ucmed.common.utils.PageUtils;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 博客前台对外
 * @author zhch
 * @since 2020/3/25 21:53
 */
@Slf4j
@Controller
@RequestMapping("/open/blog")
public class BlogOpenController {

    private static final String prefix="admin/blogOpen/";
    @Autowired
    private IBlogContentService blogContentService;

    //博客主页
    @GetMapping()
    public String blog() {
        return prefix+"main";
    }

    //获取博客列表
    @ResponseBody
    @GetMapping("/list")
    public PageUtils openList(@RequestParam Map<String, Object> params) {
        log.info("openList params:"+ JSONObject.toJSONString(params));
        List<BlogContent> bContentList = blogContentService.list(null);
        return new PageUtils(bContentList, (long) (null == bContentList? 0 : bContentList.size()));
    }

    //根据id获取某博客
    @GetMapping("/get/{cid}")
    public String post(@PathVariable("cid") Long cid, Model model) {
        BlogContent bContentDO = blogContentService.getById(cid);
        model.addAttribute("bContent", bContentDO);
        model.addAttribute("gtmModified", DateUtil.dateTimeToString(bContentDO.getGtmModified()));
        return prefix+"post";
    }

    //某个类型的博客如关于、联系
    @GetMapping("/page/{categories}")
    public String about(@PathVariable("categories") String categories, Model model) {
        BlogContent bContentDO =blogContentService.getOne(new QueryWrapper<BlogContent>()
                .eq("categories",categories).last("limit 1"));

        if(null != bContentDO){
            model.addAttribute("bContent", bContentDO);
        }else {
            model.addAttribute("bContent", new BlogContent());
        }
        return prefix+"post";
    }

}
