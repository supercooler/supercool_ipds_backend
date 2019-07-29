ALTER TABLE parking_lot ADD parking_boy_id bigint UNSIGNED NOT NULL DEFAULT 0;
ALTER TABLE parking_lot ADD CONSTRAINT parking_boy_lot FOREIGN KEY (parking_boy_id) REFERENCES parking_boy(id);