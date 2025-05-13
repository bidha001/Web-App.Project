package no.ntnu.webapp.services;

import no.ntnu.webapp.models.Category;
import no.ntnu.webapp.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for handling Category-related business logic
 */
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * Get all categories
     * @return List of all categories
     */
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    /**
     * Get category by ID
     * @param categoryId ID of the category
     * @return Optional containing the category if found
     */
    public Optional<Category> getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }

    /**
     * Get category by name
     * @param name Name of the category
     * @return Category with the specified name
     */
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    /**
     * Save or update a category
     * @param category Category to save or update
     * @return Saved category
     */
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    /**
     * Delete a category by ID
     * @param categoryId ID of the category to delete
     */
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
