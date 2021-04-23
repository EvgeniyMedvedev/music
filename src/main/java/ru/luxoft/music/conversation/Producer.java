//package ru.luxoft.music.conversation;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import javax.ejb.Schedule;
//import javax.enterprise.context.ApplicationScoped;
//import javax.jms.Connection;
//import javax.jms.ConnectionFactory;
//import javax.jms.Destination;
//import javax.jms.JMSContext;
//import javax.jms.JMSException;
//import javax.jms.MessageProducer;
//import javax.jms.Queue;
//import javax.jms.Session;
//import javax.jms.TextMessage;
//
//
///**
// * Producer.
// *
// * @author Evgeniy_Medvedev
// */
//@ApplicationScoped
//public class Producer {
//
//    @Resource(name = "java:/ConnectionFactory")
//    private ConnectionFactory connectionFactory;
//
//    @Resource(name = "java:/queue/test")
//    private Destination destination;
////active or rabbit -mq
//    @Schedule(hour = "*", minute = "*", second = "*/1", persistent = false)
//    public void produceMessage(){
//        try(Connection connection = connectionFactory.createConnection();
//            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE)) {
//
//            MessageProducer producer = session.createProducer(destination);
//            TextMessage message = session.createTextMessage("Hello");
//            producer.send(message);
//            System.out.println("===============================");
//
//        } catch (JMSException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Resource(mappedName = "jms/queue/test")
//    private Queue queue;
//
//    @Autowired
//    private JMSContext context;
//
//    public void sendMessage(String txt) {
//        context.createProducer().send(queue, txt);
//    }
//}