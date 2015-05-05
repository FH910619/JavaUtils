package com.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/*
 * 工具类，做字符串处理
 */
public class ChatUtils {

	// byte数组转为十六进制字符
	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0)
			return null;
		for (byte element : src) {
			int v = element & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2)
				stringBuilder.append(0);
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	// 字符串转十六进制编码
	public static String toHexString(String s) {
		String str = "";
		for (int i = 0; i < s.length(); i++) {
			int ch = s.charAt(i);
			String s4 = Integer.toHexString(ch);
			str = str + s4;
		}
		return str;
	}

	// 十六进制编码为字符串
	public static String toStringHex(String s) {
		byte[] baKeyword = new byte[s.length() / 2];
		for (int i = 0; i < baKeyword.length; i++)
			try {
				baKeyword[i] = (byte) (0xff & Integer.parseInt(
						s.substring(i * 2, i * 2 + 2), 16));
			} catch (Exception e) {
				e.printStackTrace();
			}

		try {
			s = new String(baKeyword, "GBK");// UTF-16le:Not
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return s;
	}

	// byte数组转字符串
	public static String tobyteString(byte[] src) {
		if (src == null || src.length <= 0)
			return null;

		try {
			return new String(src, "GBK");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//byte数组转换成byte集合
	public static ArrayList<Byte> convertArrayToList(byte[] src){
		ArrayList<Byte> bytes = new ArrayList<Byte>();
		for(int i=0;i<src.length;i++){
			bytes.add(src[i]);
		}
		return bytes;
	}
	
	//byte集合转换成byte数组
	public static byte[] convertListToArray(ArrayList<Byte> src){
		byte[] bytes = new byte[src.size()];
		for(int i=0;i<src.size();i++){
			bytes[i] = src.get(i);
		}
		return bytes;
	}
}
