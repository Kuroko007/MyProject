package com.cmsk.wxplatform.common.utils;

import java.security.MessageDigest;
import java.util.Arrays;

/**
 * SHA1加密工具类
 */
public class SHA1Util {

        public static final String TOKEN = "CMSKZHANG002";
    public static String sha1(String s) {
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            byte[] btInput = s.getBytes();
            // 获得SHA1摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("sha-1");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        String timestamp = "1616659061";
        String nonce = "12254112";
        String[] arr = {timestamp,nonce, SHA1Util.TOKEN};
        Arrays.sort(arr);
        StringBuilder sb = new StringBuilder();
        for (String temp : arr) {
            sb.append(temp);
        }
        String s = SHA1Util.sha1(sb.toString());
        System.out.println(s);
    }
}
