package com.zalando.lite;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link CustomerManager} class.
 *
 * These tests verify:
 * - Registering a new customer
 * - Retrieving a customer by ID
 * - Null handling when customer is not found
 *
 * Demonstrates how to use JUnit 5 annotations:
 * - @BeforeAll for one-time setup
 * - @BeforeEach for per-test setup
 * - @AfterEach and @AfterAll for cleanup logic (if needed)
 *
 * Concepts reinforced:
 * - JUnit lifecycle
 * - Assertions for equality and null checks
 * - Testing collections and object references
 */
public class CustomerManagerTest {

    private CustomerManager customerManager;

    @BeforeAll
    static void initAll() {
        // Runs once before all tests
        // Can be used for heavy setup, e.g., static mock data
        System.out.println("🔧 Starting CustomerManager tests...");
    }

    @BeforeEach
    void init() {
        // Runs before each test
        // Fresh instance of manager ensures test isolation
        customerManager = new CustomerManager();
    }

    @Test
    @DisplayName("✅ Register and retrieve a customer by ID")
    void testRegisterAndRetrieveCustomer() {
        // TODO: Create a new Customer
        Customer customer = new Customer("Alice", "alice@example.com");

        // TODO: Register it
        customerManager.registerCustomer(customer);

        // TODO: Retrieve it by ID and assert it's the same
        Customer retrieved = customerManager.getCustomerById(customer.getId());

        // Assert that retrieved object is not null and matches the original
        assertNotNull(retrieved, "Customer should not be null");
        assertEquals(customer.getId(), retrieved.getId(), "IDs should match");
        assertEquals(customer.getName(), retrieved.getName(), "Names should match");
        assertEquals(customer.getEmail(), retrieved.getEmail(), "Emails should match");
    }

    @Test
    @DisplayName("❌ Retrieving non-existent customer returns null")
    void testRetrieveNonExistentCustomerReturnsNull() {
        // TODO: Try getting a customer with an unused ID
        Customer nonExistent = customerManager.getCustomerById(999);

        // TODO: Assert that the result is null
        assertNull(nonExistent, "Customer should be null if ID does not exist");
    }

    @AfterEach
    void tearDown() {
        // Runs after each test
        // Could be used to clear static data if shared
        System.out.println("🧹 Cleaned up after test");
    }

    @AfterAll
    static void tearDownAll() {
        // Runs once after all tests
        // Used for global cleanup (e.g., closing DB connections)
        System.out.println("🏁 All tests completed. Final cleanup done.");
    }
}
