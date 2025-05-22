package no.ntnu.webapp.repositories;

import no.ntnu.webapp.models.CourseProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CourseProviderRepository extends JpaRepository<CourseProvider, Long> {
    /**
     * Find providers by course ID
     * @param courseId ID of the course
     * @return List of providers for the specified course
     */
    List<CourseProvider> findByCourse_CourseId(Long courseId);
    
    /**
     * Find providers by name
     * @param providerName Name of the provider
     * @return List of providers with the specified name
     */
    List<CourseProvider> findByProviderName(String providerName);
    
    /**
     * Find providers by price range
     * @param minPrice Minimum price
     * @param maxPrice Maximum price
     * @return List of providers within the specified price range
     */
    List<CourseProvider> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
}