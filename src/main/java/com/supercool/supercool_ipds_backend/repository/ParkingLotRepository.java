package com.supercool.supercool_ipds_backend.repository;

import com.supercool.supercool_ipds_backend.model.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ParkingLotRepository extends JpaRepository<ParkingLot, Long> {
    List<ParkingLot> findByNameLike(String name);

    @Query(value = "select pl.* from parking_lot pl left join parking_boy pb on (pl.parking_boy_id = pb.id) where pb.name = ?1", nativeQuery = true)
    List<ParkingLot> findByParkingBoy(String parkingBoyName);

    @Query(value = "SELECT * FROM PARKING_LOT where PARKING_BOY_ID is not null", nativeQuery = true)
    List<ParkingLot> findAllManagementParkingLots();
    @Query(value = "select sum(temp.pb_rest_sum) from (select parking_boy_id, sum(rest_capacity) as pb_rest_sum from parking_lot group by parking_boy_id) as temp;", nativeQuery = true)
    Long getAllRestCapacity();
}
