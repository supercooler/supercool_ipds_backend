package com.supercool.supercool_ipds_backend.common.utils;

import com.supercool.supercool_ipds_backend.DomainObject.ParkingOrderDO;
import com.supercool.supercool_ipds_backend.model.ParkingBoy;
import com.supercool.supercool_ipds_backend.repository.ParkingBoyRepository;
import com.supercool.supercool_ipds_backend.repository.ParkingOrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DistributionExcellentParkingBoyTest {

    @MockBean
    private ParkingOrderRepository parkingOrderRepository;

    @MockBean
    private ParkingBoyRepository parkingBoyRepository;

    @Test
    public void should_return_excellent_parkingBoy_when_call_getExcellentParkingBoy() {
        //when
        ParkingOrderDO parkingOrderDO = new ParkingOrderDO(1L, 4.75, 2L);
        ParkingOrderDO parkingOrderDO2 = new ParkingOrderDO(2L, 4.95, 2L);
        List<ParkingOrderDO> parkingOrderDOs = new ArrayList<>();
        parkingOrderDOs.add(parkingOrderDO);
        parkingOrderDOs.add(parkingOrderDO2);

        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.setId(2L);

        when(parkingOrderRepository.loadParkingOrderDOs()).thenReturn(parkingOrderDOs);

        when(parkingBoyRepository.findById(any())).thenReturn(Optional.of(parkingBoy));

        ParkingBoy parkingBoy1 = new DistributionExcellentParkingBoy().getExcellentParkingBoy();

        assertEquals(parkingBoy.getId(), parkingBoy1.getId());
    }

}