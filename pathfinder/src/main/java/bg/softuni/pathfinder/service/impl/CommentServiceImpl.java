package bg.softuni.pathfinder.service.impl;

import bg.softuni.pathfinder.exceptions.CommentNotFountException;
import bg.softuni.pathfinder.model.Comment;
import bg.softuni.pathfinder.model.dto.binding.CreateCommentBindingModel;
import bg.softuni.pathfinder.model.dto.view.CommentViewModel;
import bg.softuni.pathfinder.repository.CommentRepository;
import bg.softuni.pathfinder.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final ModelMapper mapper;

    @Override
    public void create(CreateCommentBindingModel createCommentBindingModel) {
        Comment comment = mapper.map(createCommentBindingModel, Comment.class);
        comment.setId(null);

        commentRepository.save(comment);
    }

    @Override
    public CommentViewModel createRest(CreateCommentBindingModel createCommentBindingModel) {
        Comment comment = mapper.map(createCommentBindingModel, Comment.class);
        comment.setId(null);

        return mapper.map(commentRepository.save(comment), CommentViewModel.class);
    }

    @Override
    public void approve(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFountException("Comment with id: " + id + " was not found"));

        comment.setApproved(true);

        commentRepository.save(comment);
    }

    @Override
    public void delete(Long id) {
        commentRepository.deleteById(id);
    }
}
