package by.epam.tr.collection;

public interface BinaryTree<E extends Comparable<E>> {
    boolean add(E element);

    void preOrder(BypassTreatment<E> tr);

    void inOrder(BypassTreatment<E> tr);

    void postOrder(BypassTreatment<E> tr);
}
