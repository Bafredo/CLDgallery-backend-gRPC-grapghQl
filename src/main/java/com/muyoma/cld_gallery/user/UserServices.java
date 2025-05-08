package com.muyoma.cld_gallery.user;

import com.muyoma.cld_gallery.jwt.JwtUtil;
import com.muyoma.cld_gallery.user.UserAuthModels.UserLogin;
import com.muyoma.cld_gallery.user.UserAuthModels.UserLoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
@Component
public class UserServices {

    private final UserRepository userRepository;

    private JwtUtil jwtUtil;

    @Autowired
    public UserServices(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public boolean addUser(User user) {
        userRepository.save(user);
        return true;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public User findUserByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User deleteUser(String email) {
        User user = findUserByEmail(email);
        userRepository.delete(user);
        return user;
    }

    public User updateUser(Long id, User user) {
        if(userRepository.findById(id).isPresent()) {
            User updatedUser = userRepository.findById(id).get();
            updatedUser.setFirstName(user.getFirstName());
            updatedUser.setLastName(user.getLastName());
            updatedUser.setEmail(user.getEmail());
            updatedUser.setPhoneNumber(user.getPhoneNumber());
            return userRepository.save(updatedUser);
        } else{
            return null;
        }
    }
    public UserLoginResponse login(UserLogin user) {
        User u = findUserByEmail(user.mail);
        if(u != null) {
            if (u.password.equals(user.password)) {
                return new UserLoginResponse(
                        u.email,
                        jwtUtil.generateToken(u.email)
                );
            }
        }
        return null;
    }
    public boolean validateVerificationCode(String email,int verificationCode) {
        User user = userRepository.findByEmail(email);
        if(user != null) {
            if (user.validationCode == verificationCode) {
                user.setVerificationStatus(true);
                userRepository.save(user);
                return true;

            }
        }
        return false;
    }
}