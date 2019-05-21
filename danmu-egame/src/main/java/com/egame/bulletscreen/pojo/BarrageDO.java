package com.egame.bulletscreen.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @program: evtape
 * @description: 弹幕实体
 * @author: sickle
 * @create: 2018-10-19 15:32
 **/
@ToString
@Setter
@Getter
@Document
public class BarrageDO {
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
    private String anchorName;
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
    private String baseId;
    /**
     * @Description: 录制人
     * @Author: sickle
     * @Date: 2018/10/16
     */
    private String tenantId;
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
    /**
     * @Description: 视频id
     * @Author: sickle
     * @Date: 2018/10/25
     */
    private String videoId;
    /**
     * @Description:  创建时间
     * @Author: sickle
     * @Date: 2018/10/25
     */
    private Date createTime;
}