package com.dy.bulletscreen.client;

import com.alibaba.fastjson.JSONObject;
import com.dy.bulletscreen.constents.GlobalConstant;
import com.dy.bulletscreen.msg.WSUserInfo;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: barrage
 * @description: 虎牙弹幕
 * @author: sickle
 * @create: 2018-11-07 11:04
 **/
public class HuyaBulletScreenClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(HuyaBulletScreenClient.class);
    String roomId = "";

    //socket相关配置
    private Socket sock;
    private BufferedOutputStream bos;
    private BufferedInputStream bis;

    //获取弹幕线程及心跳线程运行和停止标记
    private boolean readyFlag = false;


    String agent = "";

    /**
     * 连接弹幕服务器
     */
    private void connectServer() {
        try {
            //获取弹幕服务器访问host
            String host = InetAddress.getByName(GlobalConstant.HUYA).getHostAddress();
            //建立socke连接
            System.out.println(host);
            sock = new Socket(host, 8080);
            //设置socket输入及输出
            bos = new BufferedOutputStream(sock.getOutputStream());
            bis = new BufferedInputStream(sock.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

        LOGGER.info("Server Connect Successfully!");
    }

    private void loginRoom(Map<String, String> map) {
        try {
            WSUserInfo userInfo = new WSUserInfo();
            userInfo.setLUid(Integer.valueOf(map.get("yyuid")));
            userInfo.setBAnonymous(true);
            userInfo.setSGuid("");
            userInfo.setSToken("");
            userInfo.setLTid(Integer.valueOf(map.get("topsid")));
//            System.out.println(Integer.valueOf(map.get("subsid")));
            userInfo.setLSid(new BigInteger(map.get("subsid")));
            userInfo.setLGroupId(Integer.valueOf(map.get("yyuid")));
            userInfo.setLGroupType(3);
            String string = JSONObject.toJSONString(userInfo);
            bos.write(string.getBytes(), 0, string.getBytes().length);
            bos.flush();

            //初始化弹幕服务器返回值读取包大小
            byte[] recvByte = new byte[GlobalConstant.MAX_BUFFER_LENGTH];
            //获取弹幕服务器返回值
            bis.read(recvByte, 0, recvByte.length);
//            bos.write();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Map<String, String> getParameters() {
        CloseableHttpClient httpCilent = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000)   //设置连接超时时间
                .setConnectionRequestTimeout(5000) // 设置请求超时时间
                .setSocketTimeout(5000)
                .setRedirectsEnabled(true)//默认允许自动重定向
                .build();
        Map<String, String> map = new HashMap<>();
        try {
            String realUrl = "https://www.huya.com/660069";
            HttpGet httpGet = new HttpGet(realUrl);

            httpGet.setConfig(requestConfig);
            String strResult = "";

            HttpResponse httpResponse = httpCilent.execute(httpGet);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                strResult = EntityUtils.toString(httpResponse.getEntity());
                String regex = "\"sid\":\"[0-9]*\",\"channel\":\"[0-9]*\"";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(strResult);
                String subsid = "";
                String topsid = "";
                if (matcher.find()) {
                    String[] sub = matcher.group(0).split("\"");
                    subsid = sub[3];
                    topsid = sub[7];
                    map.put("subsid", subsid);
                    map.put("topsid", topsid);
                }
                String regex1 = "data-source=\"[0-9]*\"";
                Pattern pattern1 = Pattern.compile(regex1);
                Matcher matcher1 = pattern1.matcher(strResult);
                String yyuid = "";
                if (matcher1.find()) {
                    String[] sub = matcher1.group(0).split("\"");
                    yyuid = sub[1];
                    map.put("yyuid", yyuid);
                }

            }
        } catch (Exception e) {
            LOGGER.error("请求虎牙参数出错  {}", e);
        } finally {
            try {
                httpCilent.close();
            } catch (IOException e) {
                LOGGER.error("腾讯爬虫出现错误              {}", e);
            }
        }
        return map;
    }

    public static void main(String[] args) {
        HuyaBulletScreenClient hu = new HuyaBulletScreenClient();
        Map<String, String> map = hu.getParameters();
        hu.connectServer();
        hu.loginRoom(map);
        map.forEach((s, s2) -> System.out.println(s + "--------" + s2));

    }
}