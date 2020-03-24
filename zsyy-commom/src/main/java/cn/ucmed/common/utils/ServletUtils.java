package cn.ucmed.common.utils;

import cn.hutool.extra.servlet.ServletUtil;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * 客户端工具类
 */
public class ServletUtils extends ServletUtil {
    /**
     * 从request或session中获取
     */
    public static String getParameter(HttpServletRequest request, String name) {
        HttpSession session = request.getSession();
        return request.getParameter(name) == null ? (String) session.getAttribute(name) : request.getParameter(name);
    }

    /**
     * 转换request为jsonobject
     */
    public static JSONObject parseRequestToJsonobject(HttpServletRequest request) {
        JSONObject obj = new JSONObject();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String name = parameterNames.nextElement();
            Object value = request.getParameter(name);
            obj.put(name, value);
        }
        return obj;
    }

    /**
     * 获取请求完整URL
     */
    public static String getRequestURL(HttpServletRequest request) {
        if (request == null) {
            return "";
        }
        String url = "";
        try {
            url = request.getContextPath() // 项目名称
                    + request.getServletPath() // 请求页面或其他地址
                    + "?" + (request.getQueryString()); // 参数

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return url;
    }

    /**
     * 拼接url
     */
    public static void getRedirectUrl(HttpServletRequest request, StringBuilder redirectUrl) {
        Enumeration<?> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String key = (String) enu.nextElement();
            String value = request.getParameter(key);
            redirectUrl.append("&" + key + "=" + value);
        }
    }

    /**
     * 返回页面
     */
    public static void printWriter(HttpServletResponse response, String text) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        PrintWriter writer = getWriter(response);
        writer.write(text);
        writer.flush();
    }

    /**
     * 将字符串渲染到客户端
     */
    public static String renderString(HttpServletResponse response, String string) {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = getWriter(response);
        writer.write(string);
        writer.flush();
        return  null;
    }

    /**
     * 是否是Ajax异步请求
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String accept = request.getHeader("accept");
        if (accept != null && accept.contains("application/json")) {
            return true;
        }

        String xRequestedWith = request.getHeader("X-Requested-With");
        if (xRequestedWith != null && xRequestedWith.contains("XMLHttpRequest")) {
            return true;
        }

        String uri = request.getRequestURI();
        if (StringUtils.inStringIgnoreCase(uri, ".json", ".xml")) {
            return true;
        }

        String ajax = request.getParameter("__ajax");
        return StringUtils.inStringIgnoreCase(ajax, "json", "xml");
    }

}
