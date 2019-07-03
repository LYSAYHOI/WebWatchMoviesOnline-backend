package com.kltn.ServiceImpl;

import com.kltn.Model.Role;
import com.kltn.Model.RoleName;
import com.kltn.Repository.IRoleRepository;
import com.kltn.Service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private IRoleRepository roleRepository;

    @Override
    @Transactional
    public Role addRole(RoleName roleName) {
        Role newRole = new Role();
        newRole.setRoleName(roleName);
        return roleRepository.addRole(newRole);
    }

    @Override
    public boolean checkHasRole(RoleName roleName){
        Role role = new Role();
        role.setRoleName(roleName);
        return roleRepository.checkHasRole(role);
    }

    @Override
    public Role getRole(RoleName roleName) {
        return roleRepository.getRole(roleName);
    }

}
