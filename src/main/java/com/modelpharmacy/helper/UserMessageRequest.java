package com.modelpharmacy.helper;

import com.modelpharmacy.entity.Message;
import com.modelpharmacy.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserMessageRequest {
    private User user;
    private Message message;
}
