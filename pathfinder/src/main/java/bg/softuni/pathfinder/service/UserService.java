package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.model.dto.view.UserProfileViewModel;

public interface UserService {
    boolean isUniqueUsername(String value);

    boolean isUniqueEmail(String value);

    UserProfileViewModel getUserProfile();
}
