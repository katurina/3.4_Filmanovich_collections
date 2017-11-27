package by.epam.tr.collection;


import org.junit.Test;

public class BinaryTreeImplTest {
    @Test
    public void add() {
        BinaryTree<Integer> binaryTree = new BinaryTreeImpl<>();
        binaryTree.add(25);
        binaryTree.add(45);
        binaryTree.add(15);
        binaryTree.add(50);
        binaryTree.add(100);
        binaryTree.add(9);
        binaryTree.add(20);
        binaryTree.add(22);
        binaryTree.add(21);
        binaryTree.add(3);

        binaryTree.inOrder(elem -> System.out.println(elem));
    }

}
