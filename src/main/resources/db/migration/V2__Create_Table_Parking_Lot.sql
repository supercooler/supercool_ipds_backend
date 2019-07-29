create table parking_lot
(
    id              bigint primary key auto_increment,
    parking_boy_id  bigint,
    address         varchar(255) not null,
    capacity        integer      not null,
    name            varchar(255) not null
)