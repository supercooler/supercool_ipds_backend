ALTER TABLE parking_order ADD COLUMN user_id bigint;
ALTER TABLE parking_order ADD CONSTRAINT order_user FOREIGN KEY (user_id) REFERENCES user(id);