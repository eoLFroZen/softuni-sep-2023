package com.resellerapp.service;

import com.resellerapp.model.UserLoginBindingModel;
import com.resellerapp.model.UserRegisterBindingModel;

public interface UserService {

    boolean register(UserRegisterBindingModel userRegisterBindingModel);

    boolean login(UserLoginBindingModel userLoginBindingModel);

    void logout();

}
