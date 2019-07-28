create table parking_lot
(
    id       bigint primary key auto_increment,
    address  varchar(255),
    capacity integer not null,
    name     varchar(255),
)
