package no.ntnu.webapp.services;

import no.ntnu.webapp.models.CourseProvider;
import no.ntnu.webapp.repositories.CourseProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Service class for handling CourseProvider-related business logic
 */
@Service
public class CourseProviderService {

    private final CourseProviderRepository courseProviderRepository;

    @Autowired
    public CourseProviderService(CourseProviderRepository courseProviderRepository) {
        this.courseProviderRepository = courseProviderRepository;
    }

    /**
     * Get all course providers
     * @return List of all course providers
     */
    public List<CourseProvider> getAllProviders() {
        return courseProviderRepository.findAll();
    }

    /**
     * Get course provider by ID
     * @param providerId ID of the provider
     * @return Optional containing the provider if found
     */
    public Optional<CourseProvider> getProviderById(Long providerId) {
        return courseProviderRepository.findById(providerId);
    }

    /**
     * Get providers for a specific course
     * @param courseId ID of the course
     * @return List of providers for the specified course
     */
    public List<CourseProvider> getProvidersByCourse(Long courseId) {
        return courseProviderRepository.findByCourse_CourseId(courseId);
    }

    /**
     * Get providers by name
     * @param providerName Name of the provider
     * @return List of providers with the specified name
     */
    public List<CourseProvider> getProvidersByName(String providerName) {
        return courseProviderRepository.findByProviderName(providerName);
    }

    /**
     * Get providers by price range
     * @param minPrice Minimum price
     * @param maxPrice Maximum price
     * @return List of providers within the specified price range
     */
    public List<CourseProvider> getProvidersByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return courseProviderRepository.findByPriceBetween(minPrice, maxPrice);
    }

    /**
     * Find the provider with the lowest price for a course
     * @param courseId ID of the course
     * @return The provider with the lowest price, or null if no providers found
     */
    public CourseProvider getCheapestProviderForCourse(Long courseId) {
        List<CourseProvider> providers = getProvidersByCourse(courseId);
        return providers.stream()
                .min((p1, p2) -> p1.getPrice().compareTo(p2.getPrice()))
                .orElse(null);
    }

    /**
     * Save or update a course provider
     * @param provider Provider to save or update
     * @return Saved provider
     */
    public CourseProvider saveProvider(CourseProvider provider) {
        return courseProviderRepository.save(provider);
    }

    /**
     * Delete a provider by ID
     * @param providerId ID of the provider to delete
     */
    public void deleteProvider(Long providerId) {
        courseProviderRepository.deleteById(providerId);
    }
}