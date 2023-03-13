package sn.mfdev.usermgt.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Builder
@AllArgsConstructor
@CrossOrigin("*")
@NoArgsConstructor
public class User {
    private Long id;
    private String name;
    private String email;
    private int age;
    private String password;
    //    @Enumerated(EnumType.STRING)
//    private Role role;
    private  String role;
}
