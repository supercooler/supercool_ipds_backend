package com.supercool.supercool_ipds_backend.repository;

import com.supercool.supercool_ipds_backend.model.ParkingBoy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ParkingBoyRepository extends JpaRepository<ParkingBoy, Long> {
    public List<ParkingBoy> findAll();
    public List<ParkingBoy> findByNameLike(String name);
    public List<ParkingBoy> findByGender(String gender);
}
