package com.messenger.data.entities;

import com.messenger.data.dto.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "messengers")
@Getter
@Setter
public class Messenger extends BaseEntity {

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Transient
    private User user;

    @Column(name = "conversation_id", nullable = false)
    private Long conversationId;
}
