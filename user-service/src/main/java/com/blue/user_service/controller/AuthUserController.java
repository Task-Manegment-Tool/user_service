package com.blue.user_service.controller;


import com.blue.user_service.entity.Role;
import com.blue.user_service.repository.UserRepository;
import com.blue.user_service.entity.User;
import com.blue.user_service.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/private/users")
public class AuthUserController {




    @Autowired
    private UserServiceImpl userService;

    Logger logger = Logger.getLogger(AuthUserController.class.getName());



    @PostMapping("/update_user/{userId}")
    public ResponseEntity<User> updateUserbyId(@PathVariable long userId, @RequestBody User user) {

    logger.info("updateUserbyId method called ");
    user.setId(userId);
        userService.updateUserbyId(userId,user);
        return ResponseEntity.ok(user);

    }

    @PostMapping("/assign_role/{userId}")
    public ResponseEntity<String> assignRoleToUser(@PathVariable long userId, @RequestBody Role role) {

        logger.info("assignRoleToUser method called ");
        String roleTile =role.getName();
        User user=  userService.assignRoleToUser(userId,roleTile);
        return ResponseEntity.ok(user.getUsername()+" is Promoted.");
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {

        logger.info("Error handleRuntimeException method called ");
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
