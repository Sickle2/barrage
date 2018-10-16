package com.dy.bulletscreen.client;

import com.dy.bulletscreen.constents.GlobalConstant;
import com.dy.bulletscreen.msg.DyMessage;
import com.dy.bulletscreen.msg.MsgView;
import com.dy.bulletscreen.pojo.Danmu;
import com.dy.bulletscreen.repository.DanmuRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Map;
import java.util.Optional;

/**
 * @version V1.0
 * @Summary: 弹幕客户端类
 * @author: FerroD
 * @date: 2016-3-12
 */
public class DyBulletScreenClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(DyBulletScreenClient.class);

    private static DyBulletScreenClient instance;


    //socket相关配置
    private Socket sock;
    private BufferedOutputStream bos;
    private BufferedInputStream bis;

    //获取弹幕线程及心跳线程运行和停止标记
    private boolean readyFlag = false;

    private DyBulletScreenClient() {
    }

    /**
     * 单例获取方法，客户端单例模式访问
     *
     * @return
     */
    public static DyBulletScreenClient getInstance() {
        if (null == instance) {
            instance = new DyBulletScreenClient();
        }
        return instance;
    }

    /**
     * 客户端初始化，连接弹幕服务器并登陆房间及弹幕池
     *
     * @param roomId  房间ID
     * @param groupId 弹幕池分组ID
     */
    public void init(int roomId, int groupId) {
        //连接弹幕服务器
        this.connectServer();
        //登陆指定房间
        this.loginRoom(roomId);
        //加入指定的弹幕池
        this.joinGroup(roomId, groupId);
        //设置客户端就绪标记为就绪状态
        readyFlag = true;
    }

    /**
     * 获取弹幕客户端就绪标记
     *
     * @return
     */
    public boolean getReadyFlag() {
        return readyFlag;
    }

    /**
     * 连接弹幕服务器
     */
    private void connectServer() {
        try {
            //获取弹幕服务器访问host
            String host = InetAddress.getByName(GlobalConstant.HOME_URL).getHostAddress();
            //建立socke连接
            sock = new Socket(host, GlobalConstant.PORT);
            //设置socket输入及输出
            bos = new BufferedOutputStream(sock.getOutputStream());
            bis = new BufferedInputStream(sock.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

        LOGGER.info("Server Connect Successfully!");
    }

    /**
     * 登录指定房间
     *
     * @param roomId
     */
    private void loginRoom(int roomId) {
        //获取弹幕服务器登陆请求数据包
        byte[] loginRequestData = DyMessage.getLoginRequestData(roomId);


        try {
            //发送登陆请求数据包给弹幕服务器
            bos.write(loginRequestData, 0, loginRequestData.length);
            bos.flush();

            //初始化弹幕服务器返回值读取包大小
            byte[] recvByte = new byte[GlobalConstant.MAX_BUFFER_LENGTH];
            //获取弹幕服务器返回值
            bis.read(recvByte, 0, recvByte.length);

            //解析服务器返回的登录信息
            if (DyMessage.parseLoginRespond(recvByte)) {
                LOGGER.info("Receive login response successfully!");
            } else {
                LOGGER.error("Receive login response failed!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加入弹幕分组池
     *
     * @param roomId
     * @param groupId
     */
    private void joinGroup(int roomId, int groupId) {
        //获取弹幕服务器加弹幕池请求数据包
        byte[] joinGroupRequest = DyMessage.getJoinGroupRequest(roomId, groupId);

        try {
            //想弹幕服务器发送加入弹幕池请求数据
            bos.write(joinGroupRequest, 0, joinGroupRequest.length);
            bos.flush();
            LOGGER.info("Send join group request successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Send join group request failed!");
        }
    }

    /**
     * 服务器心跳连接
     */
    public void keepAlive() {
        //获取与弹幕服务器保持心跳的请求数据包
        byte[] keepAliveRequest = DyMessage.getKeepAliveData((int) (System.currentTimeMillis() / 1000));

        try {
            //向弹幕服务器发送心跳请求数据包
            bos.write(keepAliveRequest, 0, keepAliveRequest.length);
            bos.flush();
            LOGGER.info("Send keep alive request successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Send keep alive request failed!");
        }
    }

    /**
     * 获取服务器返回信息
     */
    public void getServerMsg(DanmuRepository danmuRepository) {
        //初始化获取弹幕服务器返回信息包大小
        byte[] recvByte = new byte[GlobalConstant.MAX_BUFFER_LENGTH];
        //定义服务器返回信息的字符串
        String dataStr;
        try {
            //读取服务器返回信息，并获取返回信息的整体字节长度
            int recvLen = bis.read(recvByte, 0, recvByte.length);

            //根据实际获取的字节数初始化返回信息内容长度
            byte[] realBuf = new byte[recvLen];
            //按照实际获取的字节长度读取返回信息
            System.arraycopy(recvByte, 0, realBuf, 0, recvLen);
            //根据TCP协议获取返回信息中的字符串信息
            dataStr = new String(realBuf, 12, realBuf.length - 12);

            //循环处理socekt黏包情况
            while (dataStr.lastIndexOf("type@=") > 5) {
                //对黏包中最后一个数据包进行解析
                MsgView msgView = new MsgView(StringUtils.substring(dataStr, dataStr.lastIndexOf("type@=")));
                //分析该包的数据类型，以及根据需要进行业务操作
                parseServerMsg(msgView.getMessageList(), danmuRepository);
                //处理黏包中的剩余部分
                dataStr = StringUtils.substring(dataStr, 0, dataStr.lastIndexOf("type@=") - 12);
            }
            //对单一数据包进行解析
            MsgView msgView = new MsgView(StringUtils.substring(dataStr, dataStr.lastIndexOf("type@=")));
            //分析该包的数据类型，以及根据需要进行业务操作
            parseServerMsg(msgView.getMessageList(), danmuRepository);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析从服务器接受的协议，并根据需要订制业务需求
     *
     * @param msg
     */
    private void parseServerMsg(Map<String, Object> msg, DanmuRepository danmuRepository) {
        if (msg.get("type") != null) {

            //服务器反馈错误信息
            if (msg.get("type").equals("error")) {
                LOGGER.info(msg.toString());
                //结束心跳和获取弹幕线程
                this.readyFlag = false;
            }

            /***@TODO 根据业务需求来处理获取到的所有弹幕及礼物信息***********/

            //判断消息类型
            if (msg.get("type").equals("chatmsg")) {//弹幕消息
                LOGGER.info("弹幕消息===>" + msg.toString());
                //TODO 插入mogodb
                Optional<Object> optional = Optional.ofNullable(msg.get("txt"));
                Optional<Object> optional1 = Optional.ofNullable(msg.get("nn"));
                Optional<Object> optional2 = Optional.ofNullable(msg.get("cst"));
                Danmu danmu = new Danmu();
                if (optional.isPresent()) {
                    danmu.setText(optional.get().toString());
                } else {
                    danmu.setText(" ");
                }
                if (optional1.isPresent()) {
                    danmu.setTalkName(optional1.get().toString());
                } else {
                    danmu.setTalkName(" ");
                }
                if (optional2.isPresent()) {
                    danmu.setTime(optional2.get().toString());
                } else {
                    danmu.setTime(String.valueOf(System.currentTimeMillis()));
                }
                danmuRepository.insert(danmu);
                        //赠送礼物信息
            } else if (msg.get("type").equals("dgb")) {
                LOGGER.info("礼物消息===>" + msg.toString());
            } else {
                LOGGER.info("其他消息===>" + msg.toString());
            }
        }
    }
}