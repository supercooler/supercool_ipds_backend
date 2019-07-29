package com.supercool.supercool_ipds_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.Date;
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

    @NotNull
    private Date bookTime;

    private double score;

    public ParkingOrder() {
    }

    public ParkingOrder(Long id, @NotNull String status, @NotNull String carLisenceNumber, ParkingBoy parkingBoy, @NotNull String userPhone, @NotNull String preLocation, @NotNull Date bookTime, double score) {
        this.id = id;
        this.status = status;
        this.carLisenceNumber = carLisenceNumber;
        this.parkingBoy = parkingBoy;
        this.userPhone = userPhone;
        this.preLocation = preLocation;
        this.bookTime = bookTime;
        this.score = score;
    }

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

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Date getBookTime() {
        return bookTime;
    }

    public void setBookTime(Date bookTime) {
        this.bookTime = bookTime;
    }
}
