package com.au.jpa.Assignment.repo;

import com.au.jpa.Assignment.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Login,Long> {
}
