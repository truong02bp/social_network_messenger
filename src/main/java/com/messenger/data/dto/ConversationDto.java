package com.messenger.data.dto;

import com.messenger.data.entities.Message;
import com.messenger.data.entities.Messenger;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ConversationDto {
    private String name;
    private String color;
    private boolean isGroup;
    private Media image;
    private Messenger user;
    private List<Messenger> guests;
    private Message lastMessage;
}
