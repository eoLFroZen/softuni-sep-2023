package bg.softuni.pathfinder.controller.rest;

import bg.softuni.pathfinder.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RouteRestController {

    public final RouteService routeService;

    @GetMapping("routes/coordinates/{id}")
    public List<List<Double>> getRouteCoordinates(@PathVariable("id") Long routeId) {
        return routeService.getCoordinates(routeId);
    }

}
