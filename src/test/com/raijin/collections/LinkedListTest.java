package com.raijin.collections;

import org.junit.Test;

import java.util.*;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class LinkedListTest {

    @Test
    public void testSizeWhenSizeIs0()  {
        final java.util.LinkedList<Integer> testInstance = new java.util.LinkedList<>();
        assertEquals(0, testInstance.size());
    }

    @Test
    public void testIsEmptyWhenEmpty()  {
        final java.util.LinkedList<Integer> testInstance = new java.util.LinkedList<>();
        assertTrue(testInstance.isEmpty());
    }

    @Test
    public void testContains() {
        final java.util.LinkedList<Integer> testInstance = new java.util.LinkedList<>();
        testInstance.add(1);
        testInstance.add(2);

        assertTrue(testInstance.contains(1));
        assertFalse(testInstance.contains(0));
    }

    @Test
    public void testContainsOnNull() {
        final java.util.LinkedList<Object> testInstance = new java.util.LinkedList<>();
        testInstance.add(1);
        testInstance.add(null);
        testInstance.add("s");

        assertTrue(testInstance.contains(1));
        assertTrue(testInstance.contains(null));
        assertTrue(testInstance.contains("s"));
        assertFalse(testInstance.contains("d"));
    }

    @Test
    public void testToArrayWhenInputArrayHaveSizeOne()  {
        final java.util.LinkedList<Integer> testInstance = new java.util.LinkedList<>();
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);

        final Integer[] input = new Integer[1];

        final Integer[] result = testInstance.toArray(input);
        assertNotEquals(input, result);
        assertEquals((Integer)1, result[0]);
        assertEquals((Integer)2, result[1]);
        assertEquals((Integer)3, result[2]);
        assertEquals(3, result.length);
    }

    @Test
    public void testToArrayWhenInputArrayHaveCorrectSize() {
        final java.util.LinkedList<Integer> testInstance = new java.util.LinkedList<>();
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);

        final Integer[] input = new Integer[3];

        final Integer[] result = testInstance.toArray(input);
        assertArrayEquals(input, result);
    }


    @Test
    public void testAdd()  {
        final java.util.LinkedList<Integer> testInstance = new java.util.LinkedList<>();
        testInstance.add(1);
        testInstance.add(1);

        assertEquals(2, testInstance.size());
        assertFalse(testInstance.isEmpty());
    }

    @Test
    public void testRemoveFirstIndex()  {
        final java.util.LinkedList<String> testInstance = new java.util.LinkedList<>();
        testInstance.add("a");
        testInstance.add("b");
        testInstance.add("c");
        testInstance.remove(0);

        assertEquals(2, testInstance.size());
        assertFalse(testInstance.isEmpty());
    }

    @Test
    public void testRemoveLastIndex()  {
        final java.util.LinkedList<String> testInstance = new java.util.LinkedList<>();
        testInstance.add("a");
        testInstance.add("b");
        testInstance.add("c");
        testInstance.remove(2);
        testInstance.remove(1);

        assertEquals(1, testInstance.size());
        assertFalse(testInstance.isEmpty());
    }

    @Test
    public void testRemoveFirstItem()  {
        final java.util.LinkedList<String> testInstance = new java.util.LinkedList<>();
        testInstance.add("a");
        testInstance.add("b");
        testInstance.add("c");
        testInstance.remove("a");

        assertEquals(2, testInstance.size());
        assertFalse(testInstance.isEmpty());
    }

    @Test
    public void testRemoveLastItem()  {
        final java.util.LinkedList<String> testInstance = new java.util.LinkedList<>();
        testInstance.add("a");
        testInstance.add("b");
        testInstance.add("c");
        testInstance.remove("c");
        testInstance.remove("b");

        assertEquals(1, testInstance.size());
        assertFalse(testInstance.isEmpty());
    }

    @Test
    public void testContainsAll() {
        final java.util.LinkedList<Integer> testInstance = new java.util.LinkedList<>();
        final java.util.LinkedList<Integer> testInstance2 = new java.util.LinkedList<>();
        testInstance.add(1);
        testInstance.add(2);

        testInstance2.add(2);
        testInstance2.add(1);

        assertTrue(testInstance.containsAll(testInstance2));
    }

    @Test
    public void testAddAll(){
        final java.util.LinkedList<Integer> testInstance = new java.util.LinkedList<>();
        testInstance.add(1);
        testInstance.add(2);

        testInstance.add(3);
        testInstance.add(4);

        assertTrue(testInstance.contains(3));
        assertTrue(testInstance.contains(4));
    }

    @Test
    public void testRemoveAll() {
        final java.util.LinkedList<Integer> testInstance = new java.util.LinkedList<>();
        final java.util.LinkedList<Integer> testInstance2 = new java.util.LinkedList<>();
        testInstance.add(1);
        testInstance.add(2);

        testInstance2.add(2);
        testInstance2.add(3);

        testInstance.removeAll(testInstance2);

        assertEquals(1, testInstance.size());
        assertTrue(testInstance.contains(1));
    }

    @Test
    public void testRetainAll()  {
        final java.util.LinkedList<Integer> testInstance = new java.util.LinkedList<>();
        final java.util.LinkedList<Integer> testInstance2 = new java.util.LinkedList<>();
        testInstance.add(1);
        testInstance.add(2);

        testInstance2.add(2);
        testInstance2.add(3);

        testInstance.retainAll(testInstance2);

        assertEquals(1, testInstance.size());
        assertTrue(testInstance.contains(2));
    }

    @Test
    public void testClear(){
        final java.util.LinkedList<Integer> testInstance = new java.util.LinkedList<>();
        testInstance.add(1);
        testInstance.add(1);

        testInstance.clear();

        assertTrue(testInstance.isEmpty());
        assertEquals(0, testInstance.size());
    }

    @Test
    public void testRemoveByIndex() throws RuntimeException {
        final java.util.LinkedList<String> testInstance = new java.util.LinkedList<>();
        testInstance.add("a");
        testInstance.add("b");
        testInstance.add("c");
        testInstance.add("d");

        try {
            testInstance.remove(5);
        } catch (final NullPointerException e1) {
            throw new RuntimeException(
                    "The remove(index) method did not check the marginal values of index. " +
                            "IndexOutOfBoundsException was expected. " +
                            "So get the NullPointerException.", e1);
        } catch (final IndexOutOfBoundsException e) {}


        assertEquals("Method remove(index) must return deleted element", "b", testInstance.remove(1));
        assertEquals("After remove size is incorrect", 3, testInstance.size());

        assertEquals("Method remove(index) could not delete the last element", "d", testInstance.remove(2));
        assertEquals("After remove size is incorrect", 2, testInstance.size());

        assertEquals("Method remove(index) could not delete the first element", "a", testInstance.remove(0));
        assertEquals("After remove size is incorrect", 1, testInstance.size());

        testInstance.remove(0);
        assertEquals("After remove size is incorrect", 0, testInstance.size());

    }

    @Test
    public void testSetInList() {
        final java.util.LinkedList<Object> testInstance = new java.util.LinkedList<>();
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add("trololo");

        assertEquals("The method must return the replaced element.", "trololo", testInstance.set(2, 1));
        assertEquals(1, testInstance.get(0));
        assertEquals(2, testInstance.get(1));
    }

    @Test
    public void testSetInListOnException() {
        final java.util.LinkedList<Object> testInstance = new java.util.LinkedList<>();
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add("trololo");


        try {
            testInstance.set(3, -25);
            fail("The set () method should throw an exception on the invalid inbound index!");
        } catch (Exception e) {}

        try {
            testInstance.set(-1, 25);
            fail("The set () method should throw an exception on the invalid inbound index!");
        } catch (Exception e) {}
    }

    @Test
    public void testGetInList() {
        final java.util.LinkedList<Object> testInstance = new java.util.LinkedList<>();
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add("trololo");

        assertEquals("The method must return the replaced element.", "trololo", testInstance.get(2));
        assertEquals(1, testInstance.get(0));
        assertEquals(2, testInstance.get(1));
    }

    @Test
    public void testGetInListOnException() {
        final List<Object> testInstance = new java.util.LinkedList<>();
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add("trololo");


        try {
            int temp = (int)testInstance.get(-25);
            fail("The get() method should throw an exception on the invalid inbound index!" +
                    "Expected: IndexOutOfBoundsException, Current: " + temp);
        } catch (Exception e) {}

        try {
            int temp = (int)testInstance.get(3);
            fail("The get() method should throw an exception on the invalid inbound index!" +
                    "Expected: IndexOutOfBoundsException, Current: " + temp);
        } catch (Exception e) {}
    }

    @Test
    public void testIndexOfInList () {
        final java.util.LinkedList<Object> testInstance = new java.util.LinkedList<>();
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add("trololo");
        testInstance.add(null);
        ///testInstance.indexOf(2);
        assertEquals(1, testInstance.indexOf(2));
        assertEquals(2, testInstance.indexOf("trololo"));
    }

    //ITERATOR TESTS


    @Test
    public void testHasNextInEmptyList() {
        final java.util.LinkedList<Integer> testInstance = new java.util.LinkedList<>();
        final ListIterator<Integer> iter = testInstance.listIterator();
        assertFalse(iter.hasNext());
    }
    @Test
    public void testHasNext() {
        final java.util.LinkedList<Integer> testInstance = new java.util.LinkedList<>();
        testInstance.add(3);
        final ListIterator<Integer> iter = testInstance.listIterator();
        assertTrue(iter.hasNext());
        testInstance.add(3);
        testInstance.add(3);
        assertTrue(iter.hasNext());
    }


    @Test
    public void testNextOnEmptyCollection()  {
        final java.util.LinkedList<Integer> testInstance = new java.util.LinkedList<>();
        final java.util.LinkedList<Integer> testInstance2 = new java.util.LinkedList<>();
        testInstance.add(1);
        testInstance.add(2);

        final ListIterator<Integer> iter = testInstance.listIterator();
        iter.next();
        iter.remove();
        iter.next();
        iter.remove();
        try {
            iter.next();
            fail("next do not throw the Exception when no more elements");
        } catch (final java.util.NoSuchElementException e) {}
        final ListIterator<Integer> iter2 = testInstance2.listIterator();
        try {
            iter2.next();
            fail("next do not throw the Exception when no  elements");
        } catch (final java.util.NoSuchElementException e) {}
    }

    @Test
    public void testNextOnOneElementInCollection()  {
        final java.util.LinkedList<String> testInstance = new java.util.LinkedList<>();
        testInstance.add("a");


        final Iterator iter = testInstance.iterator();
        iter.next();

    }
    @Test
    public void testNextAfterPrevious()  {
        final java.util.LinkedList<String> testInstance = new java.util.LinkedList<>();
        testInstance.add("a");
        testInstance.add("a");
        testInstance.add("a");

        final ListIterator iter = testInstance.listIterator(3);
        iter.previous();
        iter.next();
        iter.previous();
        iter.previous();
        iter.next();
        iter.previous();
        iter.previous();
        iter.next();
    }

    @Test
    public void testHasPreviousWhenIteratorAtTheEndOfTheCollection() {
        final java.util.LinkedList<Integer> testInstance = new java.util.LinkedList<>();
        testInstance.add(1);
        testInstance.add(2);

        final ListIterator<Integer> listIterator = testInstance.listIterator();
        listIterator.next();

        assertTrue(listIterator.hasPrevious());
    }

    @Test
    public void testHasPreviousWhenIteratorAtTheBeginningOfTheCollection() {
        final java.util.LinkedList<Integer> testInstance = new java.util.LinkedList<>();
        testInstance.add(1);
        testInstance.add(2);

        final ListIterator<Integer> listIterator = testInstance.listIterator();
        listIterator.next();
        listIterator.previous();
        assertFalse("The hasPrevious() method must return false when the" +
                " iterator is on the first LinkedList element.",listIterator.hasPrevious());
    }

    @Test
    public void testHasPreviousWhenEmptyCollection() {
        final java.util.LinkedList<Integer> testInstance = new java.util.LinkedList<>();

        final ListIterator<Integer> listIterator = testInstance.listIterator();

        assertFalse(listIterator.hasPrevious());
    }

    @Test
    public void testPreviousOnCollectionWithOneElement() {
        final java.util.LinkedList<Integer> testInstance = new java.util.LinkedList<>();
        testInstance.add(1);

        final ListIterator<Integer> listIterator = testInstance.listIterator();
        final Integer next = listIterator.next();
        final Integer previous = listIterator.previous();

        assertEquals(next, previous);
    }

    @Test
    public void testPreviousWhenEmptyCollection() {
        final java.util.LinkedList<Integer> testInstance = new java.util.LinkedList<>();

        final ListIterator<Integer> listIterator = testInstance.listIterator();

        try {
            listIterator.previous();
            fail("list iterator do not throw the Exception when called previous method on empty collection");
        } catch (final java.util.NoSuchElementException e) {}
    }

    @Test
    public void testPreviousAfterNext() {
        final java.util.LinkedList<Integer> testInstance = new java.util.LinkedList<>();
        testInstance.add(0);
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(4);
        testInstance.add(5);

        final ListIterator<Integer> listIterator = testInstance.listIterator();
        while (listIterator.hasNext()) listIterator.next();
        while (listIterator.hasPrevious()) listIterator.previous();
        assertEquals(listIterator.next(), listIterator.previous());
    }

    @Test
    public void testPreviousSeveralTimesOneByOneFromTheEndOfList() {
        final java.util.LinkedList<Integer> testInstance = new java.util.LinkedList<>();
        testInstance.add(0);
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(4);
        testInstance.add(5);

        final ListIterator<Integer> listIterator = testInstance.listIterator(6);
        listIterator.previous();
        listIterator.previous();
        listIterator.previous();
        listIterator.previous();
        listIterator.previous();
        listIterator.previous();
    }

    @Test
    public void testIteratorSet() {
        final java.util.LinkedList<Integer> testInstance = new java.util.LinkedList<>();
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(4);

        final ListIterator<Integer> listIterator = testInstance.listIterator(2);
        listIterator.next();
        listIterator.set(0);
        assertEquals((Integer)0, testInstance.get(2));
        listIterator.previous();
        listIterator.previous();
        listIterator.set(9);
        assertEquals((Integer)9, testInstance.get(1));
    }

    @Test
    public void testIteratorSetWhenNeitherNextNorPreviousHaveBeenCalled() {
        final java.util.LinkedList<Integer> testInstance = new java.util.LinkedList<>();
        testInstance.add(1);

        final ListIterator<Integer> listIterator = testInstance.listIterator();

        try {
            listIterator.set(null);
            fail("set method do not throw IllegalStateException the if neither next nor previous have been called");
        } catch (final IllegalStateException e){}
    }

    @Test
    public void testPreviousIndex() {
        final java.util.LinkedList<Integer> testInstance = new java.util.LinkedList<>();
        testInstance.add(1);

        final ListIterator<Integer> listIterator = testInstance.listIterator();
        listIterator.next();

        assertEquals(0, listIterator.previousIndex());
    }

    @Test
    public void testPreviousIndexWhenItEqualsTo1() {
        final java.util.LinkedList<Integer> testInstance = new java.util.LinkedList<>();
        testInstance.add(1);
        testInstance.add(1);

        final ListIterator<Integer> listIterator = testInstance.listIterator();
        listIterator.next();
        listIterator.next();

        assertEquals(1, listIterator.previousIndex());
    }

    @Test
    public void testPreviousIndexWhenEmptyCollection() {
        final java.util.LinkedList<Integer> testInstance = new java.util.LinkedList<>();

        final ListIterator<Integer> listIterator = testInstance.listIterator();

        assertEquals(-1, listIterator.previousIndex());
    }

    @Test
    public void testNextIndex() {
        final java.util.LinkedList<String> testInstance = new java.util.LinkedList<String>(){
            {
                add("a");
                add("b");
                add("c");
                add("d");
                add("e");
            }
        };
        final ListIterator<String> listIterator = testInstance.listIterator(5);
        assertEquals(5, listIterator.nextIndex());
        assertEquals("e", listIterator.previous());
        assertEquals(4, listIterator.nextIndex());
        assertEquals("e", listIterator.next());
        assertEquals(5, listIterator.nextIndex());
    }

    @Test
    public void testNextIndexInEmptyList() {
        final java.util.LinkedList testInstance = new java.util.LinkedList<>();
        final ListIterator listIterator = testInstance.listIterator();
        assertEquals(0, listIterator.nextIndex());
        assertEquals(testInstance.size(), listIterator.nextIndex());
    }

    @Test
    public void testRemoveTwoTimeInTheRow() {
        final Collection<Integer> testInstance = new java.util.LinkedList<>();
        testInstance.add(1);
        testInstance.add(2);

        final Iterator<Integer> iter = testInstance.iterator();
        iter.next();
        iter.remove();
        assertEquals("Expected collection size is 1, however actual is not", 1, testInstance.size());
        try {
            iter.remove();
            fail("remove do not throw the Exception when called twice");
        } catch (final IllegalStateException e) {}
    }


    @Test
    public void testRemoveAfterPrevious()  {
        final java.util.LinkedList<Integer> testInstance = new java.util.LinkedList<>();
        testInstance.add(1);
        testInstance.add(2);

        final ListIterator<Integer> listIterator = testInstance.listIterator();
        listIterator.next();
        listIterator.next();
        listIterator.previous();
        listIterator.remove();
    }

    @Test
    public void testRemoveBeforeNext() {
        final java.util.LinkedList<Integer> testInstance = new LinkedList<>();
        testInstance.add(2);

        final Iterator<Integer> iter = testInstance.iterator();
        try {
            iter.remove();
            fail("remove do not throw the Exception when called before next");
        } catch (final IllegalStateException e) {}
    }
}