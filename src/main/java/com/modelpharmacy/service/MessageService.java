package com.modelpharmacy.service;

import com.modelpharmacy.entity.Message;
import com.modelpharmacy.entity.Role;
import com.modelpharmacy.entity.User;
import com.modelpharmacy.repository.MessageRepository;
import com.modelpharmacy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    public Boolean generateMessage(User user, Message message) {
        // Get the admin user, or throw an exception if not found
        User adminUser = this.userRepository.findAll().stream()
                .filter(u -> u.getRole() == Role.ADMIN)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Admin user not found"));


        // set default message
        message.setRecipient(user);
        message.setSender(adminUser.getFirstName() + " " + adminUser.getLastName());
        message.setReceiver(user.getFirstName()+ " "+ user.getLastName());
        message.setSentAt(LocalDateTime.now());
        message.setIsRead(false);
        message.setIsDeletedBySender(false);
        message.setIsDeletedByReceiver(false);
        message.setType("CREDENTIALS");
        message.setSubject("New Username and Password");

        // set default message content:
        String content = "Subject: "+message.getSubject()
                + "\n\n<<<Welcome to Model Pharmacy>>>\n\n"
                + "Dear " + message.getReceiver() + ","
                + "\nYour account has been created successfully."
                + "\n\nYour username is: " + user.getUserName()
                + "\nYour Password is: " + user.getPassword()
                + "\n\nPlease remember to change your password after login."
                + "\nDate of Password creation: " + message.getSentAt()
                + "\nYour Password will be valid till: " + user.getPasswordExpiryDate()
                + "\n\nBest regards,\n"
                + message.getSender()
                + "\n"+ adminUser.getPosition()
                + "\nModel Pharmacy";

        message.setContent(content);

        this.messageRepository.save(message);
        return true;
    }
}