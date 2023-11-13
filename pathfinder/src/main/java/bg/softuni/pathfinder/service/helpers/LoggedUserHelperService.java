package bg.softuni.pathfinder.service.helpers;

import bg.softuni.pathfinder.exceptions.UserNotFoundException;
import bg.softuni.pathfinder.model.User;
import bg.softuni.pathfinder.repository.UserRepository;
import bg.softuni.pathfinder.service.session.LoggedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoggedUserHelperService {
    private final UserRepository userRepository;
    private final LoggedUser loggedUser;

    public User get() {
        String username = loggedUser.getUsername();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User with username: " + username + " was not found"));
    }
}
