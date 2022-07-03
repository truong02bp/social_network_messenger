package com.messenger.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.messenger.data.dto.User;
import com.messenger.service.UserService;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final OkHttpClient client;
    private final ObjectMapper mapper;
    @Value("${api.user}")
    private String apiUser;
    @Value(("${api.token.app}"))
    private String appToken;

    @Override
    public User getByToken(String token) throws IOException {
        Request rq = new Request.Builder()
            .url(apiUser)
            .header("Authorization", token)
            .get()
            .build();
        Call call = client.newCall(rq);
        Response resp = call.execute();
        ResponseBody body = resp.body();
        if (body != null) {
            return mapper.readValue(body.string(), User.class);
        }
        return null;
    }

    @Override
    public User getById(Long id) throws IOException {
        Request rq = new Request.Builder()
            .url(apiUser + "/" + id)
            .header("Authorization", appToken)
            .get()
            .build();
        Call call = client.newCall(rq);
        Response resp = call.execute();
        ResponseBody body = resp.body();
        if (body != null) {
            return mapper.readValue(body.string(), User.class);
        }
        return null;
    }

    @Override
    public List<User> getByIds(List<Long> ids) {
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(apiUser + "/ids")).newBuilder();
        String idsParam = ids.toString();
        idsParam = idsParam.substring(1, idsParam.length() - 1).replace(" ", "");
        urlBuilder.addQueryParameter("ids", idsParam);
        String url = urlBuilder.build().toString();
        Request rq = new Request.Builder()
            .url(url)
            .header("Authorization", appToken)
            .get()
            .build();
        Call call = client.newCall(rq);
        try {
            Response resp = call.execute();
            ResponseBody body = resp.body();
            if (body != null) {
                String json = body.string();
                return Arrays.stream(mapper.readValue(json, User[].class)).toList();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
