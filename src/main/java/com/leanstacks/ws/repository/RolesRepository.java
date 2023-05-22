package com.leanstacks.ws.repository;

import com.leanstacks.ws.model.City;
import com.leanstacks.ws.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {
}