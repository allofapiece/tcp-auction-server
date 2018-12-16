package com.ock.au.component.mvc;

import com.ock.au.component.ActionDispatcher;
import com.ock.au.component.tcp.Message;
import com.ock.au.dao.UserDao;
import com.ock.au.entity.*;
import com.ock.au.exception.NotFoundRouteException;
import com.ock.au.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class ActionDispatcherTest {

    @Autowired
    private ActionDispatcher actionDispatcher;

    @Autowired
    private UserService userService;

    @Test
    void sendValidMessageToDispather() {
        Message message = new Message();
        User user = new User();
        user.setName("fff");
        user.setSurname("fff");
        user.setEmail("efef@efefef.ef");
        user.setPassword("efefefef");
        user.setStatus(UserStatus.DISABLED);
        user.setAdmin(true);
        Lot lot = new Lot();
        lot.setName("Hi");
        lot.setEndTime(new Timestamp(new Date().getTime()));
        lot.setSeller(user);
        lot.setStatus(LotStatus.CLOSED);

        message.addPayload("user", user);

        try {
            Message receiveMessage = actionDispatcher.dispatch("users/signup", message);
            List<User> users = userService.findAll();
            System.out.println("fef");
        } catch (NotFoundRouteException e) {
            e.printStackTrace();
        }
    }

    @Test
    void dispatch1() {
    }
}