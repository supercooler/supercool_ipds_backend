package com.supercool.supercool_ipds_backend.service;

import com.supercool.supercool_ipds_backend.common.constant.Constant;
import com.supercool.supercool_ipds_backend.common.utils.DistributionExcellentParkingBoy;
import com.supercool.supercool_ipds_backend.dto.AppointmentDto;
import com.supercool.supercool_ipds_backend.common.exception.CustomException;
import com.supercool.supercool_ipds_backend.model.ParkingBoy;
import com.supercool.supercool_ipds_backend.model.ParkingOrder;
import com.supercool.supercool_ipds_backend.repository.ParkingBoyRepository;
import com.supercool.supercool_ipds_backend.repository.ParkingOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.supercool.supercool_ipds_backend.common.exception.ExceptionEnum.*;

@Service
public class ParkingOrderService {

    @Autowired
    private ParkingOrderRepository parkingOrderRepository;

    @Autowired
    private DistributionExcellentParkingBoy distributionExcellentParkingBoy;

    @Autowired
    private ParkingBoyRepository parkingBoyRepository;

    public List<ParkingOrder> getParkingOrders(String status,String boy,String lot) {
        if(status.equalsIgnoreCase("")){
            if (boy.equalsIgnoreCase("")){
                if (lot.equalsIgnoreCase("")){
                    return parkingOrderRepository.findAll();
                }
                else {
                    return parkingOrderRepository.findByLot("%" + lot.trim() + "%");
                }
            }
            else {
                return parkingOrderRepository.findByBoy("%" + boy.trim() + "%");
            }
        }
        else{
            return parkingOrderRepository.findAllByStatus(status);
        }
    }

    public ParkingOrder createParkingOrders(AppointmentDto appointmentDto) {
        ParkingOrder parkingOrder = generateParkingOrder(appointmentDto);
        return parkingOrderRepository.save(parkingOrder);
    }

    private ParkingOrder generateParkingOrder(AppointmentDto appointmentDto) {
        // generate order by dto
        ParkingOrder parkingOrder = new ParkingOrder();
        parkingOrder.setUser(appointmentDto.getUser());
        parkingOrder.setCarLisenceNumber(appointmentDto.getPlateNumber());
        parkingOrder.setPreLocation(appointmentDto.getAddress());
        parkingOrder.setUserPhone(appointmentDto.getPhone());
        parkingOrder.setBookTime(appointmentDto.getBookTime());

        // get parkingBoy by algorithm
        ParkingBoy parkingBoy = distributionExcellentParkingBoy.getExcellentParkingBoy(parkingOrder);
        if (parkingBoy!=null) {
            parkingBoy.setStatus(Constant.BOY_BUSY);
            parkingOrder.setStatus(Constant.HAD_DISPATCHED);
            parkingOrder.setParkingBoy(parkingBoy);
        }
        return parkingOrder;

    }

    public void deleteParkingOrder(Long id) {
        try {
            parkingOrderRepository.deleteById(id);
        } catch (Exception e) {
            throw new CustomException(Delete_Not_Found_Exception.getMessage(), Delete_Not_Found_Exception.getCode());
        }
    }

    public ParkingOrder updateParkingOrder(ParkingOrder parkingOrder) {
        try {
            String status = parkingOrder.getStatus();
            switch (status){
                //已下单
                case Constant.HAD_ORDERED:
                    //已停车
                case Constant.HAD_CAR_STOPED:
//待确认
                case Constant.WILL_BE_CONFIRMED:
//已完成
                case Constant.FINISH_FETCHING:
                    updateParkingBoy(parkingOrder,Constant.BOY_EASY);break;//员工闲
                //已配单
                case Constant.HAD_DISPATCHED:
//取车中
                case Constant.IN_FETCHING_CAR:
                    updateParkingBoy(parkingOrder,Constant.BOY_BUSY);break;//员工忙碌
            }
            return parkingOrderRepository.save(parkingOrder);
        } catch (Exception e) {
            throw new CustomException(Update_Not_Fount_Exception.getMessage(), Update_Not_Fount_Exception.getCode());
        }
    }

    public List<ParkingOrder> getOrdersByUserId(Long id) {
        return parkingOrderRepository.findByUser(id);
    }

    public List<ParkingOrder> getOrdersByParkingBoy(String parkingBoyName,String type) {
        switch (type){
            case "fetch":return parkingOrderRepository.findByParkingBoyFetch(parkingBoyName);
            case "park":return parkingOrderRepository.findByParkingBoyPark(parkingBoyName);
        }
        return null;
    }

    public ParkingOrder getOrderById(Long id) {
        return parkingOrderRepository.findById(id).orElse(null);
    }

    private void updateParkingBoy(ParkingOrder parkingOrder,String status){
        //修改ParkingBoy的状态
        ParkingBoy parkingBoy = parkingOrder.getParkingBoy();
        parkingBoy.setStatus(status);
        parkingBoyRepository.save(parkingBoy);
    }
}
