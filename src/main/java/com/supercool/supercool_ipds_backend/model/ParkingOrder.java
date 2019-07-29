package com.supercool.supercool_ipds_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class ParkingOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(length = 10)
    private String status = "已下单";

    @NotNull
    @Column(length = 7)
    private String carLisenceNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parking_boy_id")
    @JsonIgnore
    private ParkingBoy parkingBoy;

    @NotNull
    @Column(length = 11)
    private String userPhone;

    @NotNull
    private String preLocation;

    private int score;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCarLisenceNumber() {
        return carLisenceNumber;
    }

    public void setCarLisenceNumber(String carLisenceNumber) {
        this.carLisenceNumber = carLisenceNumber;
    }

    public ParkingBoy getParkingBoy() {
        return parkingBoy;
    }

    public void setParkingBoy(ParkingBoy parkingBoy) {
        this.parkingBoy = parkingBoy;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getPreLocation() {
        return preLocation;
    }

    public void setPreLocation(String preLocation) {
        this.preLocation = preLocation;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
