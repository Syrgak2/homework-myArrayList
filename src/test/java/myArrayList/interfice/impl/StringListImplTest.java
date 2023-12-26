package myArrayList.interfice.impl;

import myArrayList.exception.ElementNotFoundException;
import myArrayList.exception.ExitStorageLengthException;
import myArrayList.exception.NullPointException;
import myArrayList.interfice.StringList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StringListImplTest {
    private StringList stringList;

    private String testValue;
    private String testValue1;
    private String testValue2;

    @BeforeEach
    public void setUp() {
        stringList = new StringListImpl();
        testValue1 = stringList.add("Test value");
        testValue2 = stringList.add("Test value 1");
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
                () -> stringList.remove(wrongElement)
        );

    }

    //    NullPointException
    @Test
    public void whenGivenNull() {
//        Given
        String testNull = null;
        StringList testList = null;

//        Add
        Assertions.assertThrows(
                NullPointException.class,
                () -> stringList.add(testNull)
        );

//        Add with index
        Assertions.assertThrows(
                NullPointException.class,
                () -> stringList.add(0, testNull)
        );

//        Set
        Assertions.assertThrows(
                NullPointException.class,
                () -> stringList.set(0, testNull)
        );

//        remove
        Assertions.assertThrows(
                NullPointException.class,
                () -> stringList.remove(testNull)
        );

//        contains
        Assertions.assertThrows(
                NullPointException.class,
                () -> stringList.contains(testNull)
        );

//        indexOf
        Assertions.assertThrows(
                NullPointException.class,
                () -> stringList.indexOf(testNull)
        );

//        lastIndexOf
        Assertions.assertThrows(
                NullPointException.class,
                () -> stringList.lastIndexOf(testNull)
        );

        Assertions.assertThrows(
                NullPointException.class,
                () -> stringList.equals(testList)
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
                () -> stringList.add(stringList.size() + 3, testValue)
        );

//        set
        Assertions.assertThrows(
                ExitStorageLengthException.class,
                () -> stringList.set(index, testValue)
        );

//        remove with index
        Assertions.assertThrows(
                ExitStorageLengthException.class,
                () -> stringList.remove(index)
        );

//        get
        Assertions.assertThrows(
                ExitStorageLengthException.class,
                () -> stringList.get(index)
        );
    }

    @Test
    public void AddElement() {
//        Given
        String excepted = testValue;

//        When
        String actual = stringList.add(testValue);

//        Then
        Assertions.assertEquals(excepted, actual);
    }

    @Test
    public void AddElementOfIndex() {
//        Given
        String excepted = testValue;

//        When
        String actual = stringList.add(1, testValue);

//        Then
        Assertions.assertEquals(excepted, actual);
    }

    @Test
    public void removeElement() {
//        Given
        String excepted = testValue;
        stringList.add(testValue);

//        When
        String actual = stringList.remove(testValue);

//        Then
        Assertions.assertEquals(excepted, actual);
    }

    @Test
    public void removeElementOfIndex() {
//        Given
        String excepted = stringList.add(testValue);

//        When
        String actual = stringList.remove(stringList.indexOf(testValue));

//        Then
        Assertions.assertEquals(excepted, actual);

    }

    @Test
    public void containsElement() {
//        Given
        Boolean excepted = true;
        stringList.add(testValue);

//        When
        Boolean actual = stringList.contains(testValue);

//        Then
        Assertions.assertEquals(excepted, actual);
    }

    @Test
    public void indexOfElement() {
//        Given
        int excepted = stringList.size();
        stringList.add(testValue);

//        When
        int actual = stringList.indexOf(testValue);

//        Then
        Assertions.assertEquals(excepted, actual);
    }

    @Test
    public void searchFromLastIndex() {
//        Given
        int excepted = 0;
        stringList.add(excepted, testValue);

//        When
        int actual = stringList.lastIndexOf(testValue);

//        Then
        Assertions.assertEquals(excepted, actual);
    }

    @Test
    public void getElement() {
//        Given
        int index = 0;
        String excepted = stringList.add(index, testValue);

//        When
        String actual = stringList.get(index);

//        Then
        Assertions.assertEquals(excepted, actual);
    }

    @Test
    public void equalsList() {
//        Given
        boolean excepted = true;
        StringList testList = new StringListImpl();
        testList.add(testValue1);
        testList.add(testValue2);

//        When
        boolean actual = stringList.equals(testList);

//        Then
        Assertions.assertEquals(excepted, actual);
    }

    @Test
    public void sizeOfList() {
//        Given
        int excepted = 2;

//        When
        int actual = stringList.size();

//        Then
        Assertions.assertEquals(excepted, actual);
    }


}
