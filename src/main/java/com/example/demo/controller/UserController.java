package com.example.demo.controller;

import com.example.demo.Model.User;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @CrossOrigin
    @RequestMapping(value="/registration", method=RequestMethod.POST)
    public ResponseEntity<String> home(@RequestBody User user){
        if (userService.addNewUser(user)){
                return new ResponseEntity<>("User saved", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to save User",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @CrossOrigin
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody User user){
        if(userService.verifyCredentials(user)!=null){
            return "verified";
        }
        else{
            return "wrong credentials";
        }
    }


    @CrossOrigin
    @GetMapping("/getuser")
    public List<User> getUser()
    {
        return userService.getUser();
    }
    @CrossOrigin
    @RequestMapping(value="/postuser", method=RequestMethod.POST)
    public void registerUser(@RequestBody User user){
        userService.addNewUser(user);
    }

}
