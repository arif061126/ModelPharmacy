package com.modelpharmacy.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String subject;

    @Column(length = 5000)
    private String content;

    private String sender;
    private String receiver;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd.MM.yyyy hh:mm:ss")
    private LocalDateTime sentAt;

    private Boolean isRead;
    private Boolean isDeletedBySender;
    private Boolean isDeletedByReceiver;
    private String type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "m_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User recipient;
}
