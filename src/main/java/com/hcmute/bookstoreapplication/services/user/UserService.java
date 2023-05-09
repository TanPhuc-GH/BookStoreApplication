package com.hcmute.bookstoreapplication.services.user;

import com.hcmute.bookstoreapplication.dtos.UserDTO;
import com.hcmute.bookstoreapplication.dtos.response.UserForgetPasswordResponse;
import com.hcmute.bookstoreapplication.dtos.response.UserLoginResponse;
import com.hcmute.bookstoreapplication.dtos.response.UserRegisterOtpRespone;
import com.hcmute.bookstoreapplication.dtos.response.UserResetPasswordResponse;
import com.hcmute.bookstoreapplication.entities.User;

public interface UserService {
    User createUser(UserDTO userDTO);
    UserLoginResponse login(UserLoginResponse userLoginResponse);
    UserRegisterOtpRespone otp(UserRegisterOtpRespone userRegisterOtpRespone);
    UserForgetPasswordResponse forgetPassword(UserForgetPasswordResponse userForgetPasswordResponse);
    UserResetPasswordResponse resetPassword(UserResetPasswordResponse userResetPasswordResponse);
    UserDTO getUser(Integer id);
}
