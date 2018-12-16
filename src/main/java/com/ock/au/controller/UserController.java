package com.ock.au.controller;

import com.ock.au.component.tcp.Message;
import com.ock.au.entity.Lot;
import com.ock.au.entity.User;
import com.ock.au.service.LotService;
import com.ock.au.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "signup")
    @ResponseBody
    public Message signup(@RequestParam User user) {
        Message message = new Message();
        if (userService.signup(user)) {
            message.setMessage("Sign up successfully");
            message.setStatus("success");
            return message;
        }

        message.setErrors(userService.getUserValidator().getErrors().getAllErrors());
        return new Message("Cannot sign up", "error");
    }
}
