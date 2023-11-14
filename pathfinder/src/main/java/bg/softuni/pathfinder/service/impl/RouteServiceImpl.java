package bg.softuni.pathfinder.service.impl;

import bg.softuni.pathfinder.exceptions.RouteNotFoundException;
import bg.softuni.pathfinder.model.Route;
import bg.softuni.pathfinder.model.dto.binding.AddRouteBindingModel;
import bg.softuni.pathfinder.model.dto.binding.UploadPictureRouteBindingModel;
import bg.softuni.pathfinder.model.dto.view.RouteCategoryViewModel;
import bg.softuni.pathfinder.model.dto.view.RouteDetailsViewModel;
import bg.softuni.pathfinder.model.dto.view.RouteViewModel;
import bg.softuni.pathfinder.model.enums.CategoryNames;
import bg.softuni.pathfinder.repository.RouteRepository;
import bg.softuni.pathfinder.service.RouteService;
import bg.softuni.pathfinder.service.helpers.PictureHelperService;
import bg.softuni.pathfinder.service.session.LoggedUser;
import io.jenetics.jpx.GPX;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class RouteServiceImpl implements RouteService {

    private static final String BASE_GPX_COORDINATES_PATH = ".\\src\\main\\resources\\coordinates\\";
    private static final String BASE_IMAGES_PATH = ".\\src\\main\\resources\\static\\images\\";
    private final RouteRepository routeRepository;
    private final PictureHelperService pictureHelperService;
    private final ModelMapper modelMapper;
    private final LoggedUser loggedUser;

    public RouteServiceImpl(RouteRepository routeRepository,
                            PictureHelperService pictureHelperService, ModelMapper modelMapper,
                            LoggedUser user) {
        this.routeRepository = routeRepository;
        this.pictureHelperService = pictureHelperService;
        this.modelMapper = modelMapper;
        this.loggedUser = user;
    }

    @Override
    public List<RouteViewModel> getAll() {
        return routeRepository.findAll().stream()
                .map(route -> modelMapper.map(route, RouteViewModel.class))
                .toList();
    }

    @Override
    public void add(AddRouteBindingModel addRouteBindingModel) {
        Route route = modelMapper.map(addRouteBindingModel, Route.class);

        String filePath = getFilePath(route.getName());
        boolean isUploaded = uploadGpxCoordinates(addRouteBindingModel.getGpxCoordinates(), filePath);

        if (isUploaded) {
            route.setGpxCoordinates(filePath);
        }

        routeRepository.save(route);
    }

    private boolean uploadGpxCoordinates(MultipartFile file, String filePath) {
        try {
            File newFile = new File(BASE_GPX_COORDINATES_PATH + filePath);
            newFile.getParentFile().mkdirs();
            newFile.createNewFile();

            OutputStream outputStream = new FileOutputStream(newFile);
            outputStream.write(file.getBytes());

            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    @Override
    public RouteDetailsViewModel getDetails(Long id) {
        Route route = routeRepository.findById(id)
                .orElseThrow(() -> new RouteNotFoundException("Route with id: " + id + " was not found!"));

        return modelMapper.map(route, RouteDetailsViewModel.class);
    }


    @Override
    public void uploadPicture(UploadPictureRouteBindingModel uploadPictureRouteBindingModel) {
        MultipartFile pictureFile = uploadPictureRouteBindingModel.getPicture();
        boolean isPrimary = uploadPictureRouteBindingModel.getIsPrimary();

        Route route = routeRepository.findById(uploadPictureRouteBindingModel.getId())
                .orElseThrow(() -> new RouteNotFoundException("Route not found!"));

        String picturePath = getPicturePath(pictureFile, route.getName(), isPrimary);

        try {
            File file = new File(BASE_IMAGES_PATH + picturePath);
            file.getParentFile().mkdirs();
            file.createNewFile();

            OutputStream outputStream = new FileOutputStream(file);
            outputStream.write(pictureFile.getBytes());

            if (isPrimary) {
                route.setImageUrl(picturePath);
                routeRepository.save(route);
            } else {
                pictureHelperService.create(route, picturePath);
            }
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }
    }

    @Override
    public List<RouteCategoryViewModel> getAllByCategory(CategoryNames categoryName) {
        List<Route> routes = routeRepository.findAllByCategories_Name(categoryName);
        List<RouteCategoryViewModel> viewRoutes = routes.stream()
                .map(route -> modelMapper.map(route, RouteCategoryViewModel.class))
                .toList();

        return viewRoutes;
    }

    @Override
    public List<List<Double>> getCoordinates(Long routeId) {
        Route route = this.routeRepository.findById(routeId)
                .orElseThrow(() -> new RouteNotFoundException("Route not found"));

        final String gpxCoordinates = route.getGpxCoordinates();

        if (gpxCoordinates == null) {
            return List.of(Collections.emptyList());
        }

        GPX gpx = GPX.Reader.DEFAULT.fromString(gpxCoordinates);

            return gpx.getTracks().get(0).getSegments().get(0).getPoints().stream()
                    .map(point -> List.of(point.getLongitude().doubleValue(),
                                          point.getLatitude().doubleValue()))
                    .toList();
    }

    private String getPicturePath(MultipartFile pictureFile, String routeName, boolean isPrimary) {
        String ext = getFileExtension(pictureFile.getOriginalFilename());

        String pathPattern = "%s\\%s\\%s." + ext;

        return String.format(pathPattern,
                transformRouteName(routeName),
                isPrimary ? "" : "gallery",
                UUID.randomUUID());
    }

    private String getFileExtension(String fileName) {
        String[] splitPictureName = fileName.split("\\.");
        return splitPictureName[splitPictureName.length - 1];
    }

    private String getFilePath(String routeName) {
        String pathPattern = "%s\\%s_%s.xml";
        return String.format(pathPattern,
                loggedUser.getUsername(),
                transformRouteName(routeName),
                UUID.randomUUID());
    }

    private String transformRouteName(String routeName) {
        return routeName.toLowerCase()
                .replaceAll("\\s+", "_");
    }


}
