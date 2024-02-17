package com.example.test;

import com.example.Components.DueDate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class DueDateTest {
    DueDate date;
    
    @Test
    void testNoDate(){
        date = new DueDate(null);
        assertEquals(date.isValid(), false);
    }

    @Test
    void testInvalidDate(){
        date = new DueDate(10249381390.);
        assertEquals(date.isValid(), false);
    }

    @Test
    void testPastDate(){
        date = new DueDate(2023/01/10);
        assertEquals(date.isValid(), false);
    }

    @Test
    void testInvalidFormat(){
        date = new DueDate(2025,01,10);
        assertEquals(date.isValid(), false);
    }

    @Test
    void testCorrectDate(){
        date = new DueDate(2025/01/10);
        assertEquals(date.isValid(), false);
    }
}
