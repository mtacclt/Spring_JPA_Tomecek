package com.au.jpa.assignment.service;

import com.au.jpa.assignment.model.Login;
import com.au.jpa.assignment.model.UserDetails;
import com.au.jpa.assignment.repo.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsService {
    @Autowired
    UserDetailsRepository repo;

    public List<UserDetails> getAll() {
        List<UserDetails> userDetailsList = repo.findAll();
        return userDetailsList;
    }

    public UserDetails getById(long id) throws Exception {
        return repo.findById(id).orElseThrow(() -> new Exception("UserDetails does not exist with id: " + id));
    }
    public UserDetails save(UserDetails userDetails) {
        repo.save(userDetails);
        return userDetails;
    }

    public void delete(long id) {
        repo.deleteById(id);
    }

    public UserDetails update(long id, UserDetails updateduserDetails) throws Exception {
        UserDetails oldUserDetails = repo.findById(id).orElseThrow(() -> new Exception("UserDetails does exist with id: " + id));

        oldUserDetails.setAccessLevel(updateduserDetails.getAccessLevel());
        oldUserDetails.setAddress(updateduserDetails.getAddress());
        oldUserDetails.setName(updateduserDetails.getName());
        oldUserDetails.setContactNumber(updateduserDetails.getContactNumber());
        oldUserDetails.setLogin(updateduserDetails.getLogin());


        repo.save(oldUserDetails);
        return oldUserDetails;
    }






    public UserDetails findByIdCustomQuery(Long id) throws Exception{
        try {
            return repo.findByIdCustomQuery(id);
        } catch (Exception e) {
            throw new Exception("Login does not exist with id: " + id);
        }
    }

    public UserDetails saveCustomQuery(UserDetails userDetails) throws Exception{
        repo.insertCustomQuery(userDetails.getName(), userDetails.getAddress(), userDetails.getContactNumber(), userDetails.getAccessLevel());
        return userDetails;
    }

    public void deleteCustomQuery(Long id){
        repo.deleteByIdCustomQuery(id);
    }
}
