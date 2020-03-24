package cn.ucmed.common.api;

import cn.hutool.extra.emoji.EmojiUtil;
import cn.ucmed.common.utils.CommonUtil;
import cn.ucmed.common.utils.StringUtils;
import cn.ucmed.common.utils.result.Result;
import cn.ucmed.common.utils.result.ResultUtils;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Api(tags = "微信工具类")
@RestController
@RequestMapping(value = "/api/wechatUtils")
@Slf4j
public class WechatUtilsApi {

    @Autowired
    private CommonUtil commonUtil;

    @Autowired
    private RestTemplate restTemplate;

    @ApiOperation(value = "获取openId和UnionId",position = 1)
    @PostMapping("/getOpenIdAndUnionId")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appId", value = "微信appId", required = true) ,
            @ApiImplicitParam(name = "appSecret", value = "微信appSecret", required = true),
            @ApiImplicitParam(name = "code", value = "微信code", required = true),
    })
    public Result getOpenIdAndUnionId(@RequestParam(value = "appId")String appId, @RequestParam(value = "appSecret")String appSecret, @RequestParam(value = "code")String code){
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
                + appId + "&secret=" + appSecret + "&code=" + code + "&grant_type=authorization_code";
        log.info("获取openID url:{}",url);
        HttpEntity<String> entity = new HttpEntity<>(commonUtil.headers(MediaType.APPLICATION_JSON_UTF8));
        ResponseEntity<String> responseEntity;
        try {
            responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        } catch (RestClientException e) {
            log.info("获取openID接口异常", e);
            return ResultUtils.error("请求异常，请稍后重试");
        }
        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            return ResultUtils.error(responseEntity.getStatusCodeValue(), "返回结果为空");
        }
        String result = null;
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            result = responseEntity.getBody();
            log.info("获取openID接口出参：{}", result);
        }
        if (result == null) {
            return ResultUtils.error("返回结果为空");
        }
        JSONObject jsonObject =JSONObject.parseObject(result);
        if(jsonObject.containsKey("errcode")) {
            return ResultUtils.error(jsonObject.getString("errmsg"));
        }
        return ResultUtils.success(jsonObject);
    }

    @ApiOperation(value = "获取access_token",position = 2)
    @PostMapping("/getAccessToken")
    public Result getAccessToken(){
        //TODO 
        String url = "";
        log.info("获取access_token url:{}",url);
        HttpEntity<String> entity = new HttpEntity<>(commonUtil.headers(MediaType.APPLICATION_JSON_UTF8));
        ResponseEntity<String> responseEntity ;
        try {
            responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        } catch (RestClientException e) {
            log.info("获取access_token接口异常", e);
            return ResultUtils.error("请求异常，请稍后重试");
        }
        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            return ResultUtils.error(responseEntity.getStatusCodeValue(), "返回结果为空");
        }
        String result = null;
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            result = responseEntity.getBody();
            log.info("获取access_token接口出参：{}", result);
        }
        if (result == null) {
            return ResultUtils.error("返回结果为空");
        }
        JSONObject jsonObject =JSONObject.parseObject(result);
        if(!jsonObject.getBoolean("suc")) {
            return ResultUtils.error("获取access_token失败");
        }
        return ResultUtils.success(jsonObject.getString("extra"));
    }

    @ApiOperation(value = "获取用户信息",position = 3)
    @PostMapping("/getUserInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openId", value = "openId", dataType = "String", paramType = "query", required = true) ,
            @ApiImplicitParam(name = "accessToken", value = "access_token", dataType = "String", paramType = "query",  required = true) ,
    })
    public Result getUserInfo(@RequestParam(value = "openId")String openId,@RequestParam(value = "accessToken")String accessToken){
        String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + accessToken + "&openid=" + openId + "&lang=zh_CN";
        log.info("获取用户信息 url:{}",url);
        HttpEntity<JSONObject> entity = new HttpEntity<>(commonUtil.headers(MediaType.APPLICATION_JSON_UTF8));
        ResponseEntity<JSONObject> responseEntity ;
        try {
            responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, JSONObject.class);
        } catch (RestClientException e) {
            log.info("获取用户信息接口异常", e);
            return ResultUtils.error("请求异常，请稍后重试");
        }
        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            return ResultUtils.error(responseEntity.getStatusCodeValue(), "返回结果为空");
        }
        JSONObject result = null;
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            result = responseEntity.getBody();
            log.info("获取用户信息接口出参：{}", result);
        }
        if (result == null) {
            return ResultUtils.error("返回结果为空");
        }

        if(result.containsKey("errcode")) {
            return ResultUtils.error(result.getString("errmsg"));
        }
        String nickname = result.getString("nickname");
        nickname = EmojiUtil.toUnicode(nickname);
        result.put("nickname",nickname);
        return ResultUtils.success(result);
    }

    @ApiOperation(value = "消息推送",position = 4)
    @PostMapping("/wechatPush")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openId", value = "openId", dataType = "String", paramType = "query", required = true) ,
            @ApiImplicitParam(name = "accessToken", value = "access_token", dataType = "String", paramType = "query",  required = true) ,
            @ApiImplicitParam(name = "templateId", value = "模板id", dataType = "String", paramType = "query",  required = true) ,
            @ApiImplicitParam(name = "content", value = "消息内容({\"first\":\"取消成功\",\"keyword1\":\"xxxx\",\"keyword2\":\"xxx\",\"remark\":\"取消成功\"})", dataType = "String", paramType = "query",  required = true) ,
    })
    public Result wechatPush(@RequestParam(value = "openId")String openId,@RequestParam(value = "accessToken")String accessToken,@RequestParam(value = "templateId")String templateId,@RequestParam(value = "content")String content){
        log.info("开始向{}推送微信公众号消息",openId);
        JSONObject contentJS = JSONObject.parseObject(content);
        JSONObject data = new JSONObject();
        for(Map.Entry<String, Object> entry : contentJS.entrySet()) {
            String key = entry.getKey();
            String value = contentJS.getString(key);
            if(StringUtils.isBlank(value)) {
                value = "暂无";
            }
            data.put(key,setData(value,"#173177"));
        }
        JSONObject req = new JSONObject();
        req.put("touser",openId);
        req.put("template_id",templateId);
        req.put("data",data);
        String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + accessToken;
        log.info("向{}推送微信公众号消息入参:{}",openId,req.toJSONString());
        JSONObject result = restTemplate.postForObject(url,req, JSONObject.class);
        assert result != null;
        log.info("向{}推送微信公众号消息出参:{}",openId,result.toJSONString());
        if(result.getInteger("errcode") != 0) {
            return ResultUtils.error(result.getString("errmsg"));
        }
        return ResultUtils.success();
    }

    /**
     * 封装data
     */
    private JSONObject setData(String value, String color) {
        JSONObject data = new JSONObject();
        data.put("value",value);
        data.put("color", color);
        return data;
    }

}
