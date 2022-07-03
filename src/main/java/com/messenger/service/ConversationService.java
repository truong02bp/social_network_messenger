package com.messenger.service;

import com.messenger.data.dto.ConversationDto;
import com.messenger.data.entities.Conversation;

import java.util.List;

public interface ConversationService {
    Conversation createGroup(List<Long> userIds);
    Conversation create(List<Long> userIds);
    List<ConversationDto> findByUserId(Long userId);
}
