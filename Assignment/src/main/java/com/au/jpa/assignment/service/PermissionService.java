package com.au.jpa.assignment.service;

import com.au.jpa.assignment.model.Login;
import com.au.jpa.assignment.model.Permission;
import com.au.jpa.assignment.repo.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {
    @Autowired
    PermissionRepository repo;

    public List<Permission> getAll() {
        List<Permission> permissionList = repo.findAll();
        return permissionList;
    }

    public Permission getById(long id) throws Exception {
        return repo.findById(id).orElseThrow(() -> new Exception("Permission does not exist with id: " + id));
    }
    public Permission save(Permission permission) {
        repo.save(permission);
        return permission;
    }

    public void delete(long id) {
        repo.deleteById(id);
    }

    public Permission update(long id, Permission updatedPermission) throws Exception {
        Permission oldPermission = repo.findById(id).orElseThrow(() -> new Exception("Permission does exist with id: " + id));

        oldPermission.setPermissionLevel(updatedPermission.getPermissionLevel());

        repo.save(oldPermission);
        return oldPermission;
    }








    public Permission findByIdCustomQuery(Long id) throws Exception{
        try {
            return repo.findByIdCustomQuery(id);
        } catch (Exception e) {
            throw new Exception("Login does not exist with id: " + id);
        }
    }

    public Permission saveCustomQuery(Permission permission) throws Exception{
        repo.insertCustomQuery(permission.getUserId(), permission.getPermissionLevel());
        return permission;
    }

    public void deleteCustomQuery(Long id){
        repo.deleteByIdCustomQuery(id);
    }
}
