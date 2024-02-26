package test;
import com.example.Components.DueDate;

import static org.junit.Assert.*;
import org.junit.Test;

public class DueDateTest {
    DueDate date;
    
    @Test
    public void testNoDate(){
        date = new DueDate("");
        assertFalse(date.isValid());
    }

    @Test
    public void testInvalidDate(){
        date = new DueDate("10249381390");
        assertFalse(date.isValid());
    }

    @Test
    public void testPastDate(){
        date = new DueDate("2023/01/10");
        assertFalse(date.isValid());
    }

    @Test
    public void testInvalidFormat(){
        date = new DueDate("2025,01,10");
        assertFalse(date.isValid());
    }

    @Test
    public void testCorrectDate(){
        date = new DueDate("2025/01/10");
        assertTrue(date.isValid());
    }
}
