package ru.clevertec.collections;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.clevertec.collections.data.Data;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomArrayListTest {

    private static CustomArrayList<String> customArrayList;

    @BeforeEach
    public void initializeCustomArrayList() {
        customArrayList = Data.getDataForCustomArrayList();
    }

    @Test
    public void testSize() {
        assertEquals(100, customArrayList.size());
    }

    @Test
    public void testIsEmpty() {
        assertFalse(customArrayList.isEmpty());
    }

    @Test
    public void testContains() {
        assertTrue(customArrayList.contains("elem78"));
    }

    @Test
    public void testContainsIfElementNotExists() {
        assertFalse(customArrayList.contains("elem101"));
    }

    @Test
    public void testIndexOf() {
        assertEquals(56, customArrayList.indexOf("elem56"));
    }

    @Test
    public void testIndexOfIfElementNotContains() {
        assertEquals(-1, customArrayList.indexOf("elem101"));
    }

    @Test
    public void testLastIndexOf() {
        assertEquals(98, customArrayList.lastIndexOf("elem98"));
    }

    @Test
    public void testLastIndexOfIfElementNotContains() {
        assertEquals(-1, customArrayList.lastIndexOf("elem101"));
    }

    @Test
    public void testGet() {
        assertEquals("elem99", customArrayList.get(99));
    }

    @Test
    public void testAddWithOneParameters() {
        customArrayList.add("elem101");
        assertEquals("elem101", customArrayList.get(100));
    }

    @Test
    public void testAddWithTwoParameters() {
        customArrayList.add(5, "elem5test");
        assertEquals("elem5test", customArrayList.get(5));
    }

    @Test
    public void testRemoveWithObjectParameter() {
        customArrayList.remove("elem5test");
        assertEquals(100, customArrayList.size());
    }

    @Test
    public void testRemoveWithIntParameter() {
        customArrayList.remove(1);
        assertEquals(99, customArrayList.size());
    }

    @Test
    public void testAddAllWithOneParameter() {
        customArrayList.addAll(Arrays.asList("elem101test", "elem102test", "elem103test"));
        assertEquals(103, customArrayList.size());
    }

    @Test
    public void testAddAllWithTwoParameters() {
        customArrayList.addAll(56,
                Arrays.asList("elem101test", "elem102test", "elem103test"));
        assertEquals("elem101test", customArrayList.get(56));
    }

    @Test
    public void testSet() {
        customArrayList.set(92, "elem1000test");
        assertEquals("elem1000test", customArrayList.get(92));
    }

    @Test
    public void testClear() {
        customArrayList.clear();
        assertEquals(0, customArrayList.size());
    }
}
