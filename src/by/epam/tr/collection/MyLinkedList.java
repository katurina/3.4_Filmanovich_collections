package by.epam.tr.collection;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyLinkedList<E> implements List<E> {

    private static final String INDEX = "Index: ";
    private static final String SIZE = ", Size: ";
    private static final String SIZE_OF_ARRAY = "Size of array: ";
    private static final String SIZE_OF_LIST = ", size of list: ";

    private Node<E> first;
    private Node<E> last;

    private int size;


    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> previous;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.previous = prev;
        }

    }

    public MyLinkedList() {
        first = null;
        last = null;
    }

    public MyLinkedList(Collection<? extends E> c) {
        this();
        addAll(c);
    }


    public E poll() {
        return pollFirst();
    }

    public E element() {
        return getFirst();
    }

    public E peek() {
        return (first == null) ? null : first.item;
    }

    public void push(E e) {
        addFirst(e);

    }

    public E pop() {
        return removeFirst();
    }

    public void addFirst(E e) {
        Node<E> f = first;
        Node<E> newNode = new Node<>(null, e, f);
        first = newNode;
        if (f == null) {
            last = newNode;
        } else {
            f.previous = newNode;
        }
        size++;
    }

    public void addLast(E e) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    public boolean offerFirst(E e) {
        addFirst(e);
        return true;
    }

    public boolean offerLast(E e) {
        addLast(e);
        return true;
    }

    public E removeFirst() {
        if (first == null) {
            return null;
        }
        E element = first.item;
        final Node<E> next = first.next;
        first.item = null;
        first = next;
        if (next == null) {
            last = null;
        } else {
            next.previous = null;
        }
        size--;
        return element;
    }

    public E removeLast() {
        if (last == null) {
            return null;
        }
        E element = last.item;
        Node<E> prev = last.previous;
        last.item = null;
        last = prev;
        if (prev == null) {
            first = null;
        } else {
            prev.next = null;
        }
        size--;
        return element;
    }

    public E pollFirst() {
        if (first == null) {
            return null;
        }
        Node<E> f = first;
        remove(0);
        return f.item;
    }

    public E pollLast() {
        if (last == null) {
            return null;
        }
        E element = last.item;
        Node<E> prev = last.previous;
        last.item = null;
        last.previous = null;
        last = prev;
        if (prev == null) {
            first = null;
        } else {
            prev.next = null;
        }
        size--;
        return element;

    }

    public E getFirst() {
        if (first == null) {
            return null;
        }
        return first.item;
    }

    public E getLast() {
        if (last == null) {
            return null;
        }
        return last.item;
    }

    public E peekFirst() {
        if (first == null) {
            return null;
        }
        return first.item;
    }


    public E peekLast() {
        if (last == null) {
            return null;
        }
        return last.item;
    }

    public boolean removeFirstOccurrence(Object o) {
        return remove(o);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) > -1;
    }

    @Override
    public MyLinkedListIterator iterator() {
        return new MyLinkedListIterator();
    }


    private class MyLinkedListIterator implements Iterator<E> {
        int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor != size();
        }

        @Override
        public E next() {
            int i = cursor;
            E next = get(i);
            cursor = i + 1;
            return next;
        }
    }


    @Override
    public Object[] toArray() {
        Node<E> helpFirst = first;
        Object[] array = new Object[size];
        int i = 0;
        while (helpFirst != null) {
            array[i] = helpFirst.item;
            i++;
            helpFirst = helpFirst.next;
        }
        return array;
    }

    @Override
    public boolean add(E e) {
        Node<E> rememberLast = last;
        Node<E> newNode = new Node<E>(rememberLast, e, null);
        last = newNode;
        if (size == 0) {
            first = newNode;
        } else {
            rememberLast.next = newNode;
        }
        size++;
        return true;
    }

    public boolean offer(E e) {
        return add(e);
    }

    public E remove() {
        return removeFirst();
    }


    @Override
    public boolean remove(Object o) {
        Node<E> help = first;
        if (o == null) {
            while (help.next != null) {
                if (help.item == null) {
                    return removeNullNode(help);
                }
                help = help.next;
            }

        } else {
            while (help.next != null) {
                if (help.item.equals(o)) {
                    return removeNode(help);
                }
                help = help.next;
            }
        }
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return addAll(size, c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (index > size) {
            throw new IndexOutOfBoundsException(INDEX + index + SIZE + size);
        }
        if (c.size() == 0) {
            return false;
        }
        Node<E> previousNode, actualNode;
        if (index == size) {
            actualNode = null;
            previousNode = last;
        } else {
            if (index < size / 2) {
                Node<E> x = first;
                for (int i = 0; i < index; i++) {
                    x = x.next;
                }
                actualNode = x;
            } else {
                Node<E> x = last;
                for (int i = size - 1; i > index; i--) {
                    x = x.previous;
                }
                actualNode = x;
            }
            previousNode = actualNode.previous;
        }
        for (Object o : c) {
            E e = (E) o;
            Node<E> newNode = new Node<>(previousNode, e, null);
            if (previousNode == null) {
                first = newNode;
            } else {
                previousNode.next = newNode;
            }
            previousNode = newNode;
        }

        if (actualNode == null) {
            last = previousNode;
        } else {
            previousNode.next = actualNode;
            actualNode.previous = previousNode;
        }

        size += c.size();

        return false;
    }

    @Override
    public void clear() {
        Node<E> helpFirst = first;
        while (helpFirst != null) {
            Node<E> next = helpFirst.next;
            helpFirst.item = null;
            helpFirst.next = null;
            helpFirst.previous = null;
            helpFirst = next;
        }
        first = last = null;
        size = 0;
    }

    @Override
    public E get(int index) {
        if (index >= 0 && index < size) {
            if (index < (size / 2)) {
                Node<E> x = first;
                for (int i = 0; i < index; i++) {
                    x = x.next;
                }
                return x.item;
            } else {
                Node<E> x = last;
                for (int i = size - 1; i > index; i--) {
                    x = x.previous;
                }
                return x.item;
            }
        } else {
            String message = INDEX + index + SIZE + size;
            throw new IndexOutOfBoundsException(message);
        }
    }

    @Override
    public E set(int index, Object element) {
        if (index > (size - 1)) {
            throw new IndexOutOfBoundsException(INDEX + index + SIZE + size);
        }
        Node<E> x = getNode(index);
        E oldItem = x.item;
        x.item = (E) element;
        return oldItem;
    }


    @Override
    public void add(int index, E element) {
        if (index > size) {
            throw new IndexOutOfBoundsException(INDEX + index + SIZE + size);
        }
        if (index == size) {
            add(element);
        } else {
            Node<E> x;
            x = getNode(index);
            Node<E> previousHelp = x.previous;
            Node<E> newNode = new Node<>(previousHelp, element, x);
            x.previous = newNode;
            if (previousHelp == null) {
                first = newNode;
            } else {
                previousHelp.next = newNode;
            }
            size++;
        }
    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;
        Node<E> firstHelp = first;
        if (o == null) {
            while (firstHelp != null) {
                firstHelp = firstHelp.next;
                if (firstHelp.item == null) {
                    return index;
                }
                index++;
            }
        } else {
            while (firstHelp != null) {
                firstHelp = firstHelp.next;
                if (o.equals(firstHelp.item)) {
                    return index;
                }
                index++;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = size;
        Node<E> x = last;
        if (o == null) {
            while (x != null) {
                index--;
                if (x.item == null) {
                    return index;
                }
                x = x.previous;
            }
        } else {
            while (x != null) {
                index--;
                if (o.equals(x.item)) {
                    return index;
                }
                x = x.previous;
            }
        }
        return -1;
    }

    @Override
    public ListIterator listIterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ListIterator listIterator(int index) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        MyLinkedList<E> subList = new MyLinkedList<>();
        for (int i = fromIndex; i < toIndex; i++) {
            subList.add(get(i));
        }
        return subList;
    }

    @Override
    public boolean retainAll(Collection c) {
        if (c == null) {
            throw new NullPointerException("Collection can't be null!");
        }
        MyLinkedList<E> help = new MyLinkedList<>();
        help.addAll(this);
        help.removeAll(c);
        return removeAll(help);

    }


    @Override
    public boolean removeAll(Collection c) {
        if (c == null) {
            throw new NullPointerException("Collection can't be null!");
        }
        boolean modified = false;
        for (Object o : c) {
            while (contains(o)) {
                remove(o);
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean containsAll(Collection c) {
        for (Object e : c) {
            if (!contains(e)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            throw new IndexOutOfBoundsException(SIZE_OF_ARRAY + a.length + SIZE_OF_LIST + size);
        }
        int i = 0;
        for (Node<E> x = first; x != null; x = x.next) {
            ((Object[]) a)[i++] = x.item;
        }

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    private boolean removeNullNode(Node<E> help) {
        Node<E> prevNode = help.previous;
        Node<E> nextNode = help.next;

        if (prevNode == null) {
            first = nextNode;
        } else {
            prevNode.next = nextNode;
            help.previous = null;
        }
        nextNode.previous = prevNode;
        help.next = null;
        help.item = null;
        size--;
        return true;
    }

    private boolean removeNode(Node<E> help) {
        Node<E> prevNode = help.previous;
        Node<E> nextNode = help.next;
        if (prevNode == null) {
            first = nextNode;
        } else {
            prevNode.next = nextNode;
            help.previous = null;
        }
        if (nextNode == null) {
            last = prevNode;
        } else {
            nextNode.previous = prevNode;
            help.next = null;
        }
        help.item = null;
        size--;
        return true;
    }

    private Node<E> getNode(int index) {
        Node<E> x;
        if (index < (size / 2)) {
            x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
        } else {
            x = last;
            for (int i = size - 1; i > index; i--) {
                x = x.previous;
            }
        }
        return x;
    }

}
