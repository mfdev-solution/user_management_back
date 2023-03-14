package sn.mfdev.usermgt.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;
import sn.mfdev.usermgt.Models.UserModel;
import sn.mfdev.usermgt.services.UserService;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/userapi")
@EnableMethodSecurity
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<UserModel> getAllUsers(){
        return userService.allUsers() ;
    }

    @GetMapping("/users/{id}")
    public UserModel getAUser(@PathVariable (value = "id") Long id){
        return  userService.findAUser(id);
    }
//    @GetMapping("/users/{email}")
//    public UserModel findUserByEmail(@PathVariable(value = "email") String email){
//        return  userService.findByEmail(email);
//    }
    @PostMapping("/users/email")
    public  UserModel getUserByEmail(@RequestBody UserModel userModel){
        return  userService.findByEmail(userModel.getEmail());
    }
    @PreAuthorize("hasAuthority('ADMIN')")
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
