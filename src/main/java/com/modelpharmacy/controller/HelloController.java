package com.modelpharmacy.controller;

import com.modelpharmacy.entity.Message;
import com.modelpharmacy.entity.User;
import com.modelpharmacy.helper.UserMessageRequest;
import com.modelpharmacy.service.MessageService;
import com.modelpharmacy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/model-pharmacy/api/v1/hello")
public class HelloController {
    private final UserService userService;
    private final MessageService messageService;

    @GetMapping("/"  )
    public String helloWorld() {
        return "Hello, Model Pharmacy!";
    }

    @PostMapping("/add")
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
}
