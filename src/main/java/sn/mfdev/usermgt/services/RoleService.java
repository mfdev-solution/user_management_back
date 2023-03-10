package sn.mfdev.usermgt.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.mfdev.usermgt.Models.UserRole;
import sn.mfdev.usermgt.repository.RoleRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor

public class RoleService {

    @Autowired
    private RoleRepository roleRepository;


    public List<UserRole> getRoles(){
        return  roleRepository.findAll();
    }

    public UserRole getRole(Long id){
        Optional<UserRole> newUSer =  roleRepository.findById(id);
        if(newUSer.isPresent()){
            return newUSer.get();
        }
        return null;
    }
}
