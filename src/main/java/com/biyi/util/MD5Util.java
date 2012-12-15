package com.biyi.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
	
	private static String md5(String password) throws NoSuchAlgorithmException{
		MessageDigest digest = MessageDigest.getInstance("md5");
		digest.update(password.getBytes());
		byte[] bytes = digest.digest();
		return bytesToHex(bytes);
	}
	
	public static String password(String password){
		try {
			password = md5(password);
			password += "&#%@$^%@#$()%^$!@#~~%uuonhgm;.yoooo'";
			return md5(password);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("加密失败", e);
		}
	}
	
	private static String bytesToHex(byte bytes[]){  
		return bytesToHex(bytes, 0, bytes.length);  
    }
	
	private static String bytesToHex(byte bytes[], int start, int end){
		StringBuilder sb = new StringBuilder();
		for(int i = start; i < start + end; i++){  
			sb.append(byteToHex(bytes[i]));
		}
		return sb.toString();
    }
	
	private static final char HEX_DIGITS[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
	
	private static String byteToHex(byte bt){
		return HEX_DIGITS[(bt & 0xf0) >> 4] + "" + HEX_DIGITS[bt & 0xf];
    }
	
	public static void main(String[] args) {
		System.out.println(password("78524163"));
	}

}
