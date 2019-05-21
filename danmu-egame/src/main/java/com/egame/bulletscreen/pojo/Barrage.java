package com.egame.bulletscreen.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @program: evtape
 * @description: 弹幕消息
 * @author: sickle
 * @create: 2018-10-16 16:05
 **/
@ToString
@Setter
@Getter
@Document
public class Barrage {
    /**
     * @Description: 弹幕内容
     * @Author: sickle
     * @Date: 2018/10/16
     */
    private String text;
    /**
     * @Description: 用户姓名
     * @Author: sickle
     * @Date: 2018/10/16
     */
    private String anchor_name;
    /**
     * @Description: 说话的人的名字
     * @Author: sickle
     * @Date: 2018/10/16
     */
    private String talkName;
    /**
     * @Description: 用户id
     * @Author: sickle
     * @Date: 2018/10/16
     */
    private String base_id;
    /**
     * @Description: 录制人
     * @Author: sickle
     * @Date: 2018/10/16
     */
    private String tenant_id;
    /**
     * @Description: 弹幕时间
     * @Author: sickle
     * @Date: 2018/10/16
     */
    private String time;
    /**
     * @Description: 弹幕分钟
     * @Author: sickle
     * @Date: 2018/10/16
     */
    private String minute;
}
