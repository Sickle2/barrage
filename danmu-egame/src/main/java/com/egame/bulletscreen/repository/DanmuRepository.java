package com.egame.bulletscreen.repository;

import com.egame.bulletscreen.pojo.Barrage;
import com.egame.bulletscreen.pojo.BarrageDO;
import com.egame.bulletscreen.pojo.Danmu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DanmuRepository extends MongoRepository<BarrageDO, Long> {


}