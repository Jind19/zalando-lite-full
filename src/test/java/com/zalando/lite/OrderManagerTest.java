package com.zalando.lite;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Unit tests for {@link OrderManager}.
 *
 * These tests verify:
 * - Order creation with valid stock
 * - Orders are stored and retrieved correctly
 * - Orders fail when products are out of stock
 * - Inventory updates correctly after order
 *
 * Concepts reinforced:
 * - Control flow testing
 * - State mutation (inventory reduction)
 * - Object relationships (Order → Customer + Items)
 */
public class OrderManagerTest {

    private InventoryManager inventoryManager;
    private OrderManager orderManager;
    private Customer testCustomer;

    @BeforeEach
    void setUp() {
        // Prepare managers and a test customer
        inventoryManager = new InventoryManager();
        orderManager = new OrderManager(inventoryManager);
        testCustomer = new Customer("Alice", "alice@example.com");
        // TODO: Set basic customer info (e.g., ID)
        testCustomer.setId(101);
    }

    @Test
    @DisplayName("Create order successfully when products have enough stock")
    void testCreateOrderSuccess() {
        // TODO: Add product with enough stock to inventory
        Product product = new Product(1, "T-Shirt", "Clothing", 19.99, 10, Arrays.asList("S", "M", "L"));
        inventoryManager.addProduct(product);
        // TODO: Create OrderItem and call createOrder
        OrderItem item = new OrderItem(product, 2);
        List<OrderItem> items = new ArrayList<>();
        items.add(item);

        Order order = orderManager.createOrder(testCustomer, items);

        assertNotNull(order);
        assertEquals(39.98, order.calculateTotal(), 0.01);
        assertEquals(testCustomer, order.getCustomer());
        assertEquals(1, order.getItems().size());
    }

    @Test
    @DisplayName("Fail order creation when product stock is insufficient")
    void testCreateOrderFailsIfOutOfStock() {
       // Product with 0 stock
        Product product = new Product(1, "T-Shirt", "Clothing", 19.99, 0, Arrays.asList("S", "M", "L"));
        inventoryManager.addProduct(product);

        // Create order item requesting quantity 1
        OrderItem item = new OrderItem(product, 1);
        List<OrderItem> items = new ArrayList<>();
        items.add(item);

        // Attempt to create order - expect failure (null or exception)
        Order order = orderManager.createOrder(testCustomer, items);

        // Assert order creation failed
        assertNull(order, "Order should fail when product stock is insufficient");
    }

    @Test
    @DisplayName("Store multiple orders per customer and retrieve correctly")
    void testOrdersAreStoredByCustomer() {
        Product product = new Product(3, "Hat", "Accessories", 14.99, 10, Arrays.asList("One Size"));
        inventoryManager.addProduct(product);

        OrderItem item1 = new OrderItem(product, 1);
        OrderItem item2 = new OrderItem(product, 2);

        orderManager.createOrder(testCustomer, List.of(item1));
        orderManager.createOrder(testCustomer, List.of(item2));

        // TODO: Retrieve orders using getOrdersForCustomer
        List<Order> orders = orderManager.getOrdersForCustomer(testCustomer.getId());

        // TODO: Assert the list contains both orders
        assertEquals(2, orders.size());
    }

    @Test
    @DisplayName("Update inventory correctly after order creation")
    void testInventoryUpdatedAfterOrder() {
        // TODO: Add product with known stock
        Product product = new Product(4, "Jeans", "Clothing", 59.99, 5, Arrays.asList("M", "L"));
        inventoryManager.addProduct(product);
        // TODO: Place order that reduces some stock
        OrderItem item = new OrderItem(product, 3);
        orderManager.createOrder(testCustomer, List.of(item));
        // TODO: Check that stock value is correctly reduced
        assertEquals(2, product.getStock(), "Stock should reduce after order");
    }

    @AfterEach
    void tearDown() {
        // Reset fields if necessary (optional for logic-based unit tests)
        inventoryManager = null;
        orderManager = null;
        testCustomer = null;
        System.out.println("🧹 Test resources cleaned up.");
    }
}
