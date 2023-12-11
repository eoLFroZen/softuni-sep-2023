package bg.softuni.pathfinder.service.impl;

import bg.softuni.pathfinder.model.User;
import bg.softuni.pathfinder.model.dto.binding.UserRegisterBindingModel;
import bg.softuni.pathfinder.repository.UserRepository;
import bg.softuni.pathfinder.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(UserRegisterBindingModel userRegisterBindingModel) {
        User user = modelMapper.map(userRegisterBindingModel, User.class);
        userRepository.save(user);
    }
}
