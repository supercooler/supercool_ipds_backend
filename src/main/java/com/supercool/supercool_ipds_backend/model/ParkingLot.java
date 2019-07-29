package com.supercool.supercool_ipds_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class ParkingLot {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String name;

    private int capacity;

    private String address;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parking_boy_id")
    @JsonIgnore
    private ParkingBoy parkingBoy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ParkingBoy getParkingBoy() {
        return parkingBoy;
    }

    public void setParkingBoy(ParkingBoy parkingBoy) {
        this.parkingBoy = parkingBoy;
    }
}
