package com.modelpharmacy.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {
    private Long id;
    private String subject;
    private String content;
    private String sender;
    private String receiver;

    @JsonFormat(pattern = "dd.MM.yyyy hh:mm:ss")
    private LocalDateTime sentAt;

    private Boolean isRead;
    private Boolean isDeletedBySender;
    private Boolean isDeletedByReceiver;
    private String type;
}
