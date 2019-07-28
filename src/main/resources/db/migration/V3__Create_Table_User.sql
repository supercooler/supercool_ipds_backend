create table user
(
    id        bigint primary key auto_increment,
    password  varchar(255) not null,
    user_name varchar(255) not null
)
