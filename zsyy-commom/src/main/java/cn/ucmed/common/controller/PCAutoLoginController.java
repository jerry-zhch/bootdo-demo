package cn.ucmed.common.controller;

import cn.ucmed.common.api.LoginStatusApi;
import cn.ucmed.common.db.user.entity.PvUser;
import cn.ucmed.common.db.user.service.IPvUserService;
import cn.ucmed.common.utils.ServletUtils;
import cn.ucmed.common.utils.result.Result;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * pc端自动登录
 */
@Controller
@RequestMapping(value = "/pc/autoLogin")
@Slf4j
public class PCAutoLoginController {

    @Autowired
    private LoginStatusApi loginStatusApi;
    @Autowired
    private IPvUserService pvUserService;

    @RequestMapping(method = RequestMethod.GET)
    public void pcAutoLoginController(HttpServletRequest request, HttpServletResponse response) {
        String dirUrl = (String) request.getSession().getAttribute("dir_url");
        String token = request.getParameter("token");
        if(StringUtils.isBlank(token)) {
            log.info("pc互联网跳转token为空");
            ServletUtils.printWriter(response,"<script type='text/javascript'>alert('会话已失效,请重新登录')</script>");
            return ;
        }
        log.info("pc互联网跳转用户token：" + token);
        Result result = new Result();
        if(!result.isSuccess()){
            log.info("pc获取用户信息失败，" + result.getMessage());
            ServletUtils.printWriter(response,"<script type='text/javascript'>alert('会话已失效，请重新登录')</script>");
            return ;
        }
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(result.getData()));
        String userId = jsonObject.getString("user_id");
        //通过userid肯定能查出来用户
        PvUser user = pvUserService.getById(userId);
        if(user == null) {
            log.info("pc" + userId + ":获取用户信息为空");
            ServletUtils.printWriter(response,"<script type='text/javascript'>alert('先关注微信公众号完成注册，领卡方能问诊')</script>");
            return ;
        }
        jsonObject.put("phone",jsonObject.getString("mobile"));
        //99 pc端
        loginStatusApi.saveClientSession(request, jsonObject, user.getAccount(),  user.getUnionId(),"99", null);
        ServletUtils.printWriter(response,"<script type='text/javascript'>window.location.href='" + dirUrl + "'</script>");
    }
}
