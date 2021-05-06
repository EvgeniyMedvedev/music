package ru.luxoft.music.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Если мы хотим отключить только автоматическую миграцию Flyway при запуске,
 * но хотим запускать миграции вручную, то использование вышеописанных свойств нам не подойдет.
 *
 * Это связано с тем, что Spring Boot не будет автоматически конфигурировать бины Flyway и,
 * следовательно, нам придется настраивать их самостоятельно, что не очень удобно.
 *
 * В этом случае мы можем оставить Flyway включенным и реализовать пустую FlywayMigrationStrategy:
 * */
@Configuration
@Profile("manual-migration")
public class EmptyMigrationStrategyConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmptyMigrationStrategyConfig.class);

    @Bean
    public FlywayMigrationStrategy flywayMigrationStrategy() {
        LOGGER.info("do nothing");
        return null;
    }
}