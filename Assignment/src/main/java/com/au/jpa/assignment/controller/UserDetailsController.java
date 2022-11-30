package com.au.jpa.assignment.controller;

import com.au.jpa.assignment.model.Login;
import com.au.jpa.assignment.model.UserDetails;
import com.au.jpa.assignment.service.LoginService;
import com.au.jpa.assignment.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserDetailsController {
    @Autowired
    UserDetailsService service;

    @Autowired
    LoginService loginService;

    @GetMapping("/userdetails")
    public ResponseEntity<List<UserDetails>> getAll() {
        List<UserDetails> userDetails = service.getAll();
        return new ResponseEntity<>(userDetails, HttpStatus.OK);
    }

    @GetMapping("/userdetails/{id}")
    public ResponseEntity<UserDetails> get(@PathVariable Long id) throws Exception {
        UserDetails userDetails = service.getById(id);
        return new ResponseEntity<>(userDetails, HttpStatus.OK);
    }

    @PostMapping("/userdetails/postparams={id}")
    public ResponseEntity<UserDetails> create(@PathVariable long id, @RequestBody UserDetails newUserDetails) throws Exception {
        System.out.println("retrieving login");
        System.out.println("login at id "+id+" has following parameters:");
        System.out.println("Password: "+loginService.getById(id).getPassword());
        System.out.println("Active: "+loginService.getById(id).getIsActive());
        newUserDetails.setLogin(loginService.getById(id));
        System.out.println("login details set");
        UserDetails userDetails = service.save(newUserDetails);
        System.out.println("login details saved");
        return new ResponseEntity<>(userDetails, HttpStatus.CREATED);
    }

    @PostMapping("/userdetails")
    public ResponseEntity<UserDetails> createButBetter(@RequestBody UserDetails newUserDetails) throws Exception {
        UserDetails userDetails = service.save(newUserDetails);
        return new ResponseEntity<>(userDetails, HttpStatus.CREATED);
    }

    @PutMapping("/userdetails/{id}")
    public ResponseEntity<UserDetails> update(@PathVariable Long id, @RequestBody UserDetails updatedUserDetailsValues) throws Exception {
        UserDetails updatedUserDetails = service.update(id, updatedUserDetailsValues);
        return new ResponseEntity<>(updatedUserDetails, HttpStatus.OK);
    }

    @DeleteMapping("/userdetails/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }




    @GetMapping("/userdetails/custom/{id}")
    public ResponseEntity<UserDetails> customGet(@PathVariable Long id) throws Exception{
        UserDetails userDetails = service.findByIdCustomQuery(id);
        return new ResponseEntity<>(userDetails,HttpStatus.OK);
    }

    @PostMapping("/userdetails/custom")
    public ResponseEntity<UserDetails> customCreate(@RequestBody UserDetails newUserDetails) throws Exception {
        UserDetails userDetails = service.saveCustomQuery(newUserDetails);
        return new ResponseEntity<>(userDetails, HttpStatus.CREATED);
    }

    @DeleteMapping("/userdetails/custom/{id}")
    public ResponseEntity<Long> customDelete(@PathVariable Long id){
        service.deleteCustomQuery(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
