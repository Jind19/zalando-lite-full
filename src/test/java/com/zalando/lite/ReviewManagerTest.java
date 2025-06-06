package com.zalando.lite;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link ReviewManager}.
 *
 * These tests verify:
 * - Reviews are properly added and linked to the correct product
 * - Multiple reviews can be added per product
 * - Retrieving reviews by product ID returns expected results
 * - System handles missing reviews safely (returns empty list, not null)
 *
 * Concepts reinforced:
 * - Nested collection testing: Map<Integer, List<Review>>
 * - Defensive testing (null handling)
 * - Composition: testing Product + Customer + Review
 */
public class ReviewManagerTest {

    private ReviewManager reviewManager;
    private Product sampleProduct;
    private Customer sampleCustomer;

    @BeforeEach
    void setUp() {
        // Initialize manager and test objects
        reviewManager = new ReviewManager();

        // Create sample product with ID = 1
        sampleProduct = new Product(1, "Sneakers", "Footwear", 49.99, 10, List.of("42", "43"));

        // Create sample customer
        sampleCustomer = new Customer("John", "john@example.com");

        // set customer ID
        sampleCustomer.setId(101);
    }

    @Test
    @DisplayName("Add a review and retrieve it by product ID")
    void testAddReviewAndRetrieve() {
        // TODO: Create a Review and add it
        Review review = new Review(sampleCustomer, sampleProduct, 5, "Great Shoes!" );
        reviewManager.addReview(review);
        // TODO: Retrieve reviews for product and assert it contains the review
        List<Review> reviews = reviewManager.getReviewsForProduct(sampleProduct.getId());
        assertNotNull(reviews);
        assertEquals(1, reviews.size());
        assertEquals("Great Shoes!", reviews.get(0).getComment());
    }

    @Test
    @DisplayName("Add multiple reviews for same product and verify all are returned")
    void testMultipleReviewsForSameProduct() {
        // TODO: Add 2–3 reviews for same product
        Review review1 = new Review(sampleCustomer, sampleProduct, 5, "Excellent quality!");
        Review review2 = new Review(sampleCustomer, sampleProduct, 4, "Very comfortable.");
        Review review3 = new Review(sampleCustomer, sampleProduct, 3, "Good, but a bit tight.");

        reviewManager.addReview(review1);
        reviewManager.addReview(review2);
        reviewManager.addReview(review3);

        // TODO: Retrieve list and assert size == 3
        List<Review> reviews = reviewManager.getReviewsForProduct(sampleProduct.getId());
        assertNotNull(reviews);
        assertEquals(3, reviews.size());
    }

    @Test
    @DisplayName("Return empty list when product has no reviews")
    void testRetrieveReviewsForNonReviewedProductReturnsEmptyList() {
        // TODO: Use a product ID with no reviews
        int unusedProductId = 999;

        // TODO: Assert that result is not null and isEmpty() is true
        List<Review> reviews = reviewManager.getReviewsForProduct(unusedProductId);

        assertNotNull(reviews, "Review list should not be null");
        assertTrue(reviews.isEmpty(), "Review list should be empty for a product with no reviews");
    }


    @AfterEach
    void tearDown() {
        // Optional: cleanup if static/shared state is used later
        System.out.println("✅ Test completed");
    }

}
