package com.hcmute.bookstoreapplication.services.user;

import com.hcmute.bookstoreapplication.dtos.UserDTO;
import com.hcmute.bookstoreapplication.dtos.response.*;
import com.hcmute.bookstoreapplication.entities.User;
import com.hcmute.bookstoreapplication.exceptions.InvalidException;
import com.hcmute.bookstoreapplication.exceptions.NotFoundException;
import com.hcmute.bookstoreapplication.repositories.UserRepository;
import com.hcmute.bookstoreapplication.utils.EnumRole;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JavaMailSender mailSender;
    public void sendEmail(String toEmail, String subject, String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("baovan301@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        mailSender.send(message);
        System.out.println("Mail sent successfully....");
    }
    @Override
    public BaseResponse createUser(UserDTO userDTO) {
        int OTP = 5;
        User user = new User();
        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0','9').build();
        Optional<User> existingUser = userRepository.findByEmail(userDTO.getEmail());
        if(existingUser.isPresent()){
            throw new InvalidException("Tài khoản đã tồn tại");
        }
//        user.setId(userDTO.getId());
        user.setDefaultAddress(userDTO.getAddress());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setRoles(EnumRole.USER);
        user.setIsActive(false);
        user.setVerificationCode(generator.generate(OTP));

        sendEmail(userDTO.getEmail(),"OTP CODE FOR REGISTER","Here is your OTP Code: " + user.getVerificationCode());
        userRepository.save(user);
        return new BaseResponse(true,"Đã gởi mã OTP qua email");
    }

    @Override
    public UserLoginResponse login(UserLoginResponse userLoginResponse) {
        UserLoginResponse response = new UserLoginResponse();
        Optional<User> user = userRepository.findByEmail(userLoginResponse.getEmail());
        if (!user.isPresent()){
            response.setStatus("Email này chưa đăng ký");
            System.out.println(response.getEmail());
            System.out.println(response.getEmail());
            System.out.println(response.getPassword());
            System.out.println(response.getStatus());
        }
        else{
            if(user.get().getIsActive() == false){
                    response.setStatus("Tài khoản chưa được kích hoạt");
                    System.out.println(response.getEmail());
                    System.out.println(response.getPassword());
                    System.out.println(response.getStatus());
            }
            else if (!user.get().getPassword().equals(userLoginResponse.getPassword())) {
                response.setStatus("Mật khẩu sai");
                System.out.println(response.getEmail());
                System.out.println(response.getPassword());
                System.out.println(response.getStatus());
            }
            else{
                response.setEmail(user.get().getEmail());
                response.setPassword(user.get().getPassword());
                response.setId(user.get().getId());
                response.setStatus("Đăng nhập thành công");
            }
        }
//        List<User> users = userRepository.findAll();
//        for(User user:users){
//            if(user.getEmail().equals(userLoginResponse.getEmail())){
//                if(user.getIsActive() == false){
//                    response.setStatus("Tài khoản chưa được kích hoạt");
//                    System.out.println(response.getEmail());
//                    System.out.println(response.getPassword());
//                    System.out.println(response.getStatus());
//                }
//                else if (!user.getPassword().equals(userLoginResponse.getPassword())) {
//                    response.setStatus("Mật khẩu sai");
//                    System.out.println(response.getEmail());
//                    System.out.println(response.getPassword());
//                    System.out.println(response.getStatus());
//                }
//                else{
//                    response.setEmail(user.getEmail());
//                    response.setPassword(user.getPassword());
//                    response.setId(user.getId());
//                    response.setStatus("Đăng nhập thành công");
//                }
//                break;
//            }
//            else{
//                if(!user.getEmail().equals(userLoginResponse.getEmail())){
//                    response.setStatus("Email này chưa đăng ký");
//                    System.out.println(response.getEmail());
//                    System.out.println(response.getEmail());
//                    System.out.println(response.getPassword());
//                    System.out.println(response.getStatus());
//                }
//            }
//        }
        return response;
    }

    @Override
    public UserRegisterOtpRespone otp(UserRegisterOtpRespone userRegisterOtpRespone) {
        UserRegisterOtpRespone respone = new UserRegisterOtpRespone();
        Optional<User> user = userRepository.findByEmail(userRegisterOtpRespone.getEmail());
        if(!user.get().getVerificationCode().equals(userRegisterOtpRespone.getOtpCode())){
            respone.setStatus("Sai OTP");
            respone.setEmail(user.get().getEmail());
        }
        else{
            user.get().setVerificationCode(null);
            user.get().setIsActive(true);
            userRepository.save(user.get());
            respone.setStatus("Tài khoản được kích hoạt");
            respone.setEmail(user.get().getEmail());
            respone.setOtp(true);
            respone.setOtpCode(user.get().getVerificationCode());
        }
//        for(User user:users){
//            if(user.getEmail().equals(userRegisterOtpRespone.getEmail())){
//                if(!user.getVerificationCode().equals(userRegisterOtpRespone.getOtpCode())){
//                    respone.setStatus("Sai OTP");
//                    respone.setEmail(user.getEmail());
//                }
//                else{
//                    user.setVerificationCode(null);
//                    user.setIsActive(true);
//                    userRepository.save(user);
//                    respone.setStatus("Tài khoản được kích hoạt");
//                    respone.setEmail(user.getEmail());
//                    respone.setOtp(true);
//                    respone.setOtpCode(user.getVerificationCode());
//                }
//            }
//        }
        return respone;
    }

    @Override
    public UserForgetPasswordResponse forgetPassword(UserForgetPasswordResponse userForgetPasswordResponse) {
        int OTP = 5;
        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0','9').build();
        UserForgetPasswordResponse response = new UserForgetPasswordResponse();
        Optional<User> user = userRepository.findByEmail(userForgetPasswordResponse.getEmail());
        if(!user.isPresent()){
            response.setStatus("Email này chưa đăng ký");
        }
        else{
            response.setEmail(user.get().getEmail());
            response.setStatus("Đã gởi mã OTP về cho gmail");
            user.get().setVerificationCode(generator.generate(OTP));
            sendEmail(user.get().getEmail(),"OTP CODE FOR RESET PASSWORD","Here is your OTP Code: " + user.get().getVerificationCode());
            userRepository.save(user.get());
        }
//        List<User> users = userRepository.findAll();
//        for(User user:users){
//            if(!user.getEmail().equals(userForgetPasswordResponse.getEmail())){
//                response.setStatus("Email này chưa được đăng ký");
//            }
//            else {
//                response.setEmail(user.getEmail());
//                response.setStatus("Đã gởi mã OTP về cho gmail");
//                user.setVerificationCode(generator.generate(OTP));
//                sendEmail(user.getEmail(),"OTP CODE FOR RESET PASSWORD","Here is your OTP Code: " + user.getVerificationCode());
//                userRepository.save(user);
//                break;
//            }
//        }
        return response;
    }

    @Override
    public UserResetPasswordResponse resetPassword(UserResetPasswordResponse userResetPasswordResponse) {
//        List<User> users = userRepository.findAll();
        Optional<User> user = userRepository.findByEmail(userResetPasswordResponse.getEmail());
        UserResetPasswordResponse response = new UserResetPasswordResponse();
        if(user.get().getVerificationCode().equals(userResetPasswordResponse.getOtpCode())){
            response.setStatus("Đổi mật khẩu thành công");
            response.setOtpCode(user.get().getVerificationCode());
            response.setEmail(user.get().getEmail());
            response.setNewPassword(userResetPasswordResponse.getNewPassword());
            user.get().setVerificationCode(null);
            user.get().setPassword(userResetPasswordResponse.getNewPassword());
            userRepository.save(user.get());
        }else{
            response.setStatus("Mã OTP Sai");
        }
//        for(User user:users){
//            if(user.getEmail().equals(userResetPasswordResponse.getEmail())){
//                if(user.getVerificationCode().equals(userResetPasswordResponse.getOtpCode())){
//                    response.setStatus("Đổi mật khẩu thành công");
//                    response.setOtpCode(user.getVerificationCode());
//                    response.setEmail(user.getEmail());
//                    response.setNewPassword(userResetPasswordResponse.getNewPassword());
//                    user.setVerificationCode(null);
//                    user.setPassword(userResetPasswordResponse.getNewPassword());
//                    userRepository.save(user);
//                }
//                else{
//                    response.setStatus("Mã OTP Sai");
//                }
//                break;
//            }
//
//        }
        return response;
    }

    @Override
    public UserDTO getUser(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new RuntimeException("Product not found with id: "+id);
        }
        UserDTO userDTO = new UserDTO(user.get());
        return userDTO;
    }
}
