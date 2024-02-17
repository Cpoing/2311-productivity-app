package com.example.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class Prioritytest {

    @Test
    void testValidPriority() {
        String validPriority = "Medium";
        Priority priority = new Priority(validPriority);
        assertTrue(priority.isValidPriority(validPriority));
    }

    @Test
    void testInvalidPriority() {
        String invalidPriority = "Invalid";
        Priority priority = new Priority("");
        assertFalse(priority.isValidPriority(invalidPriority));
    }

    @Test
    void testCaseInsensitivePriority() {
        String caseInsensitivePriority = "hIgH";
        Priority priority = new Priority("");
        assertTrue(priority.isValidPriority(caseInsensitivePriority));
    }

}