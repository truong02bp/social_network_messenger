package com.messenger.service;

import com.messenger.data.dto.MessageDto;
import com.messenger.data.entities.Message;

public interface MessageService {
    Message create(MessageDto messageDto);
}
