package com.demo.basedemo.utils;

import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	public static final String REG_PHONE="^1[3|4|5|7|8]\\d{9}$";
	public static final String REG_PASS="^.{6,16}$";
	
	public static String string2MD5(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}

	public static boolean regExCompile(String str, String regEx) {
		boolean flag = false;
		if (str != null && regEx != null) {
			Pattern pat = Pattern.compile(regEx);
			Matcher mat = pat.matcher(str);
			flag = mat.find();
		}
		return flag;
	}
	
	public static String analyseMoney(long money){
		if(money>=100){
			StringBuilder builder=new StringBuilder();
			builder.append(money+"").insert((money+"").length()-2, ".");
			return builder.toString();
		}else if(money<100&&money>=10){
			return "0."+money;
		}else{
			return "0.0"+money;
		}
	}
	
	public static boolean regPhone(String phone){
		return regExCompile(phone, REG_PHONE);
	}
	public static boolean regPass(String pass){
		return regExCompile(pass, REG_PASS);
	}
}
