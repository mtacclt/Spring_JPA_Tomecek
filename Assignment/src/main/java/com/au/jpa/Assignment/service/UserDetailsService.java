package com.au.jpa.Assignment.service;

import com.au.jpa.Assignment.model.Permission;
import com.au.jpa.Assignment.model.UserDetails;
import com.au.jpa.Assignment.repo.UserDetailsRepository;
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
        UserDetails olduserDetails = repo.findById(id).orElseThrow(() -> new Exception("UserDetails does exist with id: " + id));

        olduserDetails.set(updateduserDetails.get());

        repo.save(olduserDetails);
        return olduserDetails;
    }
}
