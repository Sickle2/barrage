package com.egame.bulletscreen.repository;

import com.egame.bulletscreen.pojo.Danmu;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @program: barrage
 * @description:
 * @author: zhaoshuai
 * @create: 2018-10-11 18:22
 **/
@Repository
public interface DanmuRepository extends MongoRepository<Danmu, Long> {
}
