package ru.luxoft.music.configure;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.ConnectionFactory;

@Configuration
public class TestConfigure {

    @Bean
    public ConnectionFactory connectionFactory(){
        return new ActiveMQConnectionFactory("vm://localhost?broker.persistent=false");
    }
}
