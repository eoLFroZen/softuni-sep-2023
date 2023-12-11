package bg.softuni.pathfinder.service.helpers;

import bg.softuni.pathfinder.exceptions.UserNotFoundException;
import bg.softuni.pathfinder.model.User;
import bg.softuni.pathfinder.model.enums.UserRoles;
import bg.softuni.pathfinder.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoggedUserHelperService {
    private final UserRepository userRepository;

    public User get() {
        String username = getUsername();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User with username: " + username + " was not found"));
    }

    public boolean isLogged() {
        return !hasRole(UserRoles.ANONYMOUS);
    }

    public boolean isAdmin() {
        return hasRole(UserRoles.ADMIN);
    }

    public boolean isModerator() {
        return hasRole(UserRoles.MODERATOR);
    }

    public boolean isOnlyUser() {
        return getAuthentication().getAuthorities().stream()
                .allMatch(role -> role.getAuthority().equals("ROLE_" + UserRoles.USER));
    }

    public String getUsername() {
        return getUserDetails().getUsername();
    }

    public boolean hasRole(UserRoles userRoles) {
        return getAuthentication().getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ROLE_" + userRoles));
    }

    private UserDetails getUserDetails() {
        return (UserDetails) getAuthentication().getPrincipal();
    }

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
