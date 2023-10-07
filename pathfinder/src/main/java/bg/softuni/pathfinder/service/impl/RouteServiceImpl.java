package bg.softuni.pathfinder.service.impl;

import bg.softuni.pathfinder.model.Route;
import bg.softuni.pathfinder.model.dto.AddRouteBindingModel;
import bg.softuni.pathfinder.repository.RouteRepository;
import bg.softuni.pathfinder.service.RouteService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class RouteServiceImpl implements RouteService {
    private final RouteRepository routeRepository;
    private final ModelMapper modelMapper;

    public RouteServiceImpl (RouteRepository routeRepository,
                             ModelMapper modelMapper) {

        this.routeRepository = routeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void add(AddRouteBindingModel addRouteBindingModel) {

        Route route = modelMapper.map(addRouteBindingModel, Route.class);
        System.out.println();

        routeRepository.save(route);
    }
}
