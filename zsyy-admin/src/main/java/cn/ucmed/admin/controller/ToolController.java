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
        return "redirect:/admin/login";
    }

    @GetMapping("/admin/swagger")
    public String swagger() {
        return "redirect:/doc.html";
    }

}
