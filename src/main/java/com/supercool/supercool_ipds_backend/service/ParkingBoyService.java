package com.supercool.supercool_ipds_backend.service;

import com.supercool.supercool_ipds_backend.common.constant.Constant;
import com.supercool.supercool_ipds_backend.common.exception.CustomException;
import com.supercool.supercool_ipds_backend.common.exception.ExceptionEnum;
import com.supercool.supercool_ipds_backend.common.utils.MD5Utils;
import com.supercool.supercool_ipds_backend.model.ParkingBoy;
import com.supercool.supercool_ipds_backend.model.User;
import com.supercool.supercool_ipds_backend.repository.ParkingBoyRepository;
import com.supercool.supercool_ipds_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.supercool.supercool_ipds_backend.common.exception.ExceptionEnum.Delete_Not_Found_Exception;

@Service
public class ParkingBoyService {

    @Autowired
    private ParkingBoyRepository parkingBoyRepository;
    @Autowired
    private UserRepository userRepository;

    public List<ParkingBoy> getParkingBoys(String name, String gender,String tag) {
        if (name != null) return parkingBoyRepository.findByNameLike("%" + name.trim().toUpperCase() + "%");
        if (gender != null) return parkingBoyRepository.findByGender(gender);
        if (tag != null) return parkingBoyRepository.findByTagLike("%" + tag.trim().toUpperCase() + "%");
        return parkingBoyRepository.findAllByOrderByBirthYearDesc();
    }

    public ParkingBoy updateParkingBoy(ParkingBoy parkingBoy) {
        return parkingBoyRepository.save(parkingBoy);
    }

    public ParkingBoy addParkingBoy(ParkingBoy parkingBoy) {
        if(userRepository.findByUserName(parkingBoy.getName()) == null){
            User user = new User(parkingBoy.getName(),MD5Utils.MD5("123"),Constant.ROLE_USER_PARKING_BOY);
            userRepository.save(user);
            return parkingBoyRepository.save(parkingBoy);
        }
        else {
            throw new CustomException(ExceptionEnum.Register_User_Exist_Exception.getMessage(),ExceptionEnum.Register_User_Exist_Exception.getCode());
        }
    }

    public void deleteParkingBoy(Long id) {
        try {
            parkingBoyRepository.deleteById(id);
        } catch (Exception e) {
            throw new CustomException(Delete_Not_Found_Exception.getMessage(), Delete_Not_Found_Exception.getCode());
        }

    }
}
