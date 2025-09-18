package com.example.user.login.Service;


//import com.example.user.login.Interface.Feign;
import com.example.user.login.Entity.User;
//import com.example.user.login.Interface.Feign;
import com.example.user.login.Entity.UserDTO;
import com.example.user.login.Repository.UserRepository;
import com.example.user.login.Security.Jwtfilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    Jwtfilter jwtfilter;
    public String createuser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new ArrayList<>(List.of("ROLE_USER","ROLE_ADMIN")));
        userRepository.save(user);
        return "Saved";
    }
    public String validatetoken(UserDTO user) {
        Authentication authenication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        if(authenication.isAuthenticated())
        {
            User user1=userRepository.findByUsername(user.getUsername());
            return jwtfilter.generateToken(user1);
        }
        return "Incorrect Credentials";
    }
}
