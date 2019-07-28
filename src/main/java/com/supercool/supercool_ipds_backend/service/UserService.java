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
        try{
            User user = userRepository.findByUserName(userName);
            if(user == null){
                throw new CustomException(ExceptionEnum.USER_NOT_EXIST_Exception.getMessage(),ExceptionEnum.USER_NOT_EXIST_Exception.getCode());
            }
            else{
                if(user.getPassword().equalsIgnoreCase(password)){
                    return user;
                }
                else{
                    throw new CustomException(ExceptionEnum.USER_ACCOUNT_ERROR_Exception.getMessage(),ExceptionEnum.USER_ACCOUNT_ERROR_Exception.getCode());
                }
            }
        }
        catch (Exception e){
            throw new CustomException(ExceptionEnum.USER_NOT_EXIST_Exception.getMessage(),ExceptionEnum.USER_NOT_EXIST_Exception.getCode());
        }
    }
}
