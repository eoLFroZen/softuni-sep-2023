package bg.softuni.pathfinder.config;

import bg.softuni.pathfinder.exceptions.LoginCredentialsException;
import bg.softuni.pathfinder.exceptions.RouteNotFoundException;
import bg.softuni.pathfinder.exceptions.UserNotFoundException;
import bg.softuni.pathfinder.model.*;
import bg.softuni.pathfinder.model.dto.binding.AddRouteBindingModel;
import bg.softuni.pathfinder.model.dto.binding.CreateCommentBindingModel;
import bg.softuni.pathfinder.model.dto.binding.UserRegisterBindingModel;
import bg.softuni.pathfinder.model.dto.view.PictureViewModel;
import bg.softuni.pathfinder.model.dto.view.RouteCategoryViewModel;
import bg.softuni.pathfinder.model.dto.view.RouteDetailsViewModel;
import bg.softuni.pathfinder.model.enums.CategoryNames;
import bg.softuni.pathfinder.model.enums.Level;
import bg.softuni.pathfinder.repository.RouteRepository;
import bg.softuni.pathfinder.repository.UserRepository;
import bg.softuni.pathfinder.service.CategoryService;
import bg.softuni.pathfinder.service.RoleService;
import bg.softuni.pathfinder.service.session.LoggedUser;
import bg.softuni.pathfinder.util.YoutubeUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Conditions;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    private final LoggedUser loggedUser;
    private final UserRepository userRepository;
    private final RouteRepository routeRepository;
    private final CategoryService categoryService;
    private final RoleService roleService;

    @Bean
    public ModelMapper modelMapper() {

        final ModelMapper modelMapper = new ModelMapper();

        //AddRouteBindingModel -> Route
        Provider<User> loggedUserProvider = req -> getLoggedUser();
        Provider<String> youtubeSubUrlProvider = req -> YoutubeUtil.getUrl((String) req.getSource());

        Converter<Set<CategoryNames>, Set<Category>> toEntitySet
                = ctx -> (ctx.getSource() == null)
                ? null
                : categoryService.getAllByNameIn(ctx.getSource());

        modelMapper
                .createTypeMap(AddRouteBindingModel.class, Route.class)
                .addMappings(mapper -> mapper
                        .using(toEntitySet)
                        .map(AddRouteBindingModel::getCategories, Route::setCategories))
                .addMappings(mapper -> mapper
                        .when(Conditions.isNull())
                        .with(loggedUserProvider)
                        .map(AddRouteBindingModel::getAuthor, Route::setAuthor))
                .addMappings(mapper -> mapper
                        .with(youtubeSubUrlProvider)
                        .map(AddRouteBindingModel::getVideoUrl, Route::setVideoUrl));

        //UserRegisterBindingModel -> User
        Provider<User> newUserProvider = req -> new User()
                .setRoles(Set.of(roleService.getRoleByName("USER")))
                .setLevel(Level.BEGINNER);

        Converter<String, String> passwordConverter
                = ctx -> (ctx.getSource() == null)
                ? null
                : passwordEncoder().encode(ctx.getSource());

        modelMapper
                .createTypeMap(UserRegisterBindingModel.class, User.class)
                .setProvider(newUserProvider)
                .addMappings(mapper -> mapper
                        .using(passwordConverter)
                        .map(UserRegisterBindingModel::getPassword, User::setPassword));

        // RouteCategoryViewModel
        modelMapper
                .createTypeMap(Route.class, RouteCategoryViewModel.class)
                .addMappings(mapper -> mapper
                        .map(Route::getName, RouteCategoryViewModel::setTitle));

        //CreateCommentBindingModel -> Comment

        Provider<Comment> bindModelToCommentProvider = ctx -> {
            CreateCommentBindingModel createCommentBindingModel = (CreateCommentBindingModel) ctx.getSource();

            Optional<Route> optionalRoute = routeRepository.findById(createCommentBindingModel.getRouteId());

            if (optionalRoute.isEmpty()) {
                throw new RouteNotFoundException("Route not found");
            }

            User user = userRepository.findByUsername(loggedUser.getUsername())
                    .orElse(null);
            Route route = optionalRoute.get();

            Comment comment = new Comment();
            comment.setRoute(route);
            comment.setCreated(LocalDateTime.now());
            comment.setAuthor(user);

            return comment;
        };

        modelMapper
                .createTypeMap(CreateCommentBindingModel.class, Comment.class)
                .setProvider(bindModelToCommentProvider);

        // Picture -> PictureViewMode;
        modelMapper
                .createTypeMap(Picture.class, PictureViewModel.class)
                .addMappings(mapper -> mapper
                        .map(Picture::getUrl, PictureViewModel::setSrc))
                .addMappings(mapper -> mapper
                        .map(Picture::getTitle, PictureViewModel::setAlt));

        return modelMapper;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private User getLoggedUser() {
        final String username = loggedUser.getUsername();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new LoginCredentialsException("User with username: " + username + " is not present"));
    }
}
