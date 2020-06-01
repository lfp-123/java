package com.seecen.ssm.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	
	public static void main(String[] args) {
		System.out.println(new MD5().MD5Code("123"));
	}

	public static String MD5Code(String content) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] byteArray = content.getBytes("UTF-8");
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(byteArray);
			byte[] digest = messageDigest.digest();
			int length = digest.length;
			char str[] = new char[length * 2];
			int k = 0;
			for (int i = 0; i < length; i++) {
				byte mData = digest[i];
				str[k++] = hexDigits[mData >>> 4 & 0xf];
				str[k++] = hexDigits[mData & 0xf];
			}
			return new String(str);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("不支持hash算法：MD5",e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("不支持UTF-8编码",e);
		}
	}
	
	public  String MD5Code(byte[] byteArray) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(byteArray);
			byte[] digest = messageDigest.digest();
			int length = digest.length;
			char str[] = new char[length * 2];
			int k = 0;
			for (int i = 0; i < length; i++) {
				byte mData = digest[i];
				str[k++] = hexDigits[mData >>> 4 & 0xf];
				str[k++] = hexDigits[mData & 0xf];
			}
			return new String(str);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("不支持hash算法：MD5",e);
		}
	}
	
}
