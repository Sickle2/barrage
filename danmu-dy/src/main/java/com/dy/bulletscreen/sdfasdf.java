package com.dy.bulletscreen;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @program: barrage
 * @description:
 * @author: sickle
 * @create: 2018-11-08 15:35
 **/
public class sdfasdf {
    private static final Logger LOGGER = LoggerFactory.getLogger(sdfasdf.class);


    public static void main(String[] args) {
        try {
            CloseableHttpClient httpCilent = HttpClients.createDefault();
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(5000)   //设置连接超时时间
                    .setConnectionRequestTimeout(5000) // 设置请求超时时间
                    .setSocketTimeout(5000)
                    .setRedirectsEnabled(true)//默认允许自动重定向
                    .build();
            String ss = "http://pity004dc.bkt.clouddn.com/A3_181203130527_181203130629_f2e640a28a2942f3b4be19671871aa5f.mp4?avinfo";
            HttpGet httpGet = new HttpGet(ss);
            httpGet.setConfig(requestConfig);
            String strResult = "";

            HttpResponse httpResponse = httpCilent.execute(httpGet);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                strResult = EntityUtils.toString(httpResponse.getEntity());
                System.out.println(strResult);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
