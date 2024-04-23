package com.webspringmvc.helps;

import java.security.MessageDigest;

import org.springframework.util.Base64Utils;

public class Bcrypt {
	public static String toSHA1(String str, String salt) {
		str = str + salt;
		String result = "";
		try {
			byte[] dataBytes = str.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			result = Base64Utils.encodeToString(md.digest(dataBytes));
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
