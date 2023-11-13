package bg.softuni.pathfinder.service.helpers;

import bg.softuni.pathfinder.model.Picture;
import bg.softuni.pathfinder.model.Route;
import bg.softuni.pathfinder.repository.PictureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PictureHelperService {
    private final PictureRepository pictureRepository;
    private final LoggedUserHelperService loggedUserHelperService;

    public void create(Route route, String picturePath){
        Picture picture = new Picture();

        picture.setTitle(route.getName());
        picture.setRoute(route);
        picture.setUrl(picturePath);
        picture.setAuthor(loggedUserHelperService.get());

        pictureRepository.save(picture);
    }
}
