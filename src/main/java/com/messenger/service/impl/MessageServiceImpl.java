package com.messenger.service.impl;

import com.messenger.data.dto.MessageDto;
import com.messenger.data.entities.Message;
import com.messenger.data.entities.Messenger;
import com.messenger.data.repositories.MessengerRepository;
import com.messenger.exceptions.BusinessException;
import com.messenger.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessengerRepository messengerRepository;

    @Override
    public Message create(MessageDto messageDto) {
        Messenger sender = messengerRepository.findById(messageDto.getMessengerId()).orElseThrow(() -> {
                throw BusinessException.builder().message("Messenger not found").status(HttpStatus.NOT_FOUND).build();
            }
        );

        Message message = new Message();
        message.setContent("HAHA");
        message.setSender(sender);
        return message;
    }
}
