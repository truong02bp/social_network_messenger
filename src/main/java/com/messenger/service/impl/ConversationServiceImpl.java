package com.messenger.service.impl;

import com.messenger.data.dto.ConversationDto;
import com.messenger.data.entities.Conversation;
import com.messenger.data.entities.Messenger;
import com.messenger.data.repositories.ConversationRepository;
import com.messenger.exceptions.BusinessException;
import com.messenger.service.ConversationService;
import com.messenger.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ConversationServiceImpl implements ConversationService {

    private final ConversationRepository conversationRepository;
    private final UserService userService;

    @Override
    public Conversation createGroup(List<Long> userIds) {
        if (userIds.isEmpty()) {
            throw BusinessException.builder().message("Conversation member must not be empty").status(HttpStatus.BAD_REQUEST).build();
        }
        Conversation conversation = new Conversation();
        conversationRepository.save(conversation);
        StringBuilder name = new StringBuilder();
        List<Messenger> messengers = userService.getByIds(userIds)
            .stream().map(user -> {
                name.append(user.getName().substring(user.getName().lastIndexOf(" ") + 1)).append(", ");
                Messenger messenger = new Messenger();
                messenger.setUser(user);
                messenger.setConversationId(conversation.getId());
                return messenger;
            }).toList();
        conversation.setIsGroup(true);
        conversation.setName(name.substring(0, name.length() - 2));
        conversation.setMessengers(messengers);
        return conversationRepository.save(conversation);
    }

    @Override
    public Conversation create(List<Long> userIds) {
        if (userIds.isEmpty()) {
            throw BusinessException.builder().message("Conversation member must not be empty").status(HttpStatus.BAD_REQUEST).build();
        }
        Conversation conversation = new Conversation();
        conversationRepository.save(conversation);
        List<Messenger> messengers = userService.getByIds(userIds)
            .stream().map(user -> {
                Messenger messenger = new Messenger();
                messenger.setUser(user);
                messenger.setConversationId(conversation.getId());
                return messenger;
            }).toList();
        conversation.setMessengers(messengers);
        return conversationRepository.save(conversation);
    }

    @Override
    public List<ConversationDto> findByUserId(Long userId) {
        return null;
    }
}
