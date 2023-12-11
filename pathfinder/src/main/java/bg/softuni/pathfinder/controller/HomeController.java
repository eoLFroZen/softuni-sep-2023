package bg.softuni.pathfinder.controller;

import bg.softuni.pathfinder.model.dto.view.RouteIndexViewModel;
import bg.softuni.pathfinder.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final RouteService routeService;

    @GetMapping("/")
    public String index(Model model) {
        RouteIndexViewModel route = routeService.getMostCommentedRoute();

        model.addAttribute("mostCommentedRoute", route);

        return "index";

    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }
}
