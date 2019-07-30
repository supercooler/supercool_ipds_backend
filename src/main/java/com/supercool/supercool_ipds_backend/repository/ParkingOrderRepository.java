package com.supercool.supercool_ipds_backend.repository;

import com.supercool.supercool_ipds_backend.DomainObject.ParkingOrderDO;
import com.supercool.supercool_ipds_backend.model.ParkingOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ParkingOrderRepository extends JpaRepository<ParkingOrder, Long> {

    @Query(value = "select new com.supercool.supercool_ipds_backend.DomainObject.ParkingOrderDO(po.parkingBoy.id, avg(po.score) as score, count(po.parkingBoy)) from ParkingOrder po left join ParkingBoy pb on (po.parkingBoy.id = pb.id) where pb.status = '空闲' group by pb.id")
    List<ParkingOrderDO> loadParkingOrderDOs();

    @Query(value = "select po.* from parking_order po left join parking_boy pb on (po.parking_boy_id = pb.id) where pb.name like ?1", nativeQuery = true)
    List<ParkingOrder> findByBoy(String info);

    @Query(value = "select po.* from parking_order po left join parking_lot pl on (po.parking_lot_id = pb.id) where pl.name like ?1", nativeQuery = true)
    List<ParkingOrder> findByLot(String info);

    @Query(value = "delete from parking_order where id = ?1", nativeQuery = true)
    void deleteById(Long id);

    List<ParkingOrder> findAllByStatus(String status);
}
