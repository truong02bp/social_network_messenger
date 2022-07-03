package com.messenger.controllers;

import com.messenger.data.dto.ConversationDto;
import com.messenger.data.entities.Conversation;
import com.messenger.service.ConversationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/conversation")
@RequiredArgsConstructor
public class ConversationController {

    private final ConversationService conversationService;

    @GetMapping
    public ResponseEntity<List<ConversationDto>> findByUserId(@RequestParam("userId") Long userId) {
        return ResponseEntity.ok(conversationService.findByUserId(userId));
    }

    @PostMapping("/group")
    public ResponseEntity<Conversation> createGroup(@RequestParam("userIds") List<Long> userIds) {
        Conversation conversation = conversationService.createGroup(userIds);
        return ResponseEntity.ok(conversation);
    }
}
