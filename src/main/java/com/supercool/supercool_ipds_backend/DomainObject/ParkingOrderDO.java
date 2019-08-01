package com.supercool.supercool_ipds_backend.DomainObject;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ParkingOrderDO {

    @Id
    private Long parkingBoyId;
    private double score;
    private Long count;

    public ParkingOrderDO() {
    }

    public ParkingOrderDO(Long parkingBoyId, double score, Long count) {
        this.parkingBoyId = parkingBoyId;
        this.score = score;
        this.count = count;
    }

    public Long getParkingBoyId() {
        return parkingBoyId;
    }

    public void setParkingBoyId(Long parkingBoyId) {
        this.parkingBoyId = parkingBoyId;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
