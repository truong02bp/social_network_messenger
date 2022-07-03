package com.messenger.data.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "media")
@Entity
@Getter
@Setter
public class Media extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "content_type", nullable = false)
    private String contentType;

    @Column(name = "url", nullable = false)
    private String url;
}
