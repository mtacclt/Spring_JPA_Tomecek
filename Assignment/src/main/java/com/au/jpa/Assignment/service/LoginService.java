package com.au.jpa.Assignment.service;

import com.au.jpa.Assignment.model.Login;
import com.au.jpa.Assignment.repo.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {
    @Autowired
    LoginRepository repo;

    public List<Login> getAll() {
        List<Login> loginList = repo.findAll();
        return loginList;
    }

    public Login getById(long id) throws Exception {
        return repo.findById(id).orElseThrow(() -> new Exception("Login does not exist with id: " + id));
    }
    public Login save(Login login) {
        repo.save(login);
        return login;
    }

    public void delete(long id) {
        repo.deleteById(id);
    }

    public Login update(long id, Login updatedLogin) throws Exception {
        Login oldLogin = repo.findById(id).orElseThrow(() -> new Exception("Login does exist with id: " + id));

        oldLogin.setPassword(updatedLogin.getPassword());
        oldLogin.setIsActive(updatedLogin.getIsActive());

        repo.save(oldLogin);
        return oldLogin;
    }
}
