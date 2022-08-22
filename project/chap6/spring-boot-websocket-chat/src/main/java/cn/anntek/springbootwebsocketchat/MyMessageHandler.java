package cn.anntek.springbootwebsocketchat;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyMessageHandler extends TextWebSocketHandler {
    private List<WebSocketSession> list;

    public MyMessageHandler() {
        list=new ArrayList<WebSocketSession>();
    }
    @Override
    public void afterConnectionClosed(WebSocketSession seesion, CloseStatus status){
        list.remove(seesion);
    }
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws IOException {
        session.sendMessage(new TextMessage("欢迎您...."));
        list.add(session);
    }
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws IOException {
        // 收到消息
        System.out.println("Message received："+textMessage.getPayload()+" sessionId："+session.getId());
        String msg = "服务器收到消息："+textMessage.getPayload();
        session.sendMessage(new TextMessage(msg));
        for(var sess:list){
            sess.sendMessage(new TextMessage(msg+"id："+sess.getId()));
        }
    }
}