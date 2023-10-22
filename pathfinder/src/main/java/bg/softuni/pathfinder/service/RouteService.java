package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.model.dto.binding.AddRouteBindingModel;
import bg.softuni.pathfinder.model.dto.view.RouteDetailsViewModel;
import bg.softuni.pathfinder.model.dto.view.RouteViewModel;

import java.util.List;

public interface RouteService {

    void add(AddRouteBindingModel addRouteBindingModel);

    List<RouteViewModel> getAll();

    RouteDetailsViewModel getDetails(Long id);
}
