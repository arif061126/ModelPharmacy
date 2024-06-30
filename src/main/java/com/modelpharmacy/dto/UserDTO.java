package com.modelpharmacy.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String password;

    @JsonFormat(pattern = "dd.MM.yyyy hh:mm:ss")
    private LocalDateTime passwordExpiryDate;

    private String email;
    private String role;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String city;
    private String district;
    private String NIDNumber;
    private byte[] profileImage;
    private String about;
    private String gender;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate dateOfBirth;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate entryDate;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate leaveDate;

    private String position;
    private Integer takenVacationDays;
    private Integer remainingVacationDays;
    private Boolean isActive;
    private List<MessageDTO> messages;
}
