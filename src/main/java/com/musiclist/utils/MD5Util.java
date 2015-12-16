package com.musiclist.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;

/**  
 * @author ZJK
 * @date 2015年12月14日 下午4:11:11
 */
public class MD5Util {
    
    private static final char HEX_DIGITS[] = { '0', '1', '2', '3', '4', '5',
        '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
    
    /*** 
     * MD5加码 生成32位md5码 
     */  
    public static String string2MD5(String inStr){  
        MessageDigest md5 = null;  
        try{  
            md5 = MessageDigest.getInstance("MD5");  
        }catch (Exception e){  
            return "";  
        }  
        char[] charArray = inStr.toCharArray();  
        byte[] byteArray = new byte[charArray.length];  
  
        for (int i = 0; i < charArray.length; i++)  
            byteArray[i] = (byte) charArray[i];  
        byte[] md5Bytes = md5.digest(byteArray);  
        StringBuffer hexValue = new StringBuffer();  
        for (int i = 0; i < md5Bytes.length; i++){  
            int val = ((int) md5Bytes[i]) & 0xff;  
            if (val < 16)  
                hexValue.append("0");  
            hexValue.append(Integer.toHexString(val));  
        }  
        return hexValue.toString();  
    }  
  
    /**
     *  获取文件的MD5值
     * @param file 目标文件
     * @return MD5字符串
     */
    public static String getFileMD5String(byte[] bytes) {
        String ret = "";
        try {
            MessageDigest md5 = null;  
            md5 = MessageDigest.getInstance("MD5");  
            md5.update(bytes);
            ret = bytesToHex(md5.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
    
    public static String bytesToHex(byte bytes[]) {
        return bytesToHex(bytes, 0, bytes.length);

    }
    public static String bytesToHex(byte bytes[], int start, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < start + end; i++) {
            sb.append(byteToHex(bytes[i]));
        }
        return sb.toString();
    }
    public static String byteToHex(byte bt) {
        return HEX_DIGITS[(bt & 0xf0) >> 4] + "" + HEX_DIGITS[bt & 0xf];
    }
}
