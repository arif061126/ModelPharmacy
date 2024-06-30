package com.modelpharmacy.service;

import com.modelpharmacy.dto.MessageDTO;
import com.modelpharmacy.dto.UserDTO;
import com.modelpharmacy.entity.Message;
import com.modelpharmacy.entity.Role;
import com.modelpharmacy.entity.User;
import com.modelpharmacy.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUserName());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole("NORMAL");
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setAddress(user.getAddress());
        userDTO.setCity(user.getCity());
        userDTO.setDistrict(user.getDistrict());
        userDTO.setProfileImage(user.getProfileImage());
        userDTO.setAbout(user.getAbout());
        userDTO.setGender(user.getGender());
        userDTO.setDateOfBirth(user.getDateOfBirth());
        userDTO.setEntryDate(user.getEntryDate());
        userDTO.setLeaveDate(user.getLeaveDate());
        userDTO.setPosition(user.getPosition());
        userDTO.setTakenVacationDays(user.getTakenVacationDays());
        userDTO.setRemainingVacationDays(user.getRemainingVacationDays());
        userDTO.setIsActive(user.getIsActive());
        userDTO.setNIDNumber(user.getNIDNumber());
        userDTO.setPassword(user.getPassword());
        userDTO.setPasswordExpiryDate(user.getPasswordExpiryDate());
        userDTO.setMessages(user.getMessages().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList()));

        return userDTO;
    }

    public MessageDTO convertToDTO(Message message){
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setId(message.getId());
        messageDTO.setSubject(message.getSubject());
        messageDTO.setContent(message.getContent());
        messageDTO.setSender(message.getSender());
        messageDTO.setReceiver(message.getReceiver());
        messageDTO.setSentAt(message.getSentAt());
        messageDTO.setIsRead(message.getIsRead());
        messageDTO.setIsDeletedBySender(message.getIsDeletedBySender());
        messageDTO.setIsDeletedByReceiver(message.getIsDeletedByReceiver());
        messageDTO.setType(message.getType());
        return messageDTO;
    }

    // Fetch all users from the database
    public List<User> getAllUsers() {
        List<User> users = this.userRepository.findAll();
        //users.forEach(u -> u.setPassword(desPasswordEncoder.decrypt(u.getPassword())));
        return users;
    }

    // Fetch user by ID from the database
    public User getUserById(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    // Validate if user with given NID already exists before adding
    public Boolean addUser(User user) {
        //this.userRepository.findUserByNIDNumber(user.getNIDNumber()).ifPresent(u -> System.out.println("User with " + user.getNIDNumber() + " already exists"));
        if (this.userRepository.findUserByNIDNumber(user.getNIDNumber()).isPresent()) {
            System.out.println("User with the same NID already exists");
            return false;
        }else {
            /*
            Generate a unique username based on the user's first two letters of first name
            and last three letters of last name, followed by the last two digits of the user's entry date
            */
            String firstName = user.getFirstName();
            firstName = firstName.substring(0, 2).toUpperCase();
            String lastName = user.getLastName();
            lastName = lastName.substring(lastName.length()-3, lastName.length()).toUpperCase();
            String userName = firstName + lastName + ((user.getEntryDate().getYear()) % 100);

            // set the generated username to the user object
            user.setUserName(userName);

            // Set the entry date if it's not set
            user.setEntryDate(LocalDate.now());

            //set user password
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

            // set default profile image
            user.setProfileImage(null);

            // set default password expiry date
            user.setPasswordExpiryDate(LocalDateTime.now().plusMonths(3));

            // set default role
            user.setRole(Role.NORMAL);

            // set default position
            user.setPosition("Employee");

            // set default profile image

            // set default remaining vacation days
            user.setRemainingVacationDays(30);

            // set default is active status to true
            user.setIsActive(true);

            // set default taken vacation days to 0
            user.setTakenVacationDays(0);

            // save the user to the database
            this.userRepository.save(user);
            return true;
        }
    }

    // Validate if user with given ID already exist before updating
    public boolean updateUser(Long id, User user) {
        List<User> users = this.userRepository.findAll();
        Optional<User> optionalUser = users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
        if (optionalUser.isPresent()) {
            user.setId(id);
            this.userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

    // Validate if user with given ID already exist before deleting
    public boolean deleteUser(Long id) {
        if (this.userRepository.findById(id).isPresent()) {
            this.userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    // Decoding a string
    /*public String decodePassword(String password) {
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(password);
            return new String(decodedBytes);
        }catch (Exception e) {
            return e.getMessage();
        }
    }*/

    // 1. Implement spring security
    // 2. Implement user notification with username, password, validity of password after creating a new user-->Done
}
