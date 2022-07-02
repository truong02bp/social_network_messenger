package com.messenger;

import com.messenger.data.dto.User;
import com.messenger.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/messenger")
@RequiredArgsConstructor
public class Controllers {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<User> getMessage() throws IOException {
        return ResponseEntity.ok(userService.getById((long) 1));
    }
}
