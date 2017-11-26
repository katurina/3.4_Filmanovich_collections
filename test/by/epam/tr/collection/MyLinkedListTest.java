package by.epam.tr.collection;


import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class MyLinkedListTest {

    @Test
    public void add() {
        List<Integer> actual = new MyLinkedList<>();
        actual.add(25);
        actual.add(45);
        actual.add(75);
        List<Integer> expected = new LinkedList<>();
        expected.add(25);
        expected.add(45);
        expected.add(75);
        Assert.assertEquals(expected,actual);
    }


}
