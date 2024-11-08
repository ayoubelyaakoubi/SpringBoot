package com.SpringBoot.Tp3.Service;

import org.springframework.stereotype.Service;

import com.SpringBoot.Tp3.DTO.RoleRepo;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepo roleRepo;

    public void deleteRoleById(String roleId) {
        roleRepo.deleteById(roleId);
    }

}