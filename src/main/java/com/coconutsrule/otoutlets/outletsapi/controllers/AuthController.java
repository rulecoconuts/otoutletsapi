package com.coconutsrule.otoutlets.outletsapi.controllers;

import java.net.URI;
import com.coconutsrule.otoutlets.outletsapi.dao.UserDao;
import com.coconutsrule.otoutlets.outletsapi.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    UserDao userDao;

    @PostMapping("/register")
    public ResponseEntity register(User user){
        userDao.create(user);
        return ResponseEntity.ok().build();
    }
}
