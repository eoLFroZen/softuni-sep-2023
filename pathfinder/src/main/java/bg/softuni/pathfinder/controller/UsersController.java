package bg.softuni.pathfinder.controller;


import bg.softuni.pathfinder.exceptions.LoginCredentialsException;
import bg.softuni.pathfinder.model.dto.UserLoginBindingModel;
import bg.softuni.pathfinder.model.dto.UserRegisterBindingModel;
import bg.softuni.pathfinder.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.Objects;

@Controller
@RequestMapping("/users")
public class UsersController {

    public static final String BINDING_RESULT_PATH = "org.springframework.validation.BindingResult";
    public static final String DOT = ".";
    private final AuthenticationService authenticationService;

    public UsersController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @PostMapping("/login")
    public ModelAndView login(UserLoginBindingModel userLoginBindingModel) {

        authenticationService.login(userLoginBindingModel);
        return new ModelAndView("redirect:/");
    }

    @ExceptionHandler(LoginCredentialsException.class)
    public ModelAndView handleLoginCredentialsError(LoginCredentialsException e) {

        System.out.println(e.getMessage());
        return new ModelAndView("login");
    }

    @GetMapping("/register")
    public ModelAndView register(Model model) {

        if (!model.containsAttribute("userRegisterBindingModel")) {
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
        }

        return new ModelAndView("register");
    }

    @PostMapping("/register")
    public ModelAndView register(@Valid @ModelAttribute("userRegisterBindingModel")
                                     UserRegisterBindingModel userRegisterBindingModel,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {

        final ModelAndView modelAndView = new ModelAndView();

        checkPasswordMatch(bindingResult);

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel)
                    .addFlashAttribute(BINDING_RESULT_PATH + DOT + "userRegisterBindingModel",
                                       bindingResult);
            modelAndView.setViewName("redirect:register");

        } else {

            this.authenticationService.register(userRegisterBindingModel);
            modelAndView.setViewName("redirect:login");
        }

        return modelAndView;
    }

    private static void checkPasswordMatch (BindingResult bindingResult) {

        bindingResult.getGlobalErrors().stream()
                .filter(err -> Arrays.stream(Objects.requireNonNull(err.getCodes())).toList().contains("PasswordMatch"))
                .findAny()
                .ifPresent((globalError) -> {
                    final FieldError confirmPasswordError = new FieldError(globalError.getObjectName(),
                                                                 "confirmPassword",
                                                                 Objects.requireNonNull(globalError.getDefaultMessage()));
                    bindingResult.addError(confirmPasswordError);
                } );
    }

    @PostMapping("/logout")
    public ModelAndView logout() {
        this.authenticationService.logout();

        return new ModelAndView("redirect:/");
    }
}
