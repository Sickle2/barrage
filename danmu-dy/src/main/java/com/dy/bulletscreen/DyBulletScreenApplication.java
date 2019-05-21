package com.dy.bulletscreen;

import com.dy.bulletscreen.client.DyBulletScreenClient;
import com.dy.bulletscreen.pojo.BilibiliDO;
import com.dy.bulletscreen.repository.BilibiliRepository;
import com.dy.bulletscreen.repository.DanmuRepository;
import com.dy.bulletscreen.utils.KeepAlive;
import com.dy.bulletscreen.utils.KeepGetMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @version V1.0
 * @summary: 弹幕Demo程序启动类
 * @author: FerroD
 * @date: 2016-3-12
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.dy"})
public class DyBulletScreenApplication implements CommandLineRunner {

    @Autowired
    public DanmuRepository danmuRepository;
    @Autowired
    public DyBulletScreenClient danmuClient;
    //设置需要访问的房间ID信息
    private static final int roomId = 288016;

    //弹幕池分组号，海量模式使用-9999
    private static final int groupId = -9999;

    public static void main(String[] args) {
        SpringApplication.run(DyBulletScreenApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        //初始化弹幕Client
//        DyBulletScreenClient danmuClient = DyBulletScreenClient.getInstance();
        //设置需要连接和访问的房间ID，以及弹幕池分组号
        danmuClient.init(roomId, groupId);

        //保持弹幕服务器心跳
        KeepAlive keepAlive = new KeepAlive(danmuRepository,danmuClient);
        keepAlive.start();

        //获取弹幕服务器发送的所有信息
        KeepGetMsg keepGetMsg = new KeepGetMsg(danmuRepository,danmuClient);
        keepGetMsg.start();

    }
}