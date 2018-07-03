package com.reader;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author lh on 2016/8/24 13:32.
 */
@Component
public class Receiver {

    @RabbitListener(queues = "myQueue")
    public void processMessage(Message message) {
        byte[] body = message.getBody();
        System.out.println("收到消息: '" + new String(body) + "'");
    }
    
    @RabbitListener(queues = "myQueue2")
    public void readMessage(Message message) {
        byte[] body = message.getBody();
        /*if (body != null) {
        	throw new RuntimeException("模拟错误");
        }*/
        System.out.println("read message : '" + new String(body) + "'");
    }

}
