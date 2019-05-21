package com.egame.bulletscreen.Dao.DAOimpl;

import com.egame.bulletscreen.Dao.DammuDAO;
import com.egame.bulletscreen.pojo.Barrage;
import com.egame.bulletscreen.pojo.BarrageDO;
import com.egame.bulletscreen.pojo.Danmu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: barrage
 * @description: impl
 * @author: sickle
 * @create: 2018-10-22 15:16
 **/
@Component
public class DanmuDAOimpl implements DammuDAO {

    @Autowired
    public MongoTemplate mongoTemplate;

    @Override
    public void save(Danmu danmu) {
        mongoTemplate.save(danmu);
    }

    /**
     * 根据用户名查询对象
     *
     * @param base_id
     * @return
     */
    @Override
    public List<Danmu> findUserByUserName(String base_id) {
        Query query = new Query(Criteria.where("base_id").is(base_id));
        List<Danmu> user = mongoTemplate.findAll(Danmu.class);
        return user;
    }

    @Override
    public List<Danmu> findDanmuByTime(String time) {
        Query query = new Query(Criteria.where("time").is(time));
        List<Danmu> danmus = mongoTemplate.find(query, Danmu.class);

        return danmus;
    }

    @Override
    public List<Danmu> findDanmusBetweenTime(String start, String end) {
        Query query = new Query(Criteria.where("time").gte(start).lte(end));
        List<Danmu> danmuList = mongoTemplate.find(query, Danmu.class);
        return danmuList;
    }

    @Override
    public List<Danmu> findAll() {
        List<Danmu> danmuList = mongoTemplate.findAll(Danmu.class);
        return danmuList;
    }

    @Override
    public List<Barrage> countBarrageByMinute(String minute) {
        Query query = new Query(Criteria.where("minute").is(minute));
        List<Barrage> list = mongoTemplate.find(query, Barrage.class);
        return list;
    }

    @Override
    public List<BarrageDO> select(String minute) {
        Query query = new Query(Criteria.where("text").regex("66").and("videoId").is(minute));
        List<BarrageDO> list = mongoTemplate.find(query, BarrageDO.class);
        return list;
    }


}
