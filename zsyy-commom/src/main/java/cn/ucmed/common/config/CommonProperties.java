package cn.ucmed.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 读取application-xxx中的配置信息
 */
@Component
@ConfigurationProperties(prefix= CommonProperties.COMMON,ignoreUnknownFields= false)
public class CommonProperties {

	public static final String COMMON = "common";

	private String hostUrl;
	private String profile;

	public String getHostUrl() {
		return hostUrl;
	}

	public void setHostUrl(String hostUrl) {
		this.hostUrl = hostUrl;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}
}
