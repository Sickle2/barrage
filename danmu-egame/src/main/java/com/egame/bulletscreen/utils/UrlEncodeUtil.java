package com.egame.bulletscreen.utils;

import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @program: Dy_BulletScreen_Demo
 * @description: Url编码工具
 * @author: zhaoshuai
 * @create: 2018-09-17 15:54
 **/
public class UrlEncodeUtil {
    private static final Logger LOGGER = Logger.getLogger(UrlEncodeUtil.class);

    public static String urlEncode(String url, String data) {
        String realUrl = "";
        try {
            String tem = url + "?_t=" + System.currentTimeMillis() + "&g_tk=&p_tk=&" + URLEncoder.encode(data, "utf-8");
            String a1 = tem.replaceAll("%3A", ":");
            String a2 = a1.replaceAll("%2C", ",");
            String a3 = a2.replaceAll("%26", "&");
            realUrl = a3.replaceAll("%3D", "=");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("url转换失败           UrlEncodeUtil     {}", e);
        }
        return realUrl;
    }
}
