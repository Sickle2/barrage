package com.dy.bulletscreen.repository;

import com.dy.bulletscreen.pojo.Danmu;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: barrage
 * @description:
 * @author: zhaoshuai
 * @create: 2018-10-11 18:22
 **/
@Repository
public interface DanmuRepository extends MongoRepository<Danmu, Long> {
}
