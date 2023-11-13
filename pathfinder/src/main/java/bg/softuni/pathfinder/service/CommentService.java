package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.model.dto.binding.CreateCommentBindingModel;

public interface CommentService {

    void create(CreateCommentBindingModel createCommentBindingModel);

    void approve(Long id);

    void delete(Long id);
}
