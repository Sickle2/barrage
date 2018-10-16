package com.egame.bulletscreen.client;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.egame.bulletscreen.constents.GlobalConstant;
import com.egame.bulletscreen.pojo.Danmu;
import com.egame.bulletscreen.repository.DanmuRepository;
import com.egame.bulletscreen.utils.UrlEncodeUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @program: barrage
 * @description: 腾讯弹幕处理
 * @author: zhaoshuai
 * @create: 2018-10-16 13:56
 **/
public class EgameScreenClient {

    public static final Logger LOGGER = LoggerFactory.getLogger(EgameScreenClient.class);

    /**
     * @Description: 获取弹幕存入mongodb
     * @Param: anchor_id
     * @return:
     * @Author: sickle
     * @Date: 2018/10/16
     */
    public static void getBullet(Integer anchor_id, DanmuRepository danmuRepository) {
        String vid = anchor_id + "_1539131261";

        String data = "param={\"key\":{\"module\":\"pgg_live_barrage_svr\",\"method\":\"get_barrage\",\"param\":{\"anchor_id\":" + anchor_id + ",\"vid\":\"" + vid + "\",\"scenes\":4096,\"last_tm\":1537163282}}}&app_info={\"platform\":4,\"terminal_type\":2,\"egame_id\":\"egame_official\",\"version_code\":\"9.9.9.9\",\"version_name\":\"9.9.9.9\"}&tt=1";

        CloseableHttpClient httpCilent = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000)   //设置连接超时时间
                .setConnectionRequestTimeout(5000) // 设置请求超时时间
                .setSocketTimeout(5000)
                .setRedirectsEnabled(true)//默认允许自动重定向
                .build();
        try {
            String realUrl = UrlEncodeUtil.urlEncode(GlobalConstant.HOME_URL, data);
            while (true) {
                HttpGet httpGet = new HttpGet(realUrl);
                httpGet.setConfig(requestConfig);
                String strResult = "";

                HttpResponse httpResponse = httpCilent.execute(httpGet);
                if (httpResponse.getStatusLine().getStatusCode() == 200) {
                    //获得返回的结果
                    strResult = EntityUtils.toString(httpResponse.getEntity());
                    LOGGER.info("弹幕结果        {}", strResult);
                    JSONObject jsonObject = JSONObject.parseObject(strResult);
                    JSONObject data1 = jsonObject.getJSONObject("data");
                    JSONObject data2 = data1.getJSONObject("key");
                    JSONObject data3 = data2.getJSONObject("retBody");
                    JSONObject data4 = data3.getJSONObject("data");
                    String new_pid = String.valueOf(data4.get("new_pid"));
                    if (StringUtils.isBlank(new_pid)) {
                        JSONArray array = data4.getJSONArray("msg_list");
                        for (int i = 0; i < array.size(); i++) {
                            String da = array.get(i).toString();
                            JSONObject object = JSONObject.parseObject(da);
                            Object type = object.get("type");
                            if (type.equals(0) || type.equals(3) || type.equals(9)) {
                                Danmu danmu = new Danmu();
                                danmu.setBase_id(String.valueOf(anchor_id));
                                danmu.setTalkName(object.getString("nick"));
                                danmu.setText(object.getString("content"));
                                danmu.setTime(object.getString("tm"));
                                danmuRepository.save(danmu);
                            }
                        }
                    } else {
                        vid = new_pid;
                        data = "param={\"key\":{\"module\":\"pgg_live_barrage_svr\",\"method\":\"get_barrage\",\"param\":{\"anchor_id\":" + anchor_id + ",\"vid\":\"" + vid + "\",\"scenes\":4096,\"last_tm\":1537163282}}}&app_info={\"platform\":4,\"terminal_type\":2,\"egame_id\":\"egame_official\",\"version_code\":\"9.9.9.9\",\"version_name\":\"9.9.9.9\"}&tt=1";
                        realUrl = UrlEncodeUtil.urlEncode(GlobalConstant.HOME_URL, data);
                    }
                } else if (httpResponse.getStatusLine().getStatusCode() == 400) {
                    LOGGER.error("400       请求出现错误！！！！");
                } else if (httpResponse.getStatusLine().getStatusCode() == 500) {
                    LOGGER.error("500       请求出现错误！！！！");
                }
                //经过测试 14s 企鹅电竞不会获取重复数据
                Thread.sleep(13500);
            }
        } catch (IOException e) {
            LOGGER.error("腾讯爬虫出现错误              {}", e);
        } catch (InterruptedException e) {
            LOGGER.error("腾讯爬虫出现错误              {}", e);
        } finally {
            try {
                httpCilent.close();
            } catch (IOException e) {
                LOGGER.error("腾讯爬虫出现错误              {}", e);
            }
        }
    }
}
