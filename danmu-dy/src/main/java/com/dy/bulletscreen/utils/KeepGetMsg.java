package com.dy.bulletscreen.utils;

import com.dy.bulletscreen.client.DyBulletScreenClient;
import com.dy.bulletscreen.repository.DanmuRepository;

/**
 * @version V1.0
 * @Summary: 获取服务器弹幕信息线程
 * @author: FerroD
 * @date: 2016-3-12
 */
public class KeepGetMsg extends Thread {

    public DanmuRepository danmuRepository;

    public DyBulletScreenClient danmuClient;

    public KeepGetMsg(DanmuRepository danmuRepository, DyBulletScreenClient danmuClient) {
        this.danmuRepository = danmuRepository;
        this.danmuClient = danmuClient;
    }

    @Override
    public void run() {
        ////获取弹幕客户端
//        DyBulletScreenClient danmuClient = DyBulletScreenClient.getInstance();

        //判断客户端就绪状态
        while (danmuClient.getReadyFlag()) {
            //获取服务器发送的弹幕信息
            danmuClient.getServerMsg();
            ;
        }
    }
}
