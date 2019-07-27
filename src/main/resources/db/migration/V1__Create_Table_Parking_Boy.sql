create table parking_boy
(
    id            bigint not null,
    birth_year    timestamp,
    employee_date timestamp,
    gender        varchar(255),
    name          varchar(255),
    phone         varchar(11),
    status        varchar(255),
    primary key (id)
)
