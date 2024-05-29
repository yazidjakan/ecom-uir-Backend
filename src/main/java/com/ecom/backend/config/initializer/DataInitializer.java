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

        var roleOpt = roleDao.findByName("ROLE_ADMIN");
        if (roleOpt.isPresent()) {
            adminRole = roleOpt.get();
        } else {
            adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");
            adminRole = roleDao.save(adminRole);
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
    }
}
