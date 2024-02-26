package test;
import com.example.Components.Priority;

import static org.junit.Assert.*;
import org.junit.Test;


public class Prioritytest {

    @Test
    public void testValidPriority() {
        String validPriority = "Medium";
        Priority priority = new Priority(validPriority);
        assertTrue(priority.isValidPriority(validPriority));
    }

    @Test
    public void testInvalidPriority() {
        String invalidPriority = "Invalid";
        Priority priority = new Priority("");
        assertFalse(priority.isValidPriority(invalidPriority));
    }

    @Test
    public void testCaseInsensitivePriority() {
        String caseInsensitivePriority = "hIgH";
        Priority priority = new Priority("");
        assertTrue(priority.isValidPriority(caseInsensitivePriority));
    }

}