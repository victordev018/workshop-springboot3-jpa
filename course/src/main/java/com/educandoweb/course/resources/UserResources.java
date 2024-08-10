package com.educandoweb.course.resources;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.services.UserServices;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResources {

    @Autowired
    private UserServices services;

    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        List<User> list = services.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        User obj = services.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user){
        user = services.insert(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{is}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        services.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj){
        try {
            obj = services.update(id, obj);
            return ResponseEntity.ok().body(obj);
        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }
}
