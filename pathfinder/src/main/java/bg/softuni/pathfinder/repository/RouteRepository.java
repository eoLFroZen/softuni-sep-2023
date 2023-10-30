package bg.softuni.pathfinder.repository;

import bg.softuni.pathfinder.model.Route;
import bg.softuni.pathfinder.model.enums.CategoryNames;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
    Route findByName(String name);

//    @Query(nativeQuery = true, value =
//            "SELECT * FROM routes r" +
//                    "LEFT JOIN routes_categories rc ON r.id == rc.route_id" +
//                    "LEFT JOIN categories c ON rc.category_id == c.id" +
//                    "WHERE c.name == ?1")
    List<Route> findAllByCategories_Name(CategoryNames categoryNames);
}
