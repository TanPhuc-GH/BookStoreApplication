package com.hcmute.bookstoreapplication.controllers;

import com.hcmute.bookstoreapplication.dtos.UserDTO;
import com.hcmute.bookstoreapplication.dtos.UserInfoDTO;
import com.hcmute.bookstoreapplication.dtos.response.*;
import com.hcmute.bookstoreapplication.entities.User;
import com.hcmute.bookstoreapplication.services.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteUser(@PathVariable Integer id){
        try{
            return ResponseEntity.ok(userService.deleteUser(id));
        }catch (RuntimeException e){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new BaseResponse(false,e.getMessage()));
        }
    }
    @PutMapping("/update")
    public ResponseEntity<BaseResponse> updateUser(@RequestBody UserInfoDTO userInfoDTO){
        try{
            return ResponseEntity.ok(userService.updateUser(userInfoDTO));
        }catch (RuntimeException e){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new BaseResponse(false, e.getMessage()));
        }
    }
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserDTO userDTO){
        return new ResponseEntity<>(userService.createUser(userDTO), HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginResponse userLoginResponse){
        UserLoginResponse response = userService.login(userLoginResponse);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/otp")
    public ResponseEntity<UserRegisterOtpRespone> otp(@RequestBody UserRegisterOtpRespone userRegisterOtpRespone){
        UserRegisterOtpRespone response = userService.otp(userRegisterOtpRespone);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/forgetPassword")
    public ResponseEntity<UserForgetPasswordResponse> forgetPassword(@RequestBody UserForgetPasswordResponse userForgetPasswordResponse){
        UserForgetPasswordResponse response = userService.forgetPassword(userForgetPasswordResponse);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/resetPassword")
    public ResponseEntity<UserResetPasswordResponse> resetPassword(@RequestBody UserResetPasswordResponse userResetPasswordResponse){
        UserResetPasswordResponse response = userService.resetPassword(userResetPasswordResponse);
        return ResponseEntity.ok(response);
    }
   @GetMapping("/all")
   public ResponseEntity<List<UserInfoDTO>> getAllUser(){
       return new ResponseEntity<>(userService.getAllUser(),HttpStatus.OK);
   }

   @GetMapping("/{userId}")
   public ResponseEntity<UserDTO> getUser(@PathVariable Integer userId){
       return new ResponseEntity<>(userService.getUser(userId),HttpStatus.OK);
   }

}
