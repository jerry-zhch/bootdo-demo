package cn.ucmed.admin.controller;

import cn.ucmed.common.shiro.ShiroUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 主页重定向，swagger重定向
 */
@Controller
public class ToolController {

    @GetMapping("/")
    public String logout() {
        ShiroUtils.logout();
        return "admin/blogOpen/main";
    }

    //百度charts示例
    @GetMapping("/admin/graphEcharts")
    public String graphEcharts() {
        return "admin/demo/graphEcharts";
    }

    //首页
    @GetMapping("/admin/main")
    public String main() {
        return "main";
    }


    @GetMapping("/admin/swagger")
    public String swagger() {
        return "redirect:/doc.html";
    }

}
