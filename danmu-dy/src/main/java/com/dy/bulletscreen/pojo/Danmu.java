package com.dy.bulletscreen.pojo;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @program: barrage
 * @description: 弹幕实体
 * @author: zhaoshuai
 * @create: 2018-10-11 16:20
 **/
@ToString
@Getter
@Setter
@Document
public class Danmu {
    //弹幕内容
    private String text;
    //用户姓名
    private String anchor_name;
    //说话的人的名字
    private String talkName;
    //用户id
    private String base_id;
    //录制人
    private String tenant_id;
    //弹幕时间
    private String time;
}
