package com.modelpharmacy.controller;

import com.modelpharmacy.dto.UserDTO;
import com.modelpharmacy.entity.User;
import com.modelpharmacy.service.MessageService;
import com.modelpharmacy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.modelpharmacy.entity.Role;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/model-pharmacy/api/v1/user")
@PreAuthorize(value = "hasAnyRole('ADMIN', 'NORMAL')")
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id){
        User user = this.userService.getUserById(id);
        UserDTO userDTO = this.userService.convertToDTO(user);
        return user!=null ?
                new ResponseEntity<>(userDTO, HttpStatus.OK) :
                new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody User user) {
        this.userService.updateUser(id, user);
        return new ResponseEntity<>("User updated successfully", HttpStatus.ACCEPTED);
    }
}
