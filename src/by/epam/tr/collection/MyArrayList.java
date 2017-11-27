package by.epam.tr.collection;


import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyArrayList<E> implements List<E> {

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int size;
    private int capacity;

    public MyArrayList(int initialCapacity) {
        if (initialCapacity >= 0) {
            this.elementData = new Object[initialCapacity];
        } else {
            throw new IllegalArgumentException("Illegal initialCapacity" + initialCapacity);
        }
    }

    public MyArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(Collection<? extends E> c) {
        this.elementData = c.toArray();
        this.size = elementData.length;
        this.capacity = size + size / 2;
    }

    private MyArrayList(Object[] array) {
        this.elementData = array;
        this.size = elementData.length;
        this.capacity = size;
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
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(o)) {
                return true;
            }
        }
        return false;
    }

    class MyArrayListIterator<E> implements Iterator<E> {
        int cursor;

        @Override
        public boolean hasNext() {
            return cursor != size;
        }


        @Override
        public E next() {
            int i = cursor;
            if (i >= size) {
                throw new NoSuchElementException();
            }
            Object[] elementData = MyArrayList.this.elementData;
            cursor = i + 1;
            return (E) elementData[i];
        }

    }


    @Override
    public Iterator<E> iterator() {
        return new MyArrayListIterator<E>();
    }


    @Override
    public Object[] toArray() {
        return Arrays.copyOf(this.elementData, size);
    }

    @Override
    public boolean add(E e) {
        checkSizeCapacity();
        this.elementData[size++] = e;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (int index = 0; index < size; index++) {
                if (elementData[index] == null) {
                    int numMoved = size - index - 1;
                    if (numMoved > 0) {
                        System.arraycopy(elementData, index + 1, elementData, index, numMoved);
                    }
                    elementData[--size] = null;
                    return true;
                }
            }
        } else {
            for (int index = 0; index < size; index++) {
                if (o.equals(elementData[index])) {
                    int numMoved = size - index - 1;
                    if (numMoved > 0) {
                        System.arraycopy(elementData, index + 1, elementData, index, numMoved);
                    }
                    elementData[--size] = null;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        if (c.size() > 0) {
            Object[] a = c.toArray();
            while ((c.size() + this.size) > capacity) {
                checkSizeCapacity();
            }
            System.arraycopy(a, 0, elementData, size, c.size());
            size += c.size();
            return true;
        }
        return false;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        checkIndex(index);
        if (c.size() > 0) {
            Object[] a = c.toArray();
            while ((c.size() + this.size) > capacity) {
                checkSizeCapacity();
            }
            if (size - index > 0) {
                System.arraycopy(elementData, index, elementData, index + c.size(), size - index);
            }
            System.arraycopy(a, 0, elementData, index, c.size());
            size += c.size();
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        if (size > 0) {
            elementData = new Object[DEFAULT_CAPACITY];
        }
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return (E) elementData[index];
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);
        E oldElement = (E) elementData[index];
        elementData[index] = element;
        return oldElement;
    }

    @Override
    public void add(int index, E element) {
        checkSizeCapacity();
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = element;
        size++;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        E oldValue = (E) elementData[index];

        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        }
        elementData[--size] = null;
        return oldValue;
    }


    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    return i;
                }
            }

        } else {
            for (int i = 0; i < size; i++) {
                if (elementData[i].equals(o)) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < elementData.length; i++) {
                if (elementData[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (elementData[i].equals(o)) {
                    return i;
                }
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
    public List subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex < 0 || fromIndex > size - 1 || toIndex > size || toIndex < fromIndex) {
            throw new ArrayIndexOutOfBoundsException("From index: " + fromIndex + ", toIndex: " + toIndex + ", size: " + size);
        }
        Object[] array = new Object[toIndex - fromIndex];
        System.arraycopy(elementData, fromIndex, array, 0, toIndex - fromIndex + 1);
        return new MyArrayList(array);
    }

    @Override
    public boolean retainAll(Collection c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean removeAll(Collection c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean containsAll(Collection c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object[] toArray(Object[] a) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void checkSizeCapacity() {
        if (size == capacity) {
            capacity = size + size / 2;
            Object[] timedList = new Object[capacity];
            for (int i = 0; i < size; i++) {
                timedList[i] = elementData[i];
            }
            elementData = timedList;
        }
    }

    private void checkIndex(int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException("Index: " + index + ", size: " + size);
        }
    }
}
