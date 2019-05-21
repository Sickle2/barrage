package com.egame.bulletscreen.Dao;

import com.egame.bulletscreen.pojo.Barrage;
import com.egame.bulletscreen.pojo.BarrageDO;
import com.egame.bulletscreen.pojo.Danmu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: barrage
 * @description: 弹幕DO
 * @author: sickle
 * @create: 2018-10-22 15:15
 **/
public interface DammuDAO {
    public void save(Danmu danmu);

    public List<Danmu> findUserByUserName(String userName);

    public List<Danmu> findDanmuByTime(String time);

    public List<Danmu> findDanmusBetweenTime(String start, String end);

    public List<Danmu> findAll();

    public List<Barrage> countBarrageByMinute(String minute);

    public List<BarrageDO> select(String minute);
}
