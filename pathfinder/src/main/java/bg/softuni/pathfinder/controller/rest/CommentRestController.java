package bg.softuni.pathfinder.controller.rest;

import bg.softuni.pathfinder.model.dto.binding.CreateCommentBindingModel;
import bg.softuni.pathfinder.model.dto.view.CommentViewModel;
import bg.softuni.pathfinder.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentRestController {

    private final CommentService commentService;

    @PostMapping("/create")
    public CommentViewModel create(@RequestBody CreateCommentBindingModel createCommentBindingModel) {
        return commentService.createRest(createCommentBindingModel);
    }
}
