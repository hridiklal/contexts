package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the GitHub Expressions Demo Application
 * These tests will be run in GitHub Actions workflow
 */
class AppTest {
    
    private App app;
    
    @BeforeEach
    void setUp() {
        app = new App();
    }
    
    @Test
    @DisplayName("Test environment information collection")
    void testEnvironmentInfo() {
        Map<String, String> envInfo = app.getEnvironmentInfo();
        
        assertNotNull(envInfo, "Environment info should not be null");
        assertFalse(envInfo.isEmpty(), "Environment info should not be empty");
        assertTrue(envInfo.containsKey("java.version"), "Should contain Java version");
        assertTrue(envInfo.containsKey("os.name"), "Should contain OS name");
    }
    
    @Test
    @DisplayName("Test string processing with valid input")
    void testProcessString_ValidInput() {
        assertEquals("HELLO_WORLD", app.processString("hello world"));
        assertEquals("GITHUB_ACTIONS", app.processString("github actions"));
        assertEquals("TEST", app.processString("test"));
    }
    
    @Test
    @DisplayName("Test string processing with edge cases")
    void testProcessString_EdgeCases() {
        assertEquals("DEFAULT", app.processString(""));
        assertEquals("DEFAULT", app.processString("   "));
        assertEquals("DEFAULT", app.processString(null));
    }
    
    @Test
    @DisplayName("Test mathematical operations")
    void testCalculate() {
        // Addition
        assertEquals(8, app.calculate(5, 3, "add"));
        
        // Subtraction
        assertEquals(2, app.calculate(5, 3, "subtract"));
        
        // Multiplication
        assertEquals(15, app.calculate(5, 3, "multiply"));
        
        // Division
        assertEquals(2, app.calculate(6, 3, "divide"));
    }
    
    @Test
    @DisplayName("Test division by zero")
    void testCalculate_DivisionByZero() {
        ArithmeticException exception = assertThrows(ArithmeticException.class, 
            () -> app.calculate(5, 0, "divide"));
        assertEquals("Division by zero", exception.getMessage());
    }
    
    @Test
    @DisplayName("Test invalid operation")
    void testCalculate_InvalidOperation() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> app.calculate(5, 3, "invalid"));
        assertTrue(exception.getMessage().contains("Unknown operation"));
    }
    
    @Test
    @DisplayName("Test application status")
    void testGetApplicationStatus() {
        assertEquals("RUNNING", app.getApplicationStatus());
    }
    
    @Test
    @DisplayName("Integration test - multiple operations")
    void testIntegration() {
        // Test a sequence of operations
        String processed = app.processString("test input");
        assertEquals("TEST_INPUT", processed);
        
        int result = app.calculate(10, 2, "multiply");
        assertEquals(20, result);
        
        String status = app.getApplicationStatus();
        assertEquals("RUNNING", status);
    }
}
