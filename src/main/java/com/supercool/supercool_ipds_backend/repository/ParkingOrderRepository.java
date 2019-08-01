package com.supercool.supercool_ipds_backend.repository;

import com.supercool.supercool_ipds_backend.DomainObject.ParkingOrderDO;
import com.supercool.supercool_ipds_backend.model.ParkingOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ParkingOrderRepository extends JpaRepository<ParkingOrder, Long> {

    @Query(value = "select po.* from parking_order po left join parking_boy pb on (po.parking_boy_id = pb.id) where pb.name like ?1", nativeQuery = true)
    List<ParkingOrder> findByBoy(String info);

    @Query(value = "select po.* from parking_order po left join parking_lot pl on (po.parking_lot_id = pl.id) where pl.name like ?1", nativeQuery = true)
    List<ParkingOrder> findByLot(String info);

    @Query(value = "select po.* from parking_order po left join user u on (po.user_id = u.id) where u.id = ?1", nativeQuery = true)
    List<ParkingOrder> findByUser(Long id);

    @Modifying
    @Query(value = "delete from parking_order where id = ?1", nativeQuery = true)
    void deleteById(Long id);

    List<ParkingOrder> findAllByStatus(String status);

    @Query(value = "select po.* from parking_order po left join parking_boy pb on (po.parking_boy_id = pb.id) where pb.name = ?1 and po.status = '已配单'", nativeQuery = true)
    List<ParkingOrder> findByParkingBoyPark(String parkingBoyName);

    @Query(value = "select po.* from parking_order po left join parking_boy pb on (po.parking_boy_id = pb.id) where pb.name = ?1 and (po.status = '已停车' or po.status = '取车中' or po.status = '待确认' or po.status = '已完成')", nativeQuery = true)
    List<ParkingOrder> findByParkingBoyFetch(String parkingBoyName);

    @Query(value = "select count(*)\n" +
            "from parking_order\n" +
            "where ?1 > book_time and book_time > ?2", nativeQuery = true)
    int findAllParkingBoysBusy(String ceil, String floor);

}
