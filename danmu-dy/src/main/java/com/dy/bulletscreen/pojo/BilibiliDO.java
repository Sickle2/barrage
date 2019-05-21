package com.dy.bulletscreen.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @program: evtape
 * @description:
 * @author: sickle
 * @create: 2019-05-21 10:35
 **/
@ToString
@Setter
@Getter
@Document
public class BilibiliDO {
    private String baseId;
    private String url;
    private String anchorName;
}
