package com.au.jpa.assignment.repo;

import com.au.jpa.assignment.model.Login;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Login,Long> {

    @Query("select l from Login l where l.id = :id")
    Login findByIdCustomQuery(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("delete from Login l where l.id=:id")
    void deleteByIdCustomQuery(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "insert into logins (password,active) values (:password, :active)",
            nativeQuery = true)
    void insertCustomQuery(String password,String active);
}
