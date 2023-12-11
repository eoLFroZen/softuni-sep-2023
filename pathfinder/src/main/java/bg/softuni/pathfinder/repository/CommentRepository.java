package bg.softuni.pathfinder.repository;

import bg.softuni.pathfinder.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "SELECT route_id FROM comments " +
            "GROUP BY route_id " +
            "ORDER BY COUNT(id) DESC " +
            "LIMIT 1", nativeQuery = true)
    Long getMostCommentedRouteId();
}
