package sn.mfdev.usermgt.Models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "role")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Data
@Getter
public class UserRole {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    private String name;
}
