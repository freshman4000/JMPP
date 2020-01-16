package com.freshman4000.restserver.repositories;

import com.freshman4000.restserver.models.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends CrudRepository<Role, Long> {

    Role findByName(String name);
}
