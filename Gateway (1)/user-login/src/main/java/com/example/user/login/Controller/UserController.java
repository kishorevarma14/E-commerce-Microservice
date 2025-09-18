package com.example.user.login.Controller;

import com.example.user.login.Entity.User;
import com.example.user.login.Entity.UserDTO;
import com.example.user.login.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/login")
    public String login(@RequestBody UserDTO user)
    {
        return userService.validatetoken(user);
    }
    @PostMapping("/register")
    public String Create(@RequestBody User user)
    {
        return userService.createuser(user);
    }
}
