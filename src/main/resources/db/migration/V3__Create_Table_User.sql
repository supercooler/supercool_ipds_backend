create table user
(
    id        bigint primary key auto_increment,
    user_name varchar(255) not null,
    password  varchar(255) not null,
)
