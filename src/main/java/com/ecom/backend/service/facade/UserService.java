package com.ecom.backend.service.facade;

import com.ecom.backend.dto.UserDto;

public interface UserService extends AbstractService<UserDto, Long> {
    UserDto findByUsername(String username);
}
