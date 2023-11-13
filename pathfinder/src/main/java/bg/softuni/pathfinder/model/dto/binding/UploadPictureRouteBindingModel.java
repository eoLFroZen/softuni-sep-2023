package bg.softuni.pathfinder.model.dto.binding;

import bg.softuni.pathfinder.validation.anotations.FileAnnotation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
@NoArgsConstructor
public class UploadPictureRouteBindingModel {

    private long id;

    @FileAnnotation(contentTypes = {"image/png", "image/jpeg"})
    private MultipartFile picture;

    private Boolean isPrimary = false;
}
