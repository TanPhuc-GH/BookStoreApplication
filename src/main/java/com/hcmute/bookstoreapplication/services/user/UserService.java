package com.hcmute.bookstoreapplication.services.user;

import com.hcmute.bookstoreapplication.dtos.UserDTO;
import com.hcmute.bookstoreapplication.dtos.response.*;
import com.hcmute.bookstoreapplication.entities.User;

public interface UserService {
    BaseResponse createUser(UserDTO userDTO);
    UserLoginResponse login(UserLoginResponse userLoginResponse);
    UserRegisterOtpRespone otp(UserRegisterOtpRespone userRegisterOtpRespone);
    UserForgetPasswordResponse forgetPassword(UserForgetPasswordResponse userForgetPasswordResponse);
    UserResetPasswordResponse resetPassword(UserResetPasswordResponse userResetPasswordResponse);
    UserDTO getUser(Integer id);
}
