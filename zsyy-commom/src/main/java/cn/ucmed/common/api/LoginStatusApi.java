package cn.ucmed.common.api;

import cn.ucmed.common.db.basic.ClientSession;
import cn.ucmed.common.db.basic.ClientSessionCache;
import cn.ucmed.common.db.user.entity.PvUser;
import cn.ucmed.common.db.user.service.IPvUserService;
import cn.ucmed.common.utils.DESUtil;
import cn.ucmed.common.utils.redis.RedisUtils;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

/**
 * 登录状态api
 */
@Component
public class LoginStatusApi {

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private IPvUserService pvUserService;

    /**
     * 保存登录状态
     */
    public String saveClientSession(HttpServletRequest request, JSONObject user, String openId, String unionId, String source, String sessionKey) {
        //保存登录状态
        String sessionId = saveClientCache(user, openId, unionId,source,sessionKey);
        request.getSession().setAttribute("open_id", openId);
        request.getSession().setAttribute("union_id", unionId);
        request.getSession().setAttribute("session_id", sessionId);
        request.getSession().setAttribute("user_id", user.getString("user_id")); //登录成功后放入session
        return sessionId;
    }

    /**
     * 保存登录状态到缓存
     */
    public String saveClientCache(JSONObject user, String openId, String unionId,String source, String sessionKey) {
        //保存登录状态
        String sessionId = UUID.randomUUID().toString();
        if("99".equals(source)) {
            sessionId = "hlwpc" + sessionId;
        }
        if("10".equals(source)) {
            sessionId = "wxgzh" + sessionId;
        }
        if("12".equals(source)) {
            sessionId = "hlwxcx" + sessionId;
        }
        ClientSession clientSession = new ClientSession();
        clientSession.setOpenId(openId);
        clientSession.setLoginName(user.getString("user_id"));
        clientSession.setSessionId(sessionId);
        clientSession.setSessionKey(sessionKey);
        clientSession.setUnionId(unionId);
        clientSession.setUserId(user.getString("user_id"));
        clientSession.setPhone(user.getString("phone"));
        clientSession.setChannelType(source);
        PvUser user1 = pvUserService.getOne(new QueryWrapper<PvUser>().eq("account",openId));
        if(user1 == null) {
            PvUser newUser = new PvUser();
            newUser.setPhone(DESUtil.encryptHex(user.getString("phone")));
            newUser.setUserId(user.getString("user_id"));
            newUser.setAccount(openId);
            newUser.setUnionId(unionId);
            if("12".equals(source)) {
                //小程序
                newUser.setThirdOpenId(user.getString("openId"));
            }
            newUser.setCreateTime(new Date());
            newUser.setUpdateTime(new Date());
            pvUserService.save(newUser);
        }else {
            if("12".equals(source)) {
                //小程序
                user1.setThirdOpenId(user.getString("openId"));
            }
            user1.setUnionId(unionId);
            pvUserService.updateById(user1);
        }
        ClientSessionCache sessionCache = new ClientSessionCache();
        sessionCache.setSessionId(sessionId);
        sessionCache.setClientSession(clientSession);
        redisUtils.set(sessionCache, 60 * 60 * 24);
        return sessionId;
    }

}
