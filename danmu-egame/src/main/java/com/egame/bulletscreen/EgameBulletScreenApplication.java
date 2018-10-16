package com.egame.bulletscreen;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.egame.bulletscreen.client.EgameScreenClient;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

/**
 * @program: Dy_BulletScreen_Demo
 * @description: 企鹅电竞弹幕爬虫
 * @author: zhaoshuai
 * @create: 2018-09-17 14:33
 **/
@SpringBootApplication
public class EgameBulletScreenApplication implements CommandLineRunner {

    public static final Logger LOGGER = LoggerFactory.getLogger(EgameBulletScreenApplication.class);

    @Autowired
    public DanmuRepository danmuRepository;

    public static void main(String[] args) {
        SpringApplication.run(EgameBulletScreenApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        int anchor_id = 0;
        for (String arg : strings) {
            anchor_id = Integer.valueOf(arg);
        }

        if (anchor_id == 0) {
            LOGGER.error("参数错误，录制任务有误！！！！！！");
        } else {
            EgameScreenClient.getBullet(anchor_id, danmuRepository);
        }
    }
}
