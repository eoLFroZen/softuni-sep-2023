package bg.softuni.pathfinder.repository;

import bg.softuni.pathfinder.model.Category;
import bg.softuni.pathfinder.model.enums.CategoryNames;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Set<Category> getAllByNameIn (Set<CategoryNames> categories);
}
