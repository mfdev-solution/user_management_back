package sn.mfdev.usermgt.Models;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import sn.mfdev.usermgt.doa.UserDao;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
public class UserModel  implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private int age;
    private String password;
//    @Enumerated(EnumType.STRING)
//    private Role role;
    private  String role;




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        System.out.println(role);
        return Arrays.stream(
                        getRole()
                        .split(","))
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
