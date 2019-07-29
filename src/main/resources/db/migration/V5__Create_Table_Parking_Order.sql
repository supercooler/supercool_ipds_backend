create table parking_order (
 id bigint primary key auto_increment ,
 car_lisence_number varchar(7) not null,
 pre_location varchar(255) not null,
 score double not null,
 status varchar(10) not null,
 user_phone varchar(11) not null,
 book_time timestamp not null,
 parking_boy_id bigint
)