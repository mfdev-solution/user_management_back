package sn.mfdev.usermgt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.mfdev.usermgt.Models.UserRole;
@Repository
public interface RoleRepository extends JpaRepository< UserRole , Long> {

}
