package com.hcmute.bookstoreapplication.services.user;

import com.hcmute.bookstoreapplication.dtos.UserDTO;
import com.hcmute.bookstoreapplication.dtos.response.UserLoginResponse;
import com.hcmute.bookstoreapplication.dtos.response.UserRegisterOtpRespone;
import com.hcmute.bookstoreapplication.entities.User;

public interface UserService {
    User createUser(UserDTO userDTO);
    UserLoginResponse login(UserLoginResponse userLoginResponse);
    UserRegisterOtpRespone otp(UserRegisterOtpRespone userRegisterOtpRespone);
}
