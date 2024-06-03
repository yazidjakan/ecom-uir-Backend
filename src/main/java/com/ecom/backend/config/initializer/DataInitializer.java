package com.ecom.backend.config.initializer;

import com.ecom.backend.entity.Role;
import com.ecom.backend.entity.User;
import com.ecom.backend.repository.RoleRepository;
import com.ecom.backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final UserRepository userDao;
    private final RoleRepository roleDao;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Role adminRole;
        Role vendeurRole;
        Role clientRole;

        var roleOpt = roleDao.findByName("ROLE_ADMIN");
        var roleVend=roleDao.findByName("ROLE_VENDEUR");
        var roleCl=roleDao.findByName("ROLE_CLIENT");
        if (roleOpt.isPresent() && roleVend.isPresent()) {
            adminRole = roleOpt.get();
            vendeurRole = roleVend.get();
        } else {
            adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");
            adminRole = roleDao.save(adminRole);

            vendeurRole= new Role();
            vendeurRole.setName("ROLE_VENDEUR");
            vendeurRole = roleDao.save(vendeurRole);
        }

        if (userDao.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));

            Set<Role> adminRoles = new HashSet<>();
            adminRoles.add(adminRole);
            admin.setRoles(adminRoles);

            userDao.save(admin);
        }
        if (userDao.findByUsername("vendeur").isEmpty()) {
            User vendeur = new User();
            vendeur.setUsername("vendeur");
            vendeur.setPassword(passwordEncoder.encode("vendeur"));

            Set<Role> vendeurRoles = new HashSet<>();
            vendeurRoles.add(adminRole);
            vendeur.setRoles(vendeurRoles);

            userDao.save(vendeur);
        }
        if(roleDao.findByName("ROLE_CLIENT").isEmpty()){
            Role role=new Role();
            role.setName("ROLE_CLIENT");
            roleDao.save(role);
        }
    }
}
