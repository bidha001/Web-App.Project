package no.ntnu.webapp.repositories;

import no.ntnu.webapp.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Category entity.
 * This interface extends JpaRepository to provide CRUD operations for Category entities.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
}
