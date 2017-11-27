package by.epam.tr.collection;

public class BinaryTreeImpl<E extends Comparable<E>> implements BinaryTree<E> {
    private Node<E> root;

    class Node<E> {
        Node<E> left;
        Node<E> right;
        E item;

        Node() {
        }

        Node(E item) {
            this.item = item;
        }

        Node(Node<E> left, E item, Node<E> right) {
            this.left = left;
            this.item = item;
            this.right = right;
        }
    }

    @Override
    public boolean add(E element) {
        if (root == null) {
            root = new Node<>(element);
            root.item = element;
            return true;
        } else {
            Node<E> t = root;
            Node<E> parent;
            int resultCompare;
            do {
                parent = t;
                resultCompare = element.compareTo(t.item);
                if (resultCompare < 0) {
                    t = t.left;
                } else if (resultCompare > 0) {
                    t = t.right;
                }
            } while (t != null);
            Node<E> newNode = new Node<>(null, element, null);
            if (resultCompare < 0) {
                parent.left = newNode;
                return true;
            } else {
                parent.right = newNode;
                return true;
            }
        }
    }


    @Override
    public void preOrder(BypassTreatment<E> tr) {
        preOrder(root, tr);
    }

    @Override
    public void inOrder(BypassTreatment<E> tr) {
        inOrder(root, tr);
    }

    @Override
    public void postOrder(BypassTreatment<E> tr) {
        postOrder(root, tr);
    }


    private void preOrder(Node<E> node, BypassTreatment<E> tr) {
        tr.treatment(node.item);
        if (node.left != null) {
            preOrder(node.left, tr);
        }
        if (node.right != null) {
            preOrder(node.right, tr);
        }
    }

    private void inOrder(Node<E> node, BypassTreatment<E> tr) {
        if (node.left != null) {
            inOrder(node.left, tr);
        }
        tr.treatment(node.item);
        if (node.right != null) {
            inOrder(node.right, tr);
        }
    }

    private void postOrder(Node<E> node, BypassTreatment<E> tr) {
        if (node.left != null) {
            postOrder(node.left, tr);
        }
        if (node.right != null) {
            postOrder(node.right, tr);
        }
        tr.treatment(node.item);
    }


}
