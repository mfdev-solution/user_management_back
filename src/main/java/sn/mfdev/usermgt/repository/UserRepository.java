package sn.mfdev.usermgt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import sn.mfdev.usermgt.Models.UserModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel,Long> {
    List<UserModel> getUserModelByRole(String role);
    Optional<UserModel> findByName(String name);
    Optional<UserModel> findByEmail(String email);
}
