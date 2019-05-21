package com.egame.bulletscreen.client;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Arrays;

/**
 * @program: Dy_BulletScreen_Demo
 * @description: mogodbTest
 * @author: zhaoshuai
 * @create: 2018-10-10 15:23
 **/
public class mogodbTest {
    public static void main(String args[]) {
        try {
            // 连接到 mongodb 服务

            MongoCredential credential = MongoCredential.createScramSha1Credential("daifei", "daifei", "evtape".toCharArray());

            MongoClient mongoClient = new MongoClient(new ServerAddress("118.24.103.124", 27017), Arrays.asList(credential));
            // 连接到数据库
            MongoDatabase mongoDatabase = mongoClient.getDatabase("daifei");
            System.out.println("数据库连接成功！");
            MongoCollection<Document> collection = mongoDatabase.getCollection("daifei");
            System.out.println("集合 test 选择成功");
            //插入文档
            /**
             * 1. 创建文档 org.bson.Document 参数为key-value的格式
             * 2. 创建文档集合List<Document>
             * 3. 将文档集合插入数据库集合中 mongoCollection.insertMany(List<Document>) 插入单个文档可以用 mongoCollection.insertOne(Document)
             * */
//            Document document = new Document("title", "MongoDB").
//                    append("description", "database").
//                    append("likes", 100).
//                    append("by", "Fly");
//            List<Document> documents = new ArrayList<Document>();
//
//            documents.add(document);
//            for (String name : mongoDatabase.listCollectionNames()) {
//                System.out.println(name);
//            }
//
//            collection.insertMany(documents);
//            System.out.println("文档插入成功");
            for (Document document:collection.find()){
                System.out.println(document.toJson());
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}
