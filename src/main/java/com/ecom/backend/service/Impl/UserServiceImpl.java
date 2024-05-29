package com.ecom.backend.service.Impl;

import com.ecom.backend.dto.UserDto;
import com.ecom.backend.entity.User;
import com.ecom.backend.repository.UserRepository;
import com.ecom.backend.service.facade.UserService;
import com.ecom.backend.transformer.RoleTransformer;
import com.ecom.backend.transformer.UserTransformer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserTransformer userTransformer;
    private final UserRepository userDao;
    private final RoleTransformer roleTransformer;

    @Override
    public List<UserDto> findAll() {
        log.info("Fetching all users");
        List<User> users=userDao.findAll();
        if(users.isEmpty()){
            throw new RuntimeException("List of users is Empty");
        }
        return userTransformer.toDto(users);
    }

    @Override
    public UserDto save(UserDto userDto) {
        log.info("Attempting to create user with username: {}", userDto.username());
        if(userDto.username() != null)
        {
            boolean userExists = userDao.findByUsername(userDto.username()).isPresent();
            if(userExists) {
                throw new RuntimeException("This username is already used");
            }
        }
        try{
            User user = userTransformer.toEntity(userDto);
            log.info("User created successfully with username: {}", userDto.username());
            return userTransformer.toDto(userDao.save(user));
        }catch (Exception ex)
        {
            log.error("Error occurred while creating user with username: {}", userDto.username(), ex);
            throw new RuntimeException("An unexpected error occurred while creating the User."+ ex);
        }
    }

    @Override
    public UserDto findById(Long id) {

        log.info("Fetching user by ID: {}", id);
        return userDao.findById(id)
                .map(userTransformer::toDto)
                .orElseThrow(() -> {
                    log.error("User not found with ID: {}", id);
                    return new RuntimeException("Unable to find a User with the given Id : "+id);
                });
    }

    @Override
    public UserDto findByUsername(String username) {
        log.info("Fetching user by username: {}", username);

        return userDao.findByUsername(username)
                .map(userTransformer::toDto)
                .orElseThrow(() -> {
                    log.error("User not found with username: {}", username);
                    return  new RuntimeException("Unable to find a User with the given username : "+username);
                });
    }

    @Override
    public UserDto update(UserDto userDto, Long id) {
        log.info("Updating user with ID: {}", userDto.id());

        Long userId = userDto.id();
        UserDto existingUserDto = findById(userId);
        User existingUser = userTransformer.toEntity(existingUserDto);
        existingUser.setId(userDto.id());
        existingUser.setUsername(userDto.username());
        existingUser.setPassword(userDto.password());
        existingUser.setRoles(roleTransformer.toEntitySet(userDto.roleDtos()));

        log.info("User updated successfully with ID: {}", userDto.id());
        return userTransformer.toDto(userDao.save(existingUser));
    }

    @Override
    public void deleteById(Long id) {

        log.info("Deleting user with ID: {}", id);

        UserDto foundUser = findById(id);
        userDao.deleteById(foundUser.id());
        log.info("User deleted successfully with ID: {}", id);
    }
}
