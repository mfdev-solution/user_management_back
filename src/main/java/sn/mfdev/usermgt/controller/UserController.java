package sn.mfdev.usermgt.controller;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import sn.mfdev.usermgt.Models.UserModel;
import sn.mfdev.usermgt.services.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/userapi")
@EnableMethodSecurity
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    @GetMapping("/users")
    public List<UserModel> getAllUsers(){
        return userService.allUsers() ;
    }

    @GetMapping("/users/{id}")
    public UserModel getAUser(@PathVariable (value = "id") Long id){
        return  userService.findAUser(id);
    }
    @PostMapping("/users")
    public UserModel addUser(@RequestBody UserModel userModel){
        return  userService.addUser(userModel);
    }
    @PutMapping("/users/{id}")
    public UserModel updateUser(@PathVariable("id") Long id, @RequestBody UserModel newUser){
        return  userService.updateUser(id,newUser);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
    }

    @GetMapping("/users/role/{role}")
    public List<UserModel> getUserByRole(@PathVariable("role") String role){
        return userService.getUserByRole(role);
    }


}
