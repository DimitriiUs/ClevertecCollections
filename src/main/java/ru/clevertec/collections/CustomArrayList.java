package ru.clevertec.collections;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class CustomArrayList<E> implements List<E> {

    /**
     * Default capacity
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * The size of the list
     */
    private int size;

    /**
     * The array in which all elements of the list are stored
     */
    private Object[] elements;

    /**
     * Constructs an empty list
     */
    public CustomArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Constructs an empty list with specified initial capacity
     *
     * @param initialCapacity - initial capacity
     * @throws IllegalArgumentException - if initial capacity is negative
     */
    public CustomArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            elements = new Object[initialCapacity];
        } else {
            if (initialCapacity < 0) {
                throw new IllegalArgumentException("Illegal Capacity:" +
                        initialCapacity);
            }

            elements = new Object[DEFAULT_CAPACITY];
        }
    }

    /**
     * Constructs a list with elements of specified collection
     *
     * @param collection - collection with elements
     * @throws NullPointerException - if specified collection is null
     */
    public CustomArrayList(Collection<? extends E> collection) {
        Object[] a = collection.toArray();

        if ((size = a.length) != 0) {
            if (collection.getClass() == CustomArrayList.class) {
                elements = a;
            } else {
                elements = Arrays.copyOf(a, size, Object[].class);
            }
        } else {
            elements = new Object[DEFAULT_CAPACITY];
        }
    }

    /**
     * Returns size of the list
     *
     * @return size of the list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns <tt>true</tt> if list is empty
     *
     * @return <tt>true</tt> if list is empty
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns <tt>true</tt> if list contains element
     *
     * @param o element
     * @return <tt>true</tt> if list contains element
     */
    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    /**
     * Returns the index of first entry of element in this list or -1 if
     * list doesn't contain element
     *
     * @param o element
     * @return the index of first entry of element in this list or -1 if
     * list doesn't contain element
     */
    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null)
                    return i;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(elements[i]))
                    return i;
            }
        }
        return -1;
    }

    /**
     * Returns the index of last entry of element in this list or -1 if
     * list doesn't contain element
     *
     * @param o element
     * @return the last of first entry of element in this list or -1 if
     * list doesn't contain element
     */
    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size - 1; i >= 0; i--) {
                if (elements[i] == null)
                    return i;
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (o.equals(elements[i]))
                    return i;
            }
        }
        return -1;
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns Object's array of elements of this list
     *
     * @return Object's array of elements of this list
     */
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    /**
     * Returns an array with type equal to received array a
     *
     * @param a an array with a type for the returning array
     * @return an array with type equal to received array a
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T[] toArray(T[] a) {
        return (T[]) Arrays.copyOf(elements, size, a.getClass());
    }

    /**
     * Add the specified element to the end of this list
     *
     * @param e - element
     * @return {@code true}
     */
    @Override
    public boolean add(E e) {
        if (size == elements.length) {
            elements = grow();
        }

        elements[size] = e;
        size++;

        return true;
    }

    private Object[] grow() {
        return grow(size + 1);
    }

    private Object[] grow(int minCapacity) {
        int oldCapacity = elements.length;
        if (oldCapacity == 0) {
            return elements = new Object[DEFAULT_CAPACITY];
        } else {
            int newCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
            return elements = Arrays.copyOf(elements, newCapacity);
        }
    }

    /**
     * Adds specified element at specified position
     *
     * @param index   - specified position
     * @param element - specified element
     * @throws IndexOutOfBoundsException -
     */
    @Override
    public void add(int index, E element) {
        checkIndexForAdd(index);

        int s;
        Object[] elementData;
        if ((s = this.size) == (elementData = elements).length) {
            elementData = this.grow();
        }

        System.arraycopy(elementData, index, elementData, index + 1, s - index);
        elements[index] = element;
        size = s + 1;
    }

    /**
     * Removes the first entry of the specified element from this list
     *
     * @param o to be removed from this list
     * @return {@code true} if this list contained the specified element
     */
    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (int index = 0; index < size; index++)
                if (elements[index] == null) {
                    fastRemove(index);
                    return true;
                }
        } else {
            for (int index = 0; index < size; index++)
                if (o.equals(elements[index])) {
                    fastRemove(index);
                    return true;
                }
        }
        return false;
    }

    private void fastRemove(int index) {
        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elements, index + 1, elements, index,
                    numMoved);
        elements[--size] = null;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    /**
     * Add all elements in the specified collection to the end of this list
     *
     * @param c - collection
     * @return {@code true} if this list changed as a result of the call
     * @throws NullPointerException if the specified collection is null
     */
    @Override
    public boolean addAll(Collection<? extends E> c) {
        Object[] a = c.toArray();
        int numNew = a.length;
        if (numNew == 0) {
            return false;
        } else {
            Object[] elementData;
            int s;
            if (numNew > (elementData = elements).length - (s = size)) {
                elementData = grow(s + numNew);
            }

            System.arraycopy(a, 0, elementData, s, numNew);
            size = s + numNew;
            return true;
        }
    }

    /**
     * Add all the elements in the specified collection into this
     * list, starting at the specified position
     *
     * @param index for specified position
     * @param c     - collection
     * @return {@code true} if this list changed as a result of the call
     * @throws IndexOutOfBoundsException {@inheritDoc}
     * @throws NullPointerException      if the specified collection is null
     */
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        checkIndexForAdd(index);
        Object[] a = c.toArray();
        int numNew = a.length;
        if (numNew == 0) {
            return false;
        } else {
            Object[] elementData;
            int s;
            if (numNew > (elementData = elements).length - (s = size)) {
                elementData = grow(s + numNew);
            }

            int numMoved = s - index;
            if (numMoved > 0) {
                System.arraycopy(elementData, index, elementData, index + numNew, numMoved);
            }

            System.arraycopy(a, 0, elementData, index, numNew);
            size = s + numNew;
            return true;
        }
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes all the elements from this list
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }

        size = 0;
    }

    /**
     * Returns element at specified position
     *
     * @param index - position in list
     * @return element at specified position
     */
    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        checkIndex(index);

        return (E) elements[index];
    }

    /**
     * Set element at specified position
     *
     * @param index   - position in list
     * @param element - element for replacing
     * @return replaced element
     */
    @SuppressWarnings("unchecked")
    @Override
    public E set(int index, E element) {
        checkIndex(index);

        E oldValue = (E) elements[index];
        elements[index] = element;

        return oldValue;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException(getMessageForIndexOutOfBoundException(index));
        }
    }

    private String getMessageForIndexOutOfBoundException(int index) {
        return "Received index:"
                + index
                + " "
                + "list size:"
                + size;
    }

    private void checkIndexForAdd(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException(getMessageForIndexOutOfBoundException(index));
        }
    }

    @Override
    public E remove(int index) {
        checkIndex(index);

        E oldValue = elementData(index);

        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elements, index+1, elements, index,
                    numMoved);
        elements[--size] = null;

        return oldValue;
    }

    @SuppressWarnings("unchecked")
    private E elementData(int index) {
        return (E) elements[index];
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }
}
