package com.messenger.controllers;

import com.messenger.data.dto.User;
import com.messenger.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1/test")
@RestController
@RequiredArgsConstructor
public class TestController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getUsers(@RequestParam("ids") List<Long> ids) {
        return ResponseEntity.ok(userService.getByIds(ids));
    }
}
