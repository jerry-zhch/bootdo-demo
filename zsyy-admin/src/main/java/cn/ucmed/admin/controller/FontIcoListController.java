package cn.ucmed.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 选择图标页面
 */
@Controller
@RequestMapping("/FontIcoList.html")
public class FontIcoListController {

    @GetMapping()
    public String fontIcoList() {
        return "admin/public/FontIcoList";
    }

}
