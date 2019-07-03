package com.kltn;

import com.kltn.Model.Role;
import com.kltn.Model.RoleName;
import com.kltn.Model.User;
import com.kltn.Service.IRoleService;
import com.kltn.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

/**
 * Hello world!
 *
 */

@SpringBootApplication
public class App 
{

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private PasswordEncoder pe;

    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void addFirstAdminAcc(){
        if(!roleService.checkHasRole(RoleName.ADMIN)){
            roleService.addRole(RoleName.ADMIN);
        }

        if(!roleService.checkHasRole(RoleName.MEMBER)){
            roleService.addRole(RoleName.MEMBER);
        }

        if (userService.checkIfExistAdmin() == 0) {
            User admin = new User();
            admin.setName("admin");
            admin.setUsername("admin");
            Set<Role> setRole = new HashSet<>();
            setRole.add(new Role(RoleName.ADMIN));
            setRole.add(new Role(RoleName.MEMBER));
            admin.setRoles(setRole);
            admin.setPassword(pe.encode("password"));
            userService.addAdmin(admin);
        }
    }
}
