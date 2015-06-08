package com.utils;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
                baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
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

    /**
     * 将十六进制字符数组转换为字节数组
     * 
     * @param data
     *            十六进制char[]
     * @return byte[]
     * @throws RuntimeException
     *             如果源十六进制字符数组是一个奇怪的长度，将抛出运行时异常
     */
    public static byte[] hexStringtoBytes(String str) {
        char[] data = str.toCharArray();
        int len = data.length;
        if ((len & 0x01) != 0) {
            throw new RuntimeException("Odd number of characters.");
        }
        byte[] out = new byte[len >> 1];
        // two characters form the hex value.
        for (int i = 0, j = 0; j < len; i++) {
            int f = toDigit(data[j], j) << 4;
            j++;
            f = f | toDigit(data[j], j);
            j++;
            out[i] = (byte) (f & 0xFF);
        }
        return out;
    }

    /**
     * 将十六进制字符转换成一个整数
     * 
     * @param ch
     *            十六进制char
     * @param index
     *            十六进制字符在字符数组中的位置
     * @return 一个整数
     * @throws RuntimeException
     *             当ch不是一个合法的十六进制字符时，抛出运行时异常
     */
    protected static int toDigit(char ch, int index) {
        int digit = Character.digit(ch, 16);
        if (digit == -1) {
            throw new RuntimeException("Illegal hexadecimal character " + ch + " at index " + index);
        }
        return digit;
    }

    // byte数组转换成byte集合
    public static ArrayList<Byte> convertArrayToList(byte[] src) {
        ArrayList<Byte> bytes = new ArrayList<Byte>();
        for (int i = 0; i < src.length; i++) {
            bytes.add(src[i]);
        }
        return bytes;
    }

    // byte集合转换成byte数组
    public static byte[] convertListToArray(ArrayList<Byte> src) {
        byte[] bytes = new byte[src.size()];
        for (int i = 0; i < src.size(); i++) {
            bytes[i] = src.get(i);
        }
        return bytes;
    }

    /**
     *高位在前，低位在后
     * @param value
     * @return
     */
    public static byte[] intToBytes(int value) {
        byte[] src = new byte[4];
        src[0] = (byte) ((value >> 24) & 0xFF);
        src[1] = (byte) ((value >> 16) & 0xFF);
        src[2] = (byte) ((value >> 8) & 0xFF);
        src[3] = (byte) (value & 0xFF);
        return src;
    }

    /**
     * 高位在前，低位在后
     * @param src
     * @return
     */
    public static int bytesToInt(byte[] src) {
        int value;
        value = (int) (((src[0] & 0xFF) << 24) | ((src[1] & 0xFF) << 16) | ((src[2] & 0xFF) << 8) | (src[3] & 0xFF));
        return value;
    }

    public static String DateToString(String formatter){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(formatter);
        return sdf.format(date);
    }

    public static Date StringToDate(String timeStr,String formatter){
        String nowtime = new String(timeStr);
        SimpleDateFormat sdf = new SimpleDateFormat(formatter);
        try {
            Date date = sdf.parse(nowtime);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
