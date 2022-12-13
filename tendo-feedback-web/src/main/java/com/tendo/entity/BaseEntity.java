package com.tendo.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.ZonedDateTime;
import java.util.UUID;

@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue
    protected UUID id;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    protected ZonedDateTime createdDate;

    @UpdateTimestamp
    @Column(nullable = false)
    protected ZonedDateTime modifiedDate;

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }

    public ZonedDateTime getModifiedDate() {
        return modifiedDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
