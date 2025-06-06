package com.zalando.lite;

import java.util.*;

/**
 * The entry point of the ZalandoLite application.
 *
 * This class is where the entire system starts. Eventually, this will become
 * an interactive console app that:
 *
 * - Displays a menu of actions (view products, place orders, etc.)
 * - Reads user input using Scanner
 * - Connects to manager classes like InventoryManager, OrderManager, etc.
 *
 * Right now, it simply prints a welcome message — but it will evolve into
 * your command-line interface (CLI).
 *
 * Concepts reinforced:
 * - Main method structure
 * - Execution flow
 * - Integration of system components
 */
public class Main {

    public static void main(String[] args) {

        Product p1 = new Product(1, "Leather Jacket", "Jackets", 89.99, 10, new ArrayList<>(Arrays.asList("S", "M", "L")));
        Product p2 = new Product(2, "Running Shoes", "Shoes", 59.49, 15, new ArrayList<>(Arrays.asList("M", "L")));
        Product p3 = new Product(3, "Wool Scarf", "Accessories", 25.00, 30, new ArrayList<>(Arrays.asList("one size")));
        Scanner scanner = new Scanner(System.in);
        CustomerManager customerManager = new CustomerManager();
        InventoryManager inventoryManager = new InventoryManager();
        OrderManager orderManager = new OrderManager(inventoryManager);
        ReviewManager reviewManager = new ReviewManager();

        inventoryManager.addProduct(p1);
        inventoryManager.addProduct(p2);
        inventoryManager.addProduct(p3);

        Customer customer;

        boolean exit = false;

        // Startup message to indicate the system has launched
        System.out.println("Welcome to ZalandoLite!");
        int currentCustomerId = 0;
        while (!exit) {
            System.out.println("\nPlease select an option:");
            System.out.println("1: Register as a Customer");
            System.out.println("2: Show Produts List");
            System.out.println("3: Create Order");
            System.out.println("4: Leave Review");
            System.out.println("5: Exit");

            System.out.print("Enter choice (1-5): ");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    System.out.println("Enter your name");
                    String name = scanner.nextLine();

                    System.out.println("Enter you Email address");
                    String email = scanner.nextLine();

                    customer = new Customer(name, email);
                    customerManager.registerCustomer(customer);

                    currentCustomerId=customer.getId();

                    System.out.println("\n✅ " + name + ", you are successfully registered! Your customer ID is " + currentCustomerId);
                    break;

                case "2":
                    List<Product> products = inventoryManager.listAllProducts();
                    if (products.isEmpty()) {
                        System.out.println("🚫 No products available in inventory.");
                    } else {
                        System.out.println("\n🛍️ Available Products:");
                        for (Product p : products) {
                            System.out.println(p);  // Ensure your Product class has a good toString() method
                        }
                    }
                    break;

                case "3":
                    List<OrderItem> items = new ArrayList<>();
                    do {
                        System.out.println("🛒 Enter product ID:");
                        int productId = Integer.parseInt(scanner.nextLine());

                        Product product = inventoryManager.findProductById(productId);
                        if (product == null) {
                            System.out.println("❌ Product not found. Please enter a valid product ID.");
                            continue;
                        }

                        System.out.println("📦 Enter quantity:");
                        int quantity = Integer.parseInt(scanner.nextLine());

                        // 🔍 Check if quantity exceeds stock
                        if (quantity > product.getStock()) {
                            System.out.println("❌ Not enough stock. Available: " + product.getStock());
                            System.out.println("Do you want to try again with a smaller quantity? (yes/no)");
                            String retry = scanner.nextLine();
                            if (retry.equalsIgnoreCase("yes")) {
                                continue; // re-enter product ID and quantity
                            } else {
                                System.out.println("⏭️ Skipping this product.");
                                continue;
                            }
                        }
                        items.add(new OrderItem(product, quantity));

                        System.out.println("➕ Add another product? (yes/no)");

                    } while (scanner.nextLine().equalsIgnoreCase("yes"));

                    customer = customerManager.getCustomerById(currentCustomerId);

                    if (customer != null && !items.isEmpty()) {
                        Order order = orderManager.createOrder(customer, items);
                        System.out.println("✅ Order created successfully for " + customer.getName() + "!");

                        // 🧾 Print order summary
                        System.out.println("🧾 Order Summary:");
                        double total = 0.0;
                        for (OrderItem item : items) {
                            double subTotal = item.getSubtotal();
                            total += subTotal;
                            System.out.printf("- %s x %d @ €%.2f each → Subtotal: €%.2f%n",
                                    item.getProduct().getName(),
                                    item.getQuantity(),
                                    item.getProduct().getPrice(),
                                    subTotal);
                        }
                        System.out.println("Total Cart Value : " + total);
                    } else {
                        System.out.println("❌ Order creation failed. Please check your inputs.");
                    }

                    break;

                case "4":
                    String continueReviewing;
                    do {
                        System.out.println("Enter Product ID to review:");
                        int productId = Integer.parseInt(scanner.nextLine());

                        Product product = inventoryManager.findProductById(productId);
                        if (product == null) {
                            System.out.println("❌ Product not found.");
                            break;
                        }

                        int rating; // Variable to store user's rating
                        do {
                            System.out.println("Enter your rating (1-5):");
                            try {
                                rating = Integer.parseInt(scanner.nextLine());
                                if (rating < 1 || rating > 5) {
                                    System.out.println("❌ Please enter a number between 1 and 5.");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("❌ Invalid input. Please enter a number between 1 and 5.");
                                rating = -1; // Invalid sentinel to repeat loop
                            }
                        } while (rating < 1 || rating > 5); // Repeat if rating is invalid


                        System.out.println("Enter your review comment:");
                        String comment = scanner.nextLine();

                        customer = customerManager.getCustomerById(currentCustomerId);
                        if (customer == null) {
                            System.out.println("❌ Customer not found.");
                            break;
                        }

                        Review review = new Review(customer, product, rating, comment);
                        reviewManager.addReview(review);
                        System.out.println("✅ Review submitted:");
                        System.out.println(review);

                        System.out.println("Do you want to add another review? (yes/no):");
                        continueReviewing = scanner.nextLine().trim().toLowerCase();
                    } while (continueReviewing.equals("yes"));
                    break;

                default:
                    System.out.println("Invalid input. Please select a valid option.");
            }
        }
        // TODO: Create scanner input loop
        // TODO: Display menu options and call manager methods
        // TODO: Use switch-case or if-else to control flow
        // TODO: Instantiate managers (InventoryManager, CustomerManager, etc.)
    }
}
