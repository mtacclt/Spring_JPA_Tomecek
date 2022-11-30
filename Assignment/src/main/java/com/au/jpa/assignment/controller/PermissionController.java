package com.au.jpa.assignment.controller;

import com.au.jpa.assignment.model.Login;
import com.au.jpa.assignment.model.Permission;
import com.au.jpa.assignment.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PermissionController {
    @Autowired
    PermissionService service;

    @GetMapping("/permissions")
    public ResponseEntity<List<Permission>> getAll() {
        List<Permission> permissions = service.getAll();
        return new ResponseEntity<>(permissions, HttpStatus.OK);
    }

    @GetMapping("/permissions/{id}")
    public ResponseEntity<Permission> get(@PathVariable Long id) throws Exception {
        Permission permission = service.getById(id);
        return new ResponseEntity<>(permission, HttpStatus.OK);
    }

    @PostMapping("/permissions")
    public ResponseEntity<Permission> create(@RequestBody Permission newPermission) {
        Permission permission = service.save(newPermission);
        return new ResponseEntity<>(permission, HttpStatus.CREATED);
    }

    @PutMapping("/permissions/{id}")
    public ResponseEntity<Permission> update(@PathVariable Long id, @RequestBody Permission updatedPermissionDetails) throws Exception {
        Permission updatedPermission = service.update(id, updatedPermissionDetails);
        return new ResponseEntity<>(updatedPermission, HttpStatus.OK);
    }

    @DeleteMapping("/permissions/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }


    @GetMapping("/permissions/custom/{id}")
    public ResponseEntity<Permission> customGet(@PathVariable Long id) throws Exception{
        Permission permission = service.findByIdCustomQuery(id);
        return new ResponseEntity<>(permission,HttpStatus.OK);
    }

    @PostMapping("/permissions/custom")
    public ResponseEntity<Permission> customCreate(@RequestBody Permission newPermission) throws Exception {
        Permission permission = service.saveCustomQuery(newPermission);
        return new ResponseEntity<>(permission, HttpStatus.CREATED);
    }

    @DeleteMapping("/permissions/custom/{id}")
    public ResponseEntity<Long> customDelete(@PathVariable Long id){
        service.deleteCustomQuery(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
