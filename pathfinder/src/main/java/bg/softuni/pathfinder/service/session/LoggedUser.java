package bg.softuni.pathfinder.service.session;

import bg.softuni.pathfinder.model.Role;
import bg.softuni.pathfinder.model.enums.UserRoles;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class LoggedUser {

    private String username;
    private Set<Role> roles;
    private boolean isLogged;

    public LoggedUser () {

        this.roles = new HashSet<>();
    }

    public void reset() {
        this
                .setUsername(null)
                .setRoles(Collections.emptySet())
                .setLogged(false);
    }

    public String getUsername () {

        return username;
    }

    public LoggedUser setUsername (String username) {

        this.username = username;
        return this;
    }

    public Set<Role> getRoles () {

        return roles;
    }

    public LoggedUser setRoles (Set<Role> roles) {

        this.roles = roles;
        return this;
    }

    public boolean isLogged () {

        return isLogged;
    }

    public LoggedUser setLogged (boolean logged) {

        isLogged = logged;
        return this;
    }

    public boolean isAdmin () {

        return this.roles.stream()
                .anyMatch(role -> role.getName().equals(UserRoles.ADMIN));
    }
}
