package bg.softuni.pathfinder.controller;

import bg.softuni.pathfinder.model.dto.binding.AddRouteBindingModel;
import bg.softuni.pathfinder.model.dto.view.RouteViewModel;
import bg.softuni.pathfinder.model.enums.CategoryNames;
import bg.softuni.pathfinder.model.enums.Level;
import bg.softuni.pathfinder.service.RouteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/routes")
public class RoutesController {

    private final RouteService routeService;

    public RoutesController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping
    public ModelAndView getAll() {
        List<RouteViewModel> routes = routeService.getAll();

        ModelAndView modelAndView = new ModelAndView("routes");
        modelAndView.addObject("routes", routes);

        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView addRoute() {
        ModelAndView modelAndView = new ModelAndView("add-route");

        modelAndView.addObject("levels", Level.values());
        modelAndView.addObject("categories", CategoryNames.values());

        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView addRoute(AddRouteBindingModel addRouteBindingModel) {

        routeService.add(addRouteBindingModel);

        return new ModelAndView("redirect:/");
    }
}
