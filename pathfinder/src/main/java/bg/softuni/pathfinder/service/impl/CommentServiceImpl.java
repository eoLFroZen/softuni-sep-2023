package bg.softuni.pathfinder.service.impl;

import bg.softuni.pathfinder.exceptions.CommentNotFountException;
import bg.softuni.pathfinder.model.Comment;
import bg.softuni.pathfinder.model.dto.binding.CreateCommentBindingModel;
import bg.softuni.pathfinder.repository.CommentRepository;
import bg.softuni.pathfinder.repository.RouteRepository;
import bg.softuni.pathfinder.repository.UserRepository;
import bg.softuni.pathfinder.service.CommentService;
import bg.softuni.pathfinder.service.session.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final RouteRepository routeRepository;
    private final UserRepository userRepository;
    private final LoggedUser loggedUser;
    private final ModelMapper mapper;

    public CommentServiceImpl(CommentRepository commentRepository, RouteRepository routeRepository, UserRepository userRepository, LoggedUser loggedUser, ModelMapper mapper) {
        this.commentRepository = commentRepository;
        this.routeRepository = routeRepository;
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
        this.mapper = mapper;
    }

    @Override
    public void create(CreateCommentBindingModel createCommentBindingModel) {
        Comment comment = mapper.map(createCommentBindingModel, Comment.class);

        commentRepository.save(comment);
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
