package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.model.Category;
import bg.softuni.pathfinder.model.enums.CategoryNames;

import java.util.Set;

public interface CategoryService {

    Set<Category> getAllByNameIn (Set<CategoryNames> categoryNames);

}
