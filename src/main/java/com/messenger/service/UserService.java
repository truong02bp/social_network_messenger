package com.messenger.service;

import com.messenger.data.dto.User;

import java.io.IOException;
import java.util.List;

public interface UserService {
    User getByToken(String token) throws IOException;
    User getById(Long id) throws IOException;

    List<User> getByIds(List<Long> ids);
}
