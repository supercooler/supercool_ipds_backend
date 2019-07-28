create table parking_lot
(
    id       bigint auto_increment primary key,
    address  varchar(255),
    capacity integer not null,
    name     varchar(255),
)
