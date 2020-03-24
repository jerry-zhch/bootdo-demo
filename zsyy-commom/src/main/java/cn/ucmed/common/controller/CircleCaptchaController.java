package cn.ucmed.common.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.ucmed.common.db.basic.Cache;
import cn.ucmed.common.shiro.ShiroUtils;
import cn.ucmed.common.utils.ServletUtils;
import cn.ucmed.common.utils.redis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 微信端验证码
 */
@Controller
public class CircleCaptchaController {

    public static final String SHANDONG_VERIFICATION_CODE_GRAPHIC = "verificationCodeGraphic:";

    @Autowired
    private RedisUtils redisUtils;

    @RequestMapping("/wx/captcha.jpg")
    public void captcha(HttpServletResponse response, HttpServletRequest request)throws IOException {
        String phone = ServletUtils.getParameter(request,"phone");
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(200, 100, 4, 20);
        String code = captcha.getCode();
        Cache cache = new Cache();
        cache.setKey(SHANDONG_VERIFICATION_CODE_GRAPHIC + phone);
        cache.setValue(code);
        redisUtils.set(cache,15L, TimeUnit.MINUTES);
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        ServletOutputStream out = response.getOutputStream();
        captcha.write(out);
        out.close();
    }

    @RequestMapping("/admin/captcha.jpg")
    public void adminCaptcha(HttpServletResponse response)throws IOException {
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(200, 100, 4, 20);
        String code = captcha.getCode();
        ShiroUtils.setSessionAttribute("KAPTCHA_SESSION_KEY", code);
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        ServletOutputStream out = response.getOutputStream();
        captcha.write(out);
        out.close();
    }

}
