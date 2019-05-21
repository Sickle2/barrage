package com.dy.bulletscreen.repository;

import com.dy.bulletscreen.pojo.BilibiliDO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @program: barrage
 * @description:
 * @author: zhaoshuai
 * @create: 2018-10-11 18:22
 **/
@Repository
public interface BilibiliRepository extends MongoRepository<BilibiliDO, Long> {
}
