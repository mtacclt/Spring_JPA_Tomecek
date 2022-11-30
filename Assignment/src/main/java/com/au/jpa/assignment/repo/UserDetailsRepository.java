package com.au.jpa.assignment.repo;

import com.au.jpa.assignment.model.Login;
import com.au.jpa.assignment.model.UserDetails;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails,Long> {

    @Query("select ud from UserDetails ud where ud.id = :id")
    UserDetails findByIdCustomQuery(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("delete from UserDetails ud where ud.id=:id")
    void deleteByIdCustomQuery(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "insert into UserDetails (loginid,name,address,contactnumber,accesslevel) values (null,:name,:address,:contactNumber,:accessLevel)",
            nativeQuery = true)
    void insertCustomQuery(String name,String address,String contactNumber,String accessLevel);
}
