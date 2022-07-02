package com.messenger.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class User{
    private Long id;
    private String email;
    private String password;
    private String name;
    private String username;
    private LocalDateTime lastOnline;
    private boolean isOnline = false;
    private boolean isActive = true;
    private boolean isPrivate = false;
    private Media avatar;
    private String address;
    private String phone;
    private List<Role> roles;
//    private String createdBy;
//    private String lastModifiedBy;
//    private LocalDateTime createdDate;
//    private LocalDateTime lastModifiedDate;
}
