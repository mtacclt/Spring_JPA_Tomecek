package com.au.jpa.assignment.controller;

import com.au.jpa.assignment.model.Login;
import com.au.jpa.assignment.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LoginController {
    @Autowired
    LoginService service;

    @GetMapping("/logins")
    public ResponseEntity<List<Login>> getAll() {
        List<Login> logins = service.getAll();
        return new ResponseEntity<>(logins, HttpStatus.OK);
    }

    @GetMapping("/logins/{id}")
    public ResponseEntity<Login> get(@PathVariable Long id) throws Exception {
        Login login = service.getById(id);
        return new ResponseEntity<>(login, HttpStatus.OK);
    }

    @PostMapping("/logins")
    public ResponseEntity<Login> create(@RequestBody Login newLogin) {
        service.save(newLogin);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/logins/{id}")
    public ResponseEntity<Login> update(@PathVariable Long id, @RequestBody Login updatedLoginDetails) throws Exception {
        Login updatedLogin = service.update(id, updatedLoginDetails);
        return new ResponseEntity<>(updatedLogin, HttpStatus.OK);
    }

    @DeleteMapping("/logins/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @GetMapping("/logins/custom/{id}")
    public ResponseEntity<Login> customGet(@PathVariable Long id) throws Exception{
        Login login = service.findByIdCustomQuery(id);
        return new ResponseEntity<>(login,HttpStatus.OK);
    }

    @PostMapping("/logins/custom")
    public ResponseEntity<Login> customCreate(@RequestBody Login newLogin) throws Exception {
        Login login = service.saveCustomQuery(newLogin);
        return new ResponseEntity<>(login, HttpStatus.CREATED);
    }

    @DeleteMapping("/logins/custom/{id}")
    public ResponseEntity<Long> customDelete(@PathVariable Long id){
        service.deleteCustomQuery(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
