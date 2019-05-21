package com.egame.bulletscreen;

import javax.xml.crypto.Data;

/**
 * @program: barrage
 * @description: sdaf
 * @author: sickle
 * @create: 2018-10-23 13:31
 **/
public class sdf {
    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        int time = (int) (System.currentTimeMillis() / 1000 / 60);
//        time - 24 * 60
        System.out.println(time);
        System.out.println(time - 24 * 60);
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis());
        int time1 = (int) (System.currentTimeMillis() / 1000 / 60);
        System.out.println(time1);
        System.out.println(time1 - 24 * 60);
        for (int i = 24 * 60 - 3 * 60; i < 24 * 60; i++) {
//            AceInfoDO a = new AceInfoDO();
//            a.setBaseId(anchor.getId());
//            a.setStartMinute(time - 24 * 60 + i);
//            a.setTotalIncome(Double.parseDouble(incomes[i]));
//            a.setTotalSubtitle(Double.parseDouble(subtitles[i]));
//            result.add(a);
        }
    }
}
