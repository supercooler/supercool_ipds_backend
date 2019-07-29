package com.supercool.supercool_ipds_backend.model;

import com.supercool.supercool_ipds_backend.common.utils.DateUtils;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
public class ParkingBoy {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String name;

    private Date birthYear;

    private String gender;

    private Date employeeDate;

    @Column(length = 11)
    private String phone;

    private String status = "空闲";

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "parking_boy_id")
    private List<ParkingLot> parkingLots;

    private String tag;

    @Transient
    private int age;

    @Transient
    private int workExperience;

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

    public Date getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Date birthYear) {
        this.birthYear = birthYear;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getEmployeeDate() {
        return employeeDate;
    }

    public void setEmployeeDate(Date employeeDate) {
        this.employeeDate = employeeDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public int getAge() {
        return DateUtils.calculateAge(getBirthYear());
    }


    public int getWorkExperience() {
        return DateUtils.calculateAge(getEmployeeDate());
    }

    public void setAge(int age) {
        this.birthYear = DateUtils.calculateDate(age);
        this.age = age;
    }

    public void setWorkExperience(int workExperience) {
        this.employeeDate = DateUtils.calculateDate(workExperience);
        this.workExperience = workExperience;
    }

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    public void setParkingLots(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
