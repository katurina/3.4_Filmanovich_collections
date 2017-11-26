package by.epam.tr.collection;


import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class MyLinkedListTest {

    @Test
    public void add() {
        List<Integer> actual = new MyLinkedList<>();
        actual.add(25);
        actual.add(45);
        actual.add(75);
        Assert.assertEquals((Object) 25, actual.get(0));
        Assert.assertEquals((Object) 45, actual.get(1));
        Assert.assertEquals((Object) 75, actual.get(2));
    }


}
