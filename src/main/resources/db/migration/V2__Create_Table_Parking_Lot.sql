create table parking_lot
(
    id       bigint  not null,
    address  varchar(255),
    capacity integer not null,
    name     varchar(255),
    primary key (id)
)
