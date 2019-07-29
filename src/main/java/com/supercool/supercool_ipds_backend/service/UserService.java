package com.supercool.supercool_ipds_backend.service;

import com.supercool.supercool_ipds_backend.common.exception.CustomException;
import com.supercool.supercool_ipds_backend.common.exception.ExceptionEnum;
import com.supercool.supercool_ipds_backend.model.User;
import com.supercool.supercool_ipds_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User UserLogin(String userName,String password){
        User user = userRepository.findByUserName(userName);
        if(user == null) {
            throw new CustomException(ExceptionEnum.User_Not_Exist_Exception.getMessage(),ExceptionEnum.User_Not_Exist_Exception.getCode());
        }
        else{
            if(user.getPassword().equalsIgnoreCase(password)){
                return user;
            }
            else{
                throw new CustomException(ExceptionEnum.User_Account_Error_Exception.getMessage(),ExceptionEnum.User_Account_Error_Exception.getCode());
            }
        }
    }
}
