-- mvn flyway:info -Dflyway.configFile=myFlywayConfig.properties &&
-- mvn clean flyway:migrate -Dflyway.configFile=myFlywayConfig.properties
alter table singers add column info varchar null;