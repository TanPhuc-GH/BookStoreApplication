package com.hcmute.bookstoreapplication.services.user;

import com.hcmute.bookstoreapplication.dtos.UserDTO;
import com.hcmute.bookstoreapplication.dtos.response.UserLoginResponse;
import com.hcmute.bookstoreapplication.entities.User;
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
    public User createUser(UserDTO userDTO) {
        int OTP = 5;
        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0','9').build();
        Optional<User> existingUser = userRepository.findByEmail(userDTO.getEmail());
        if(existingUser.isPresent()){
            throw new RuntimeException("User nay dax dang ky");
        }
        User user = new User();
        user.setId(userDTO.getId());
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
        return userRepository.save(user);
    }

    @Override
    public UserLoginResponse login(UserLoginResponse userLoginResponse) {
        UserLoginResponse response = new UserLoginResponse();
        List<User> users = userRepository.findAll();
        for(User user:users){
            if(!user.getEmail().equals(userLoginResponse.getEmail())){
                response.setStatus("Email này chưa đăng ký");
            }
            else if (!user.getPassword().equals(userLoginResponse.getPassword())) {
                response.setStatus("Mật khẩu sai");
            }
            else{
                response.setEmail(user.getEmail());
                response.setPassword(user.getPassword());
                response.setStatus("Đăng nhập thành công");
            }
        }
        return response;
    }
}
