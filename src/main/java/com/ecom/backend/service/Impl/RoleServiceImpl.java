package com.ecom.backend.service.Impl;

import com.ecom.backend.dto.RoleDto;
import com.ecom.backend.entity.Role;
import com.ecom.backend.entity.User;
import com.ecom.backend.repository.RoleRepository;
import com.ecom.backend.service.facade.RoleService;
import com.ecom.backend.transformer.RoleTransformer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleDao;
    private final RoleTransformer roleTransformer;
    @Override
    public RoleDto findById(Long id) {
        log.info("Fetching role by ID: {}", id);
        return roleDao.findById(id)
                .map(roleTransformer::toDto)
                .orElseThrow(() ->{
                    log.error("Role not found with ID: {}", id);
                    return new RuntimeException("Unable to find a role with the given Id : "+id);
                });
    }

    @Override
    public List<RoleDto> findAll() {
        List<Role> roles=roleDao.findAll();
        if(roles.isEmpty()){
            throw new RuntimeException("List of roles is Empty");
        }
        return roleTransformer.toDto(roles);
    }

    @Override
    public RoleDto save(RoleDto dto) {
        log.info("Creating new role with name: {}", dto.name());

        if(roleDao.findByName(dto.name()).isPresent())
        {
            log.warn("Attempted to create a duplicate role with name: {}", dto.name());
            throw new RuntimeException("This role name already exists");
        }
        try {
            Role role = roleTransformer.toEntity(dto);
            log.info("Successfully created role with name: {}", dto.name());
            return roleTransformer.toDto(roleDao.save(role));

        }catch (Exception ex)
        {
            log.error("Error occurred while creating role with name: {}", dto.name(), ex);
            throw new RuntimeException("An unexpected error occurred while creating the role."+ ex);
        }
    }

    @Override
    public RoleDto update(RoleDto dto, Long id) {
        id = dto.id();
        RoleDto existingRoleDto = findById(id);
        Role existingRole = roleTransformer.toEntity(dto);
        existingRole.setId(dto.id());
        existingRole.setName(dto.name());

        log.info("Successfully updated role with ID: {}", dto.id());
        return roleTransformer.toDto(roleDao.save(existingRole));
    }

    @Override
    public void deleteById(Long id) {
        log.info("Deleting role with ID: {}", id);
        RoleDto foundRole = findById(id);
        roleDao.deleteById(foundRole.id());

        log.info("Successfully deleted role with ID: {}", id);

    }
}
