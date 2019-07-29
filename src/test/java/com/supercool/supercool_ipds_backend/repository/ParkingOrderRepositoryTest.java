package com.supercool.supercool_ipds_backend.repository;

import com.supercool.supercool_ipds_backend.DomainObject.ParkingOrderDO;
import com.supercool.supercool_ipds_backend.model.ParkingBoy;
import com.supercool.supercool_ipds_backend.model.ParkingOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ParkingOrderRepositoryTest {

    @Autowired
    private ParkingOrderRepository parkingOrderRepository;

    @Test
    public void should_reture_parkingBoyDos_when_call_loadParkingOrders() throws ParseException {

        ParkingBoy parkingBoy = new ParkingBoy(1L,"amiao",getDateFromString("1990-01-10 20:10:10"), "女",getDateFromString("2000-10-10 10:00:00"),   "15266548557", "空闲", "小");
        ParkingBoy parkingBoy2 = new ParkingBoy(2L,"王五",getDateFromString("1990-01-10 20:10:10"), "女",getDateFromString("2000-10-10 10:00:00"),   "15266548557", "空闲", "小");

        ParkingOrder parkingOrder = new ParkingOrder(1L, "已下单", "1234567", parkingBoy, "13800138000", "aaaaa", 4.8);
        ParkingOrder parkingOrder2 = new ParkingOrder(2L, "已下单", "1234567", parkingBoy2, "13800138000", "aaaaa", 4.9);
        ParkingOrder parkingOrder3 = new ParkingOrder(3L, "已下单", "1234567", parkingBoy, "13800138000", "aaaaa", 4.7);
        ParkingOrder parkingOrder4 = new ParkingOrder(4L, "已下单", "1234567", parkingBoy2, "13800138000", "aaaaa", 5.0);

        parkingOrderRepository.save(parkingOrder);
        parkingOrderRepository.save(parkingOrder3);
        parkingOrderRepository.save(parkingOrder2);
        parkingOrderRepository.save(parkingOrder4);

        //when
        List<ParkingOrderDO> parkingOrdeDos= parkingOrderRepository.loadParkingOrderDOs();
        ParkingOrderDO parkingOrderDO=new ParkingOrderDO();
        parkingOrderDO.setParkingBoyId(1L);
        parkingOrderDO.setScore(4.75);
        parkingOrderDO.setCount(2L);
        //then
        assertEquals(parkingOrderDO.getParkingBoyId(),parkingOrdeDos.get(0).getParkingBoyId());
    }

    private Date getDateFromString(String string) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(string);
    }
}