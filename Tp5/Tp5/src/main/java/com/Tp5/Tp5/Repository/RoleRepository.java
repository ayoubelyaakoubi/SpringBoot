package com.Tp5.Tp5.Repository;

import com.Tp5.Tp5.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}