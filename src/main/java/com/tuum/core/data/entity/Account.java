package com.tuum.core.data.entity;


import lombok.Data;

@Data
public class Account {

    private Long id;

    public Account() {
    }

    public Account(Long id) {
        this.id = id;
    }
}
