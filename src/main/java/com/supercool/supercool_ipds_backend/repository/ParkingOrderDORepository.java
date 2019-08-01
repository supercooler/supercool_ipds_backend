package com.supercool.supercool_ipds_backend.repository;

import com.supercool.supercool_ipds_backend.DomainObject.ParkingOrderDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ParkingOrderDORepository extends JpaRepository<ParkingOrderDO,Long> {

    @Query(value = "select po.PARKING_BOY_ID as PARKING_BOY_ID, avg(po.SCORE) as SCORE, count(po.PARKING_BOY_ID) as COUNT from PARKING_ORDER po left join PARKING_BOY pb on (po.PARKING_BOY_ID = pb.id and pb.status = '空闲') group by pb.id", nativeQuery = true)
    List<ParkingOrderDO> loadParkingOrderDOs();

}
