create table user
(
    id        bigint auto_increment primary key,
    user_name varchar(255) not null,
    password  varchar(255) not null,
)
