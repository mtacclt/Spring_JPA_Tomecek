package com.au.jpa.assignment.repo;

import com.au.jpa.assignment.model.Login;
import com.au.jpa.assignment.model.Permission;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission,Long> {

    @Query("select p from Permission p where p.id = :id")
    Permission findByIdCustomQuery(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("delete from Permission p where p.id=:id")
    void deleteByIdCustomQuery(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "insert into permissions (userid,permissionlevel) values (:userId, :permissionLevel)",
            nativeQuery = true)
    void insertCustomQuery(Long userId,Integer permissionLevel);
}
