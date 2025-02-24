package com.gousade.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
@Component
@ServerEndpoint(value = "/websocket/{id}", subprotocols = {"protocol"})
public class WebSocketPush {
    private static final ConcurrentHashMap<String, CopyOnWriteArrayList<WebSocketPush>> webSocketSet = new ConcurrentHashMap<>();
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    private String id = "";

    public static synchronized int getOnlineCount() {
        int count = 0;
        for (String key : webSocketSet.keySet()) {
            count += webSocketSet.get(key).size();
        }
        return count;
    }

    public synchronized static void sendById(String message, String id) {
        List<WebSocketPush> wsp = webSocketSet.get(id);
        if (wsp != null) {
            wsp.forEach(x -> {
                try {
                    if (x != null) {
                        x.sendMessage(message);
                    }
                } catch (Exception e) {
                    log.error(String.format("连接编号：%s 发送数据异常：%s", x.id, e.getMessage()));
                }
            });
        }
    }

    public synchronized static void sendtoAll(String message) {
        webSocketSet.keySet().forEach(key -> sendById(message, key));
    }

    @OnOpen
    public synchronized void onOpen(@PathParam(value = "id") String id, Session session) {
        this.session = session;
        this.id = id;//接收到发送消息的人员编号
        if (onlyOneConnection()) {
            kickOut();
        }
        webSocketSet.computeIfAbsent(id, k -> new CopyOnWriteArrayList<>());
        webSocketSet.get(id).add(this);
        log.info(String.format("用户:%s连接成功,sessionId:%s ！", this.id, this.session.getId()));
    }

    @OnClose
    public synchronized void onClose() {
        try {
            String sessionId = this.session.getId();
            List<WebSocketPush> list = webSocketSet.getOrDefault(this.id, new CopyOnWriteArrayList<>());
            list.removeIf(push -> push != null && push.session.getId().equals(sessionId));
            log.warn(String.format("用户：%s,sessionId:%s关闭", this.id, this.session.getId()));
        } catch (Exception e) {
            log.error("关闭连接异常:{}", e.getMessage());
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        if ("PING".equals(message)) {
            try {
                session.getBasicRemote().sendText("PONG");
            } catch (IOException e) {
                log.error("websocket回复心跳异常", e);
            }
            return;
        }
        log.info("来自客户端的消息:" + message);
    }

    private boolean onlyOneConnection() {
        return false;
    }

    private void kickOut() {
        WebSocketPush wsp = webSocketSet.getOrDefault(this.id, new CopyOnWriteArrayList<>()).stream().findFirst().orElse(null);
        if (wsp != null && !wsp.session.getId().equals(this.session.getId())) {
            wsp.kick();
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        try {
            session.close();
        } catch (Exception e) {
            log.error("websocket异常。", e);
        }
    }

    private void sendMessage(String message) throws IOException {
        try {
            this.session.getBasicRemote().sendText(message);
        } catch (IllegalStateException e) {
            log.error(String.format("连接：%s 发送数据异常：%s", this.id, e.getMessage()));
            try {
                session.close();
            } catch (Exception ignored) {
            }
        }
    }

    private void kick() {
        try {
            this.session.close();
        } catch (Exception e) {
            log.error(String.format("关闭session失败：编号：%s", this.id), e);
        }
    }
}

