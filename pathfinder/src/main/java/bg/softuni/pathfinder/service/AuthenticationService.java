package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.model.dto.binding.UserRegisterBindingModel;

public interface AuthenticationService {

    void register(UserRegisterBindingModel userRegisterBindingModel);
}
