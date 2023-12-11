package bg.softuni.pathfinder.service.helpers;

import bg.softuni.pathfinder.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentHelperService {

    private final CommentRepository commentRepository;

    public Long getMostCommentedRouteId() {
        return commentRepository.getMostCommentedRouteId();
    }
}
