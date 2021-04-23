package ru.luxoft.music.conversation;

import org.apache.activemq.command.ActiveMQBytesMessage;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;

/**
 * Receiver.
 *
 * @author Evgeniy_Medvedev
 */
@Component
public class Consumer {

//    @JmsListener(destination = "java:jms/queue/music")
    public void onMessage(Message message) throws JMSException {
        ObjectMessage objectMessage = (ObjectMessage) message;

        System.out.println("Received Message - " + objectMessage.getObject());
    }

//    @JmsListener(destination = "java:jms/queue/music")
    public void onMessage(ActiveMQBytesMessage message) throws JMSException {
    }

//    @JmsListener(destination = "java:jms/queue/music")
    public void onMessage(Object message) throws JMSException {
        System.out.println("Received Some - " + message);
    }
}