package com.egame.bulletscreen;

import com.egame.bulletscreen.Dao.DammuDAO;
import com.egame.bulletscreen.pojo.BarrageDO;
import com.egame.bulletscreen.repository.DanmuRepository;
import com.mongodb.WriteResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @program: Dy_BulletScreen_Demo
 * @description: 企鹅电竞弹幕爬虫
 * @author: zhaoshuai
 * @create: 2018-09-17 14:33
 **/
@SpringBootApplication
public class EgameBulletScreenApplication implements CommandLineRunner {

    public static final Logger LOGGER = LoggerFactory.getLogger(EgameBulletScreenApplication.class);

    @Autowired
    public DammuDAO dammuDAO;

    @Autowired
    public DanmuRepository danmuRepository;
    @Autowired
    public MongoTemplate mongoTemplate;

    public static void main(String[] args) {
        SpringApplication.run(EgameBulletScreenApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
//        int anchor_id = 298303186;
//        EgameScreenClient egameScreenClient = new EgameScreenClient();
//        for (String arg : strings) {
//            anchor_id = Integer.valueOf(arg);
//        }
//
//        if (anchor_id == 0) {
//            LOGGER.error("参数错误，录制任务有误！！！！！！");
//        } else {
//            egameScreenClient.getBullet(anchor_id, dammuDAO);
//        }
//
//
//        Thread.sleep(20000);
//        egameScreenClient.setFlag(false);

//        List<Danmu> danmuList = dammuDAO.findAll();
//        danmuList.forEach(s -> System.out.println(s));
//        List<Danmu> danmuList = dammuDAO.findDanmusBetweenTime("1540191095357", "1540194772420");
//        List<Danmu> danmuList = dammuDAO.findDanmusBetweenTime("1540191099066", "1540191111165");
//        List<BarrageDO> list = dammuDAO.select("A2_D201810251708_NALky");
        BarrageDO barrageDO = new BarrageDO();
        barrageDO.setBaseId("111111");
        barrageDO.setAnchorName("11111");
        danmuRepository.save(barrageDO);
//        list.forEach(Barrage -> System.out.println(Barrage));
        List<BarrageDO> da = danmuRepository.findAll();
//        da.forEach(barrageDO -> System.out.println(barrageDO));
        da.forEach(barrageDO1 -> System.out.println(barrageDO1));
//
//        int strat = 25672857;
//        int end = 25672859;
//
//
//        Date date = new Date();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        System.out.println(dateFormat.format(date));
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//        calendar.add(Calendar.DATE, -7);
//        System.out.println(calendar.getTime());
//
//        LocalDateTime localDateTime = LocalDateTime.of(2018, 11, 1, 00, 00);
//        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("Asia/Shanghai"));
//        Query query = new Query(Criteria.where("createTime").lt(calendar.getTime()));
//        Query query1 = new Query(Criteria.where("time").lt("1541739878346"));
//        query1.limit(10);
//        query.limit(10);
////        List<BarrageDO> list = mongoTemplate.find(query, BarrageDO.class);
//        List<BarrageDO> list = mongoTemplate.find(query, BarrageDO.class);
//        WriteResult writeResult= mongoTemplate.remove(query, BarrageDO.class);
//        System.out.println(writeResult.getN());
//        list.forEach(barrageDO -> System.out.println(barrageDO));
//        int startTime = (int) (1540361279410L / 1000 / 60);
//        List<Barrage> list = dammuDAO.countBarrageByMinute(String.valueOf(25672688));
//        list.forEach(barrage -> System.out.println(barrage));
//        Date date = new Date();
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//        calendar.add(Calendar.DATE, -7);
//        System.out.println(date);
//        System.out.println(calendar.getTime());
//        Query query = new Query(Criteria.where("createTime").lt(calendar.getTime()));
//        List<BarrageDO> list = mongoTemplate.find(query, BarrageDO.class);
//        list.forEach(barrageDO -> System.out.println(barrageDO));
//        String URL = "jdbc:mysql://cd-cdb-fbdpzybo.sql.tencentcdb.com:63875/evtape_egame?autoReconnect=true&useUnicode=true&characterEncoding=utf-8&cachePrepStmts=true&useServerPrepStmts=true&prepStmtCacheSize=250&prepStmtCacheSqlLimit=2048&useSSL=false";
//        String USER = "root";
//        String PASSWORD = "Evtape123";
//        //1.加载驱动程序
//        Class.forName("com.mysql.jdbc.Driver");
//        //2.获得数据库链接
//        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
//        //3.通过数据库的连接操作数据库，实现增删改查（使用Statement类）
//        Statement st = conn.createStatement();
//        ResultSet rs = st.executeQuery("select * from anchor_base");
//        //4.处理数据库的返回结果(使用ResultSet类)
//        while (rs.next()) {
//            if (rs.getString("live_platform").contains("企鹅") || rs.getString("live_platform").contains("斗鱼")) {
//                System.out.println(rs.getString("live_room_url"));
//                String[] str = rs.getString("live_room_url").split("/");
//                String sql = "update anchor_base set room_id =" + +"";
//                st.execute(sql);
//            }
//        }
//        //关闭资源
//        rs.close();
//        st.close();
//        conn.close();

    }
}
