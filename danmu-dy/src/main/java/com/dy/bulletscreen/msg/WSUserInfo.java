package com.dy.bulletscreen.msg;

import lombok.Data;

import java.math.BigInteger;

/**
 * @program: barrage
 * @description:
 * @author: sickle
 * @create: 2018-11-16 11:22
 **/
@Data
public class WSUserInfo {
    private Integer lUid = 0;
    private boolean bAnonymous = true;
    private String sGuid = "";
    private String sToken = "";
    private Integer lTid = 0;
    private BigInteger lSid ;
    private Integer lGroupId = 0;
    private Integer lGroupType = 3;
}
