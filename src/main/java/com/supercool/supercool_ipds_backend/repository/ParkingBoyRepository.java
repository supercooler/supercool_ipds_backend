package com.supercool.supercool_ipds_backend.repository;

import com.supercool.supercool_ipds_backend.model.ParkingBoy;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ParkingBoyRepository extends JpaRepository<ParkingBoy, Long> {

    List<ParkingBoy> findAllByOrderByBirthYearDesc();
    @Query(value = "select * from parking_boy where upper(name) like ?1 order by birth_year desc", nativeQuery = true)
    List<ParkingBoy> findByNameLike(String name);
    List<ParkingBoy> findByGender(String gender);
    @Query(value = "select * from parking_boy where upper(tag) like ?1 order by birth_year desc", nativeQuery = true)
    List<ParkingBoy> findByTagLike(String tag);
}
