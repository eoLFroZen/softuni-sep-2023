package com.plannerapp.service.impl;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class LoggedUser {

    private String username;

    private boolean isLogged;


    public String getUsername() {
        return username;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public void login(String username) {
        this.username = username;
        this.isLogged = true;
    }

    public void logout() {
        this.username = null;
        this.isLogged = false;
    }
}
