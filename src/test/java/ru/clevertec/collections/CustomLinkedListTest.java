package ru.clevertec.collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.clevertec.collections.data.Data;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomLinkedListTest {

    private static CustomLinkedList<String> customLinkedList;

    @BeforeEach
    public void initializeCustomArrayList() {
        customLinkedList = Data.getDataForCustomLinkedList();
    }

    @Test
    public void testSize() {
        assertEquals(100, customLinkedList.size());
    }

    @Test
    public void testIsEmpty() {
        assertFalse(customLinkedList.isEmpty());
    }

    @Test
    public void testContains() {
        assertTrue(customLinkedList.contains("elem78"));
    }

    @Test
    public void testContainsIfElementNotExists() {
        assertFalse(customLinkedList.contains("elem101"));
    }

    @Test
    public void testIndexOf() {
        assertEquals(56, customLinkedList.indexOf("elem56"));
    }

    @Test
    public void testIndexOfIfElementNotContains() {
        assertEquals(-1, customLinkedList.indexOf("elem101"));
    }

    @Test
    public void testLastIndexOf() {
        assertEquals(98, customLinkedList.lastIndexOf("elem98"));
    }

    @Test
    public void testLastIndexOfIfElementNotContains() {
        assertEquals(-1, customLinkedList.lastIndexOf("elem101"));
    }

    @Test
    public void testGet() {
        assertEquals("elem99", customLinkedList.get(99));
    }

    @Test
    public void testAddWithOneParameters() {
        customLinkedList.add("elem101");
        assertEquals("elem101", customLinkedList.get(100));
    }

    @Test
    public void testAddWithTwoParameters() {
        customLinkedList.add(5, "elem5test");
        assertEquals("elem5test", customLinkedList.get(5));
    }

    @Test
    public void testRemoveWithObjectParameter() {
        customLinkedList.remove("elem5test");
        assertEquals(100, customLinkedList.size());
    }

    @Test
    public void testRemoveWithIntParameter() {
        customLinkedList.remove(1);
        assertEquals(99, customLinkedList.size());
    }

    @Test
    public void testAddAllWithOneParameter() {
        customLinkedList.addAll(Arrays.asList("elem101test", "elem102test", "elem103test"));
        assertEquals(103, customLinkedList.size());
    }

    @Test
    public void testAddAllWithTwoParameters() {
        customLinkedList.addAll(56,
                Arrays.asList("elem101test", "elem102test", "elem103test"));
        assertEquals("elem101test", customLinkedList.get(56));
    }

    @Test
    public void testSet() {
        customLinkedList.set(92, "elem1000test");
        assertEquals("elem1000test", customLinkedList.get(92));
    }

    @Test
    public void testClear() {
        customLinkedList.clear();
        assertEquals(0, customLinkedList.size());
    }
}
