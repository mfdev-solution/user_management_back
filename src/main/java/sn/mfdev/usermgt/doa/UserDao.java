package sn.mfdev.usermgt.doa;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDao {
    private Long id;
    private String name;
    private String email;
    private int age;
    private String password;
    //    @Enumerated(EnumType.STRING)
//    private Role role;
    private  String role;
}
