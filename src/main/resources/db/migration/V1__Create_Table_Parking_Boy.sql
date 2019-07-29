create table parking_boy
(
    id            bigint primary key auto_increment,
    birth_year    timestamp    not null,
    employee_date timestamp    not null,
    gender        varchar(255) not null,
    name          varchar(255) not null,
    phone         varchar(11),
    status        varchar(255) not null,
    tag           varchar(255) not null
)