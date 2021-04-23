//package ru.luxoft.music.conversation;
//
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import javax.enterprise.context.ApplicationScoped;
//import javax.inject.Inject;
//import javax.jms.JMSContext;
//import javax.jms.Queue;
//
////@Component
//public class Sender {
//    //	@Resource(mappedName = "jms/queue/test")
//    @Resource(mappedName = "java:/jms/queue/DLQ")
//    private Queue queue;
//
//    @Inject
//    private JMSContext context;
//
//    public void sendMessage(String txt) {
//        context.createProducer().send(queue, txt);
//    }
//}
