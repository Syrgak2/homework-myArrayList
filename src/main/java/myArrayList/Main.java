package myArrayList;

import myArrayList.interfice.StringList;
import myArrayList.interfice.impl.StringListImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        StringList myArray = new StringListImpl(5);
        StringList myArray2 = new StringListImpl(10);

        List<String> myList = new ArrayList<>(10);

        System.out.println(myArray.add("AAA"));
        System.out.println(myArray.add("BBB"));
        System.out.println(myArray.add("CCC"));

        System.out.println(myArray2.add("AAA"));
        System.out.println(myArray2.add("BBB"));
        System.out.println(myArray2.add("CCC"));
        System.out.println("myArray2.indexOf(\"BBB\") = " + myArray2.indexOf("BBB"));

        System.out.println("Then remove " + myArray.size());
        System.out.println(myArray.remove("CCC"));
        System.out.println("After remove " + myArray.size());

        System.out.println("contains " + myArray.contains("CCC"));
        System.out.println("index of " + myArray.indexOf("CCC"));
        System.out.println("Last index " + myArray.lastIndexOf("AAA"));
        System.out.println("get " + myArray.get(1));
        System.out.println("equals " + myArray.equals(myArray2));

        System.out.println("myArray.isEmpty() = " + myArray.isEmpty());
        myArray.clear();
        System.out.println("After clear" + myArray.get(1));

        StringList myArray3 = new StringListImpl(myArray.toArray());
        System.out.println("myArray3 = " + myArray3.size());

    }
}
