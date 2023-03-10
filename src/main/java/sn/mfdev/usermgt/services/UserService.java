package sn.mfdev.usermgt.services;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sn.mfdev.usermgt.Models.SecurityUser;
import sn.mfdev.usermgt.Models.UserModel;
import sn.mfdev.usermgt.Models.UserRole;
import sn.mfdev.usermgt.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Getter
@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleService roleService;

    public List<UserModel> allUsers(){
        
        return this.repository.findAll();
    }

    public UserModel findAUser(Long id){
        Optional<UserModel>  user= this.repository.findById(id);
        if(user.isPresent())
            return user.get();
        return null;
    }
    public UserModel findByUsername(String name){
        Optional<UserModel> userModel = repository.findByName(name);
        if(userModel.isPresent())
            return userModel.get();
        return  null;
    }

    public UserModel  addUser(UserModel userModel){
        return repository.save(userModel);
    }

    public void deleteUser(Long id ){
        repository.deleteById(id);
    }

    public UserModel updateUser(Long id,UserModel newUser){
        Optional<UserModel> user = repository.findById(id);
        if(user.isPresent()){
            UserModel userModel = user.get();
            userModel.setEmail(newUser.getEmail()!=null? newUser.getEmail() : userModel.getEmail() );
            userModel.setName(newUser.getName()!=null? newUser.getName() : userModel.getName());
            userModel.setAge(newUser.getAge() != 0 ? newUser.getAge() : userModel.getAge());
            userModel.setPassword(newUser.getPassword()!= null?newUser.getPassword(): userModel.getPassword() );
            userModel.setRole(newUser.getRole()!= null? newUser.getRole() : userModel.getRole());
            return repository.save(userModel);
        }
        return null;
    }

    public  List<UserModel> getUserByRole(String role){
        return  repository.getUserModelByRole(role);
    }

    public List<UserRole> getAllRoles(){
        return  roleService.getRoles();
    }

    @Override
    public UserModel loadUserByUsername(String username) throws UsernameNotFoundException {
//        System.out.println("email:"+username);
        return repository
                .findByEmail(username)
                .map(SecurityUser::new)
                .orElseThrow(()-> new UsernameNotFoundException("username not found"+username));
    }
}