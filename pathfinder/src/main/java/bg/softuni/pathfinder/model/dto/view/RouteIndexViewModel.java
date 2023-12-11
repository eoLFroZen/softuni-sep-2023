package bg.softuni.pathfinder.model.dto.view;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RouteIndexViewModel extends RouteViewModel {
    private List<PictureViewModel> pictures;
}
