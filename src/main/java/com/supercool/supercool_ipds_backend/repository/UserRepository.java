package com.supercool.supercool_ipds_backend.repository;

import com.supercool.supercool_ipds_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String userName);
}
