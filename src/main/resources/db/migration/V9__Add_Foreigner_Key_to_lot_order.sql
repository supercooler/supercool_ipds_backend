ALTER TABLE parking_order ADD COLUMN parking_lot_id bigint;
ALTER TABLE parking_order ADD CONSTRAINT order_parking_lot FOREIGN KEY (parking_lot_id) REFERENCES parking_boy(id);