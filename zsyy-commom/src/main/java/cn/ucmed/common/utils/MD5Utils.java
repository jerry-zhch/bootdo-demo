package cn.ucmed.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Slf4j
public class MD5Utils {
	private static final String SALT = "1qazxsw2";

	private static final String ALGORITH_NAME = "md5";

	private static final int HASH_ITERATIONS = 2;

	public static String encrypt(String pswd) {
		String newPassword = new SimpleHash(ALGORITH_NAME, pswd, ByteSource.Util.bytes(SALT), HASH_ITERATIONS).toHex();
		return newPassword;
	}

	public static String encrypt(String username, String pswd) {
		String newPassword = new SimpleHash(ALGORITH_NAME, pswd, ByteSource.Util.bytes(username + SALT),
				HASH_ITERATIONS).toHex();
		return newPassword;
	}

	public static String MD5(String data) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] array = md.digest(data.getBytes(StandardCharsets.UTF_8));
			StringBuilder sb = new StringBuilder();
			for (byte item : array) {
				sb.append(Integer.toHexString((item & 0xFF) | 0x100), 1, 3);
			}
			return sb.toString().toUpperCase();
		} catch (Exception e) {
			log.info("md5加密错误"+e);
		}
		return null;
	}

}
