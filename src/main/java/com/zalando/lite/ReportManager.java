package com.zalando.lite;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * Handles writing delivery reports to a text file.
 *
 * This class is responsible for exporting summaries of deliveries,
 * which may include courier, order, customer, and status information.
 *
 * It demonstrates:
 * - Java File I/O
 * - Try-with-resources pattern
 * - Handling checked exceptions (IOException)
 *
 * Concepts reinforced:
 * - File writing
 * - Resource management
 * - Exception handling
 */
public class ReportManager {

    /**
     * Writes a list of deliveries to a text file.
     *
     * Each delivery is written on a new line using its toString() method.
     *
     * @param deliveries list of completed or active deliveries
     * @param filePath path to the file where the report should be saved
     */
    public void exportDeliveryReport(List<Delivery> deliveries, String filePath) {
        // TODO: Use FileWriter wrapped in try-with-resources
        // TODO: Loop over deliveries and write each one to a new line
        // TODO: Catch and handle IOException with a user-friendly message

        try (FileWriter writer = new FileWriter(filePath)) {
            for (Delivery delivery : deliveries) {
                writer.write(delivery.toString());
                writer.write(System.lineSeparator());
            }
            System.out.println("✅ Delivery report exported to: " + filePath);
        } catch (IOException e) {
            System.err.println("❌ Failed to write delivery report: " + e.getMessage());
        }
    }

    /**
     * Optional: Helper to generate a default file path based on timestamp.
     *
     * @return a recommended file path for report export
     */
    public String getDefaultReportPath() {
        LocalDate today = LocalDate.now();
        return String.format("delivery-report-%s.txt", today.toString());
    }
}
