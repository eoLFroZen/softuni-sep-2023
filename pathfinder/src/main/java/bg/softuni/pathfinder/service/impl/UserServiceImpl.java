package bg.softuni.pathfinder.service.impl;

import bg.softuni.pathfinder.model.User;
import bg.softuni.pathfinder.model.dto.view.UserProfileViewModel;
import bg.softuni.pathfinder.repository.UserRepository;
import bg.softuni.pathfinder.service.UserService;
import bg.softuni.pathfinder.service.helpers.LoggedUserHelperService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final LoggedUserHelperService loggedUserHelperService;

    @Override
    public boolean isUniqueUsername(String username) {
        return this.userRepository.findByUsername(username).isEmpty();
    }

    @Override
    public boolean isUniqueEmail(String email) {
        return this.userRepository.findByEmail(email).isEmpty();
    }

    @Override
    public UserProfileViewModel getUserProfile() {
        User user = loggedUserHelperService.get();

        return modelMapper.map(user, UserProfileViewModel.class);
    }
}
