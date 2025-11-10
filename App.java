package com.example;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Main application class for GitHub Expressions Demo
 * Demonstrates various application features that can be tested
 * with GitHub Actions contexts and expressions
 */
public class App {
    
    private Map<String, String> environmentInfo;
    
    public App() {
        this.environmentInfo = new HashMap<>();
        initializeEnvironmentInfo();
    }
    
    private void initializeEnvironmentInfo() {
        environmentInfo.put("java.version", System.getProperty("java.version"));
        environmentInfo.put("os.name", System.getProperty("os.name"));
        environmentInfo.put("user.name", System.getProperty("user.name"));
        environmentInfo.put("timestamp", LocalDateTime.now().toString());
    }
    
    /**
     * Returns environment information collected by the application
     */
    public Map<String, String> getEnvironmentInfo() {
        return new HashMap<>(environmentInfo);
    }
    
    /**
     * Simple string manipulation method for testing
     */
    public String processString(String input) {
        if (input == null || input.trim().isEmpty()) {
            return "DEFAULT";
        }
        return input.toUpperCase().replace(" ", "_");
    }
    
    /**
     * Mathematical operation for testing
     */
    public int calculate(int a, int b, String operation) {
        switch (operation.toLowerCase()) {
            case "add":
                return a + b;
            case "subtract":
                return a - b;
            case "multiply":
                return a * b;
            case "divide":
                if (b == 0) throw new ArithmeticException("Division by zero");
                return a / b;
            default:
                throw new IllegalArgumentException("Unknown operation: " + operation);
        }
    }
    
    /**
     * Method to simulate application status
     */
    public String getApplicationStatus() {
        return "RUNNING";
    }
    
    /**
     * Main method - application entry point
     */
    public static void main(String[] args) {
        System.out.println("=== GitHub Expressions Demo Application ===");
        
        App app = new App();
        
        // Display environment information
        System.out.println("\nEnvironment Information:");
        app.getEnvironmentInfo().forEach((key, value) -> {
            System.out.printf("  %s: %s%n", key, value);
        });
        
        // Demonstrate string processing
        System.out.println("\nString Processing Examples:");
        String[] testInputs = {"hello world", "github actions", "test input"};
        for (String input : testInputs) {
            String processed = app.processString(input);
            System.out.printf("  '%s' -> '%s'%n", input, processed);
        }
        
        // Demonstrate calculations
        System.out.println("\nCalculation Examples:");
        int[][] calculations = {{5, 3}, {10, 2}, {8, 4}};
        String[] operations = {"add", "multiply", "subtract"};
        
        for (int i = 0; i < calculations.length; i++) {
            try {
                int result = app.calculate(calculations[i][0], calculations[i][1], operations[i]);
                System.out.printf("  %d %s %d = %d%n", 
                    calculations[i][0], operations[i], calculations[i][1], result);
            } catch (Exception e) {
                System.out.printf("  Error: %s%n", e.getMessage());
            }
        }
        
        System.out.println("\nApplication Status: " + app.getApplicationStatus());
        System.out.println("=== Application Completed ===");
    }
}
