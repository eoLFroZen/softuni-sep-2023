package bg.softuni.pathfinder.model.dto.binding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateCommentBindingModel {

    private long routeId;
    private String textContent;
}
