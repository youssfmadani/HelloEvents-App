package com.example.helloevents.controllers;

import com.example.helloevents.models.User;
import com.example.helloevents.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public List<User> getAllClients() {
        return userService.getAllUsers();
    }
}