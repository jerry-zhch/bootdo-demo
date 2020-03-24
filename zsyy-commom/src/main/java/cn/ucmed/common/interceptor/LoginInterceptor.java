package cn.ucmed.common.interceptor;

import cn.hutool.core.util.CharsetUtil;
import cn.ucmed.common.db.basic.ClientSessionCache;
import cn.ucmed.common.utils.StringUtils;
import cn.ucmed.common.utils.redis.RedisUtils;
import cn.ucmed.common.utils.ServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器具体处理
 */
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisUtils redisUtils;
    public static boolean flag = true;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取请求头
        String userAgent = ServletUtils.getHeader(request, "user-agent", CharsetUtil.UTF_8);
        String path = request.getRequestURI();
        String hisUrl = ServletUtils.getRequestURL(request);
        request.getSession().setAttribute("dir_url", hisUrl);
        log.info("进入拦截器：》》》path:{};userAgent:{}", path, userAgent);
        if (null != userAgent && userAgent.contains("MicroMessenger")) {
            if (userAgent.contains("miniProgram")) {
                //小程序
                String sessionId = ServletUtils.getParameter(request, "session_id");
                String hospitalId1 = StringUtils.isBlank(request.getParameter("hospitalId")) ? (String) request.getSession().getAttribute("hospital_id") : request.getParameter("hospitalId");
                request.getSession().setAttribute("hospital_id", hospitalId1);
                log.info("小程序登录拦截器hospital_id：{}", hospitalId1);
                log.info("小程序登录拦截器session_id：{}", sessionId);
                if (isLogin(sessionId) && StringUtils.isNotBlank(hospitalId1) && sessionId.startsWith("hlwxcx")) {
                    return true;
                }
                StringBuilder redirectUrl = new StringBuilder();
                redirectUrl.append("/userCenter/autoLogin?preUri=").append(path);
                log.info("小程序互联网自动登录获取用户信息:{}", redirectUrl);
                ServletUtils.getRedirectUrl(request, redirectUrl);
                response.sendRedirect(redirectUrl.toString());
                return false;
            } else {
                //请求来源于微信公众号
                String sessionId = ServletUtils.getParameter(request, "session_id");
                String hospitalId = ServletUtils.getParameter(request, "hospital_id");
                log.info("微信公众号登录拦截器hospital_id：{}", hospitalId);
                log.info("微信公众号登录拦截器session_id：{}", sessionId);
                if (isLogin(sessionId) && sessionId.startsWith("wxgzh")) {
                    return true;
                }
                StringBuilder redirectUrl = new StringBuilder();
                redirectUrl.append("/wechat/autoLogin?preUri=").append(path);
                log.info("微信自动登录获取用户信息:{}", redirectUrl);
                ServletUtils.getRedirectUrl(request, redirectUrl);
                response.sendRedirect(redirectUrl.toString());
                return false;
            }
        } else {
            //pc端掌医流程
            String sessionId = ServletUtils.getParameter(request, "session_id");
            String hospitalId1 = StringUtils.isBlank(request.getParameter("hospitalId")) ? (String) request.getSession().getAttribute("hospital_id") : request.getParameter("hospitalId");
            request.getSession().setAttribute("hospital_id", hospitalId1);
            log.info("PC端登录拦截器hospital_id：{}", hospitalId1);
            log.info("PC端登录拦截器session_id：{}", sessionId);
            if (isLogin(sessionId) && StringUtils.isNotBlank(hospitalId1) && sessionId.startsWith("hlwpc")) {
                return true;
            }
            StringBuilder redirectUrl = new StringBuilder();
            redirectUrl.append("/pc/autoLogin?preUri=").append(path);
            log.info("PC端自动登录获取用户信息:{}", redirectUrl);
            ServletUtils.getRedirectUrl(request, redirectUrl);
            response.sendRedirect(redirectUrl.toString());
            return false;
        }
    }

    /* *
     * create by: 郄彦腾
     * description: 是否登录
     * create time: 2020/1/10 19:31
     * @return boolean
     */
    private boolean isLogin(String sessionId) {
        if (StringUtils.isNotBlank(sessionId) && redisUtils.get("session_id" + sessionId) != null) {
            ClientSessionCache clinetSession = redisUtils.get("session_id" + sessionId);
            log.info("openid：{}", clinetSession.getClientSession().getOpenId());
            log.info("userId：{}", clinetSession.getClientSession().getUserId());
            return true;
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    }

}
