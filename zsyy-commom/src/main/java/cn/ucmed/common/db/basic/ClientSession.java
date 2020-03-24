package cn.ucmed.common.db.basic;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ClientSession implements Serializable {

	private static final long serialVersionUID=1L;

	@ApiModelProperty(value = "sessionId；如登录后的sessionid",required = true)
	private String sessionId;
	@ApiModelProperty(value = "用户表主键；如 zsyy、rubikU、rubikT",required = true)
	private String userId;
	@ApiModelProperty(value = "用户渠道")
	private String channelType;
	@ApiModelProperty(value = "登录名：登录凭证")
	private String loginName;
	@ApiModelProperty(value = "微信登录openid")
	private String phone;
	@ApiModelProperty(value = "phone")
	private String openId;
	@ApiModelProperty(value = "微信开放平台统一unionid")
	private String unionId;
	@ApiModelProperty(value = "微信小程序session_key")
	private String sessionKey;
	@ApiModelProperty(value = "支付宝登录userid")
	private String aliUserId;
	@ApiModelProperty(value = "医院hospitalId")
	private String hospitalId;
	@ApiModelProperty(value = "应用id")
	private String appId;
	@ApiModelProperty(value = "来源，11 微信服务号，12 微信小程序")
	private String source;

}
