-- mvn flyway:info -Dflyway.configFile=myFlywayConfig.properties &&
-- mvn clean flyway:migrate -Dflyway.configFile=myFlywayConfig.properties
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
create table albums (
                        album_id uuid not null default uuid_generate_v4(),
                        album_name varchar(55),
                        primary key (album_id)
);

create table singers (
                         singer_id uuid not null default uuid_generate_v4(),
                         singer_name varchar(55),
                         primary key (singer_id)
);

create table songs (
                       song_id uuid not null default uuid_generate_v4(),
                       genre varchar(55),
                       release_date date,
                       title varchar(55),
                       album_id uuid not null,
                       singer_id uuid not null,
                       primary key (song_id)
);

alter table songs
drop constraint if exists UK_album_id;

alter table songs  
    add constraint UK_album_id unique (album_id);

alter table songs
drop constraint if exists UK_singer_id;

alter table songs
    add constraint UK_singer_id unique (singer_id);

alter table songs
    add constraint FK_album_id
        foreign key (album_id)
            references albums;

alter table songs
    add constraint FK_singer_id
        foreign key (singer_id)
            references singers