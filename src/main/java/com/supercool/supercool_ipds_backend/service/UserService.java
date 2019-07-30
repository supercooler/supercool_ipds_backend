package com.supercool.supercool_ipds_backend.service;

import com.supercool.supercool_ipds_backend.common.exception.CustomException;
import com.supercool.supercool_ipds_backend.common.exception.ExceptionEnum;
import com.supercool.supercool_ipds_backend.common.utils.MD5Utils;
import com.supercool.supercool_ipds_backend.dto.LoginUserDto;
import com.supercool.supercool_ipds_backend.model.User;
import com.supercool.supercool_ipds_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User UserLogin(LoginUserDto loginUser){
        User user = userRepository.findByUserName(loginUser.getUserName());
        if(user == null) {
            throw new CustomException(ExceptionEnum.User_Not_Exist_Exception.getMessage(),ExceptionEnum.User_Not_Exist_Exception.getCode());
        }
        else{
            if(user.getPassword().equalsIgnoreCase(MD5Utils.MD5(loginUser.getPassword()))){
                return user;
            }
            else{
                throw new CustomException(ExceptionEnum.User_Account_Error_Exception.getMessage(),ExceptionEnum.User_Account_Error_Exception.getCode());
            }
        }
    }

    public User UserRegister(User registerUser) {
        User user = userRepository.findByUserName(registerUser.getUserName());
        if(user == null){
            String password = registerUser.getPassword();
            registerUser.setPassword(MD5Utils.MD5(password));
            return userRepository.save(registerUser);
        }
        else {
            throw new CustomException(ExceptionEnum.Register_User_Exist_Exception.getMessage(),ExceptionEnum.Register_User_Exist_Exception.getCode());
        }
    }
}
