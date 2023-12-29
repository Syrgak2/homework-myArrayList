package myArrayList.interfice.impl;

import myArrayList.exception.ElementNotFoundException;
import myArrayList.exception.ExitStorageLengthException;
import myArrayList.exception.NullPointException;
import myArrayList.interfice.SimpleList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SimpleListImplTest {
    private SimpleList<String> simpleList;

    private String testValue;
    private String testValue1;
    private String testValue2;

    @BeforeEach
    public void setUp() {
        simpleList = new SimpleListImpl<>();
        testValue1 = simpleList.add("Test value");
        testValue2 = simpleList.add("Test value 1");
        testValue = "Car";
    }

//    Testing all exceptions

    //  ElementNotFoundException
    @Test
    public void WhenElementNotFound() {
//        Given
        final String wrongElement = "Wrong";

//        Then
//        Remove element with item
        Assertions.assertThrows(
                ElementNotFoundException.class,
                () -> simpleList.remove(wrongElement)
        );

    }

    //    NullPointException
    @Test
    public void whenGivenNull() {
//        Given
        String testNull = null;
        SimpleList<String> testList = null;

//        Add
        Assertions.assertThrows(
                NullPointException.class,
                () -> simpleList.add(testNull)
        );

//        Add with index
        Assertions.assertThrows(
                NullPointException.class,
                () -> simpleList.add(0, testNull)
        );

//        Set
        Assertions.assertThrows(
                NullPointException.class,
                () -> simpleList.set(0, testNull)
        );

//        remove
        Assertions.assertThrows(
                NullPointException.class,
                () -> simpleList.remove(testNull)
        );

//        contains
        Assertions.assertThrows(
                NullPointException.class,
                () -> simpleList.contains(testNull)
        );

//        indexOf
        Assertions.assertThrows(
                NullPointException.class,
                () -> simpleList.indexOf(testNull)
        );

//        lastIndexOf
        Assertions.assertThrows(
                NullPointException.class,
                () -> simpleList.lastIndexOf(testNull)
        );

        Assertions.assertThrows(
                NullPointException.class,
                () -> simpleList.equals(testList)
        );
    }

    //    ExitStorageLengthException
    @Test
    public void whenGivenIllegalIndex() {
//        Given
        int index = -1;

//        Add with index
        Assertions.assertThrows(
                ExitStorageLengthException.class,
                () -> simpleList.add(simpleList.size() + 3, testValue)
        );

//        set
        Assertions.assertThrows(
                ExitStorageLengthException.class,
                () -> simpleList.set(index, testValue)
        );

//        remove with index
        Assertions.assertThrows(
                ExitStorageLengthException.class,
                () -> simpleList.remove(index)
        );

//        get
        Assertions.assertThrows(
                ExitStorageLengthException.class,
                () -> simpleList.get(index)
        );
    }

    @Test
    public void AddElement() {
//        Given
        String excepted = testValue;

//        When
        String actual = simpleList.add(testValue);

//        Then
        Assertions.assertEquals(excepted, actual);
    }

    @Test
    public void AddElementOfIndex() {
//        Given
        String excepted = testValue;

//        When
        String actual = simpleList.add(1, testValue);

//        Then
        Assertions.assertEquals(excepted, actual);
    }

    @Test
    public void removeElement() {
//        Given
        String excepted = testValue;
        simpleList.add(testValue);

//        When
        String actual = simpleList.remove(testValue);

//        Then
        Assertions.assertEquals(excepted, actual);
    }

    @Test
    public void removeElementOfIndex() {
//        Given
        String excepted = simpleList.add(testValue);

//        When
        String actual = simpleList.remove(simpleList.indexOf(testValue));

//        Then
        Assertions.assertEquals(excepted, actual);

    }

    @Test
    public void containsElement() {
//        Given
        boolean excepted = true;
        simpleList.add(testValue);

//        When
        boolean actual = simpleList.contains(testValue);

//        Then
        Assertions.assertEquals(excepted, actual);
    }

    @Test
    public void containsIntegerElement() {
//        Given
        boolean excepted = true;
        SimpleList<Integer> integerList = new SimpleListImpl<>();
        integerList.add(12);
        integerList.add(22);
        integerList.add(33);

//        When
        boolean actual = integerList.contains(12);

//        Then
        Assertions.assertEquals(excepted, actual);
    }

    @Test
    public void indexOfElement() {
//        Given
        int excepted = simpleList.size();
        simpleList.add(testValue);

//        When
        int actual = simpleList.indexOf(testValue);

//        Then
        Assertions.assertEquals(excepted, actual);
    }

    @Test
    public void searchFromLastIndex() {
//        Given
        int excepted = 0;
        simpleList.add(excepted, testValue);

//        When
        int actual = simpleList.lastIndexOf(testValue);

//        Then
        Assertions.assertEquals(excepted, actual);
    }

    @Test
    public void getElement() {
//        Given
        int index = 0;
        String excepted = simpleList.add(index, testValue);

//        When
        String actual = simpleList.get(index);

//        Then
        Assertions.assertEquals(excepted, actual);
    }

    @Test
    public void equalsList() {
//        Given
        boolean excepted = true;
        SimpleList<String> testList = new SimpleListImpl<>();
        testList.add(testValue1);
        testList.add(testValue2);

//        When
        boolean actual = simpleList.equals(testList);

//        Then
        Assertions.assertEquals(excepted, actual);
    }

    @Test
    public void sizeOfList() {
//        Given
        int excepted = 2;

//        When
        int actual = simpleList.size();

//        Then
        Assertions.assertEquals(excepted, actual);
    }


}
