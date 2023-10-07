package bg.softuni.pathfinder.config;

import bg.softuni.pathfinder.model.Category;
import bg.softuni.pathfinder.model.Route;
import bg.softuni.pathfinder.model.User;
import bg.softuni.pathfinder.model.dto.AddRouteBindingModel;
import bg.softuni.pathfinder.model.enums.CategoryNames;
import bg.softuni.pathfinder.service.CategoryService;
import bg.softuni.pathfinder.service.UserService;
import org.modelmapper.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class AppConfig {

    private final CategoryService categoryService;
    private final UserService userService;

    public AppConfig (CategoryService categoryService,
                      UserService userService) {

        this.categoryService = categoryService;
        this.userService = userService;
    }

    @Bean
    public ModelMapper modelMapper() {

        final ModelMapper modelMapper = new ModelMapper();
        final TypeMap<AddRouteBindingModel, Route> typeMap
                = modelMapper.createTypeMap(AddRouteBindingModel.class, Route.class);

        Provider<User> userProvider = req -> userService.getLoggedUser();

        Converter<Set<CategoryNames>, Set<Category>> toEntitySet
                = ctx -> (ctx.getSource() == null)
                         ? null
                         : categoryService.getAllByNameIn(ctx.getSource());

        typeMap
                .addMappings(mapper -> mapper
                        .using(toEntitySet)
                        .map(AddRouteBindingModel::getCategories, Route::setCategories))
                .addMappings(mapper -> mapper
                        .when(Conditions.isNull())
                        .with(userProvider)
                        .map(AddRouteBindingModel::getAuthor, Route::setAuthor));

        return modelMapper;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
