package com.modelpharmacy.controller;

import com.modelpharmacy.dto.UserDTO;
import com.modelpharmacy.entity.Message;
import com.modelpharmacy.entity.Test;
import com.modelpharmacy.entity.User;
import com.modelpharmacy.helper.UserMessageRequest;
import com.modelpharmacy.service.MessageService;
import com.modelpharmacy.service.TestService;
import com.modelpharmacy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.modelpharmacy.entity.Role;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/model-pharmacy/api/v1/admin")
@PreAuthorize(value = "hasRole('ADMIN')")
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> viewAllUsers() {
        List<User> users = this.userService.getAllUsers();
        return new ResponseEntity<>(users.stream().map(this.userService::convertToDTO).collect(Collectors.toList()), HttpStatus.OK);
    }

    /*@PostMapping("/users/add")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        if (user.getEntryDate() == null) {
            user.setEntryDate(LocalDate.now());
        }
        return (this.userService.addUser(user)) ?
                new ResponseEntity<>("User added successfully", HttpStatus.CREATED) :
                new ResponseEntity<>("User with the same NID already exists", HttpStatus.CONFLICT);
    }*/
    @PostMapping("/users/add")
    public ResponseEntity<String> addUser(@RequestBody UserMessageRequest userMessageRequest) {
        User user = userMessageRequest.getUser();
        Message message = userMessageRequest.getMessage();

        // Log the incoming request payload
        System.out.println("Received User: " + user);
        System.out.println("Received Message: " + message);

        if (user == null) {
            return new ResponseEntity<>("User is missing", HttpStatus.BAD_REQUEST);
        }

        if (user.getEntryDate() == null) {
            user.setEntryDate(LocalDate.now());
        }

        return (this.userService.addUser(user) && this.messageService.generateMessage(user,message)) ?
                new ResponseEntity<>("User added successfully", HttpStatus.CREATED) :
                new ResponseEntity<>("User with the same NID already exists", HttpStatus.CONFLICT);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody User user) {
        return this.userService.updateUser(id, user)?
                new ResponseEntity<>("User updated successfully", HttpStatus.ACCEPTED) :
                new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
/*        userService.updateUser(id, user);
        return new ResponseEntity<>("User updated successfully", HttpStatus.ACCEPTED);*/
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        return this.userService.deleteUser(id)?
                new ResponseEntity<>("User deleted successfully", HttpStatus.OK) :
                new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }
}
