package com.haoxw.chuanmei.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidateUtil {
	private static final Logger logger = LoggerFactory
	.getLogger(ValidateUtil.class);
	/**
	 * 手机验证
	 * 
	 * @param validateStr
	 * @return
	 */
	public static boolean isPhone(String validateStr) {
		if (StringUtils.isEmpty(validateStr)) {
			return false;
		}
		String regex = "^((13[0-9])|(15[012356789])|(18[0-9]))\\d{8}$";// 请根据实际修改
		return match(regex, validateStr);
	}

	/**
	 * 正则验证方法
	 * 
	 * @param regexstr
	 * @param str
	 * @return
	 */
	public static boolean match(String regexstr, String str) {
		Pattern regex = Pattern.compile(regexstr, Pattern.CASE_INSENSITIVE
				| Pattern.DOTALL);
		Matcher matcher = regex.matcher(str);
		return matcher.matches();
	}
	
	/**
     * 必须都不为空
     * @param args
     * @return
     */
	public static boolean validParam(Object... args){
    	for(Object s:args){
    		if(s==null||StringUtils.isEmpty(s.toString())){
    			return false;
    		}
    	}
    	return true;
    }
	 /**
     * byte数组转换成16进制字符串
     * 
     * @param src
     * @return
     */
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
	 /**
     * 根据文件流读取图片文件真实类型
     * 
     * @param is
     * @return 
     */
    public static String getTypeByStream(InputStream is) {
        byte[] b = new byte[4];
        try {
            is.read(b, 0, b.length);
        } catch (IOException e) {
            logger.error("", e);
            return null;
        }
        String type = bytesToHexString(b);
        //非法io
        if (StringUtils.isEmpty(type)) {
            return null;
        }
        type = type.toUpperCase();
        //jpg
        if (type.contains("FFD8FF")) {
            return "jpg";
        }
        //png
        else if (type.contains("89504E47")) {
            return "png";
        }
        //gif
        else if (type.contains("47494638")) {
            return "gif";
        }
        //bmp
        else if (type.contains("424D")) {
            return "bmp";
        } else {
            return null;
        }
    }

}
