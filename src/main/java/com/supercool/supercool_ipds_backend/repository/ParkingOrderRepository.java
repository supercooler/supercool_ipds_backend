package com.supercool.supercool_ipds_backend.repository;

import com.supercool.supercool_ipds_backend.DomainObject.ParkingOrderDO;
import com.supercool.supercool_ipds_backend.model.ParkingOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ParkingOrderRepository extends JpaRepository<ParkingOrder, Long> {

    @Query(value = "select new com.supercool.supercool_ipds_backend.DomainObject.ParkingOrderDO(po.parkingBoy.id, avg(po.score) as score, count(po.parkingBoy)) from ParkingOrder po left join ParkingBoy pb on (po.parkingBoy.id = pb.id) where pb.status = '空闲' group by pb.id")
    public List<ParkingOrderDO> loadParkingOrderDOs();
}
