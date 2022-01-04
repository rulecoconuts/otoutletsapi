package com.coconutsrule.otoutlets.outletsapi.controllers;

import java.net.URI;
import com.coconutsrule.otoutlets.outletsapi.dao.UserDao;
import com.coconutsrule.otoutlets.outletsapi.models.ApiUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    UserDao userDao;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody ApiUser user){
        userDao.create(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/sample")
    String sample(){
        return "Sample response";
    }
}
