package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.model.dto.binding.CreateCommentBindingModel;
import bg.softuni.pathfinder.model.dto.view.CommentViewModel;

public interface CommentService {

    void create(CreateCommentBindingModel createCommentBindingModel);

    CommentViewModel createRest(CreateCommentBindingModel createCommentBindingModel);

    void approve(Long id);

    void delete(Long id);
}
