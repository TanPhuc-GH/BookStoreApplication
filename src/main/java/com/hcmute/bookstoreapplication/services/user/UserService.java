package com.hcmute.bookstoreapplication.services.user;

import com.hcmute.bookstoreapplication.dtos.UserDTO;
import com.hcmute.bookstoreapplication.dtos.UserInfoDTO;
import com.hcmute.bookstoreapplication.dtos.response.*;
import com.hcmute.bookstoreapplication.entities.User;

import java.util.List;

public interface UserService {
    User createUser(UserDTO userDTO);
    UserLoginResponse login(UserLoginResponse userLoginResponse);
    UserRegisterOtpRespone otp(UserRegisterOtpRespone userRegisterOtpRespone);
    UserForgetPasswordResponse forgetPassword(UserForgetPasswordResponse userForgetPasswordResponse);
    UserResetPasswordResponse resetPassword(UserResetPasswordResponse userResetPasswordResponse);
    UserDTO getUser(Integer id);
    List<UserInfoDTO> getAllUser();
    BaseResponse updateUser(UserInfoDTO userInfoDTO);
    BaseResponse deleteUser(Integer id);

}
