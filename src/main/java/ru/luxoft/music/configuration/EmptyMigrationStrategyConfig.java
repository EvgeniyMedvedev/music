package ru.luxoft.music.configuration;

import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Если мы хотим отключить только автоматическую миграцию Flyway при запуске,
 * но хотим запускать миграции вручную, то использование вышеописанных свойств нам не подойдет.
 *
 * Это связано с тем, что Spring Boot не будет автоматически конфигурировать бины Flyway и,
 * следовательно, нам придется настраивать их самостоятельно, что не очень удобно.
 *
 * В этом случае мы можем оставить Flyway включенным и реализовать пустую FlywayMigrationStrategy:
 * */
//@Configuration
public class EmptyMigrationStrategyConfig {

//    @Bean
//    public FlywayMigrationStrategy flywayMigrationStrategy() {
//        return flyway -> {
//            do nothing
//        };
//    }
}