//package ru.luxoft.music.conversation;
//
//import javax.ejb.ActivationConfigProperty;
//import javax.ejb.MessageDriven;
//import javax.jms.JMSException;
//import javax.jms.Message;
//import javax.jms.MessageListener;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//@MessageDriven(name = "Receiver", activationConfig = {
//        @ActivationConfigProperty(propertyName = "destinationLookup",
//                          propertyValue = "jms/queue/test"),
////                propertyValue = "java:/jms/queue/DLQ"),
//        @ActivationConfigProperty(propertyName = "destinationType",
//                propertyValue = "javax.jms.Queue"),
//        @ActivationConfigProperty(propertyName = "acknowledgeMode",
//                propertyValue = "Auto-acknowledge")})
//
//public class ReceiverByMLDefault implements MessageListener {
//
//    private static final Logger LOG = Logger.getLogger(ReceiverByMLDefault.class.getName());
//
//    public static List<String> messages = new ArrayList<>();
//
//    public void onMessage(Message rcvMessage) {
//        try {
//            String messageBody = rcvMessage.getBody(String.class);
//            System.out.println(messageBody);
//            messages.add(messageBody);
//        } catch (JMSException ex) {
//            LOG.log(Level.SEVERE, ex.getMessage(), ex);
//        }
//    }
//}