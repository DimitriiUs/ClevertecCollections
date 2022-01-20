package ru.clevertec.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class CustomLinkedList<E> implements List<E> {

    /**
     * The size of the list
     */
    private int size;

    /**
     * First node of the list
     */
    private Node<E> first;

    /**
     * Last node of the list
     */
    private Node<E> last;

    /**
     * Constructs an empty list
     */
    public CustomLinkedList() {
    }

    /**
     * Constructs a list with elements of specified collection
     *
     * @param collection - collection with elements
     * @throws NullPointerException - if specified collection is null
     */
    public CustomLinkedList(Collection<? extends E> collection) {
        this();
        addAll(collection);
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
     * Returns {@code true} if list is empty
     *
     * @return {@code true} if list is empty
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

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
        int index = 0;
        if (o == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.element == null)
                    return index;
                index++;
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (o.equals(x.element))
                    return index;
                index++;
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
        int index = size;
        if (o == null) {
            for (Node<E> x = last; x != null; x = x.previous) {
                index--;
                if (x.element == null)
                    return index;
            }
        } else {
            for (Node<E> x = last; x != null; x = x.previous) {
                index--;
                if (o.equals(x.element))
                    return index;
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
        Object[] result = new Object[size];
        int i = 0;

        for (Node<E> x = first; x != null; x = x.next) {
            result[i++] = x.element;
        }
        return result;
    }

    /**
     * Returns an array with type equal to received array a
     *
     * @param a an array with a type for the returning array
     * @return an array with type equal to received array a
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        a = (T[]) java.lang.reflect.Array.newInstance(
                a.getClass().getComponentType(), size);
        int i = 0;
        Object[] result = a;

        for (Node<E> x = this.first; x != null; x = x.next) {
            result[i++] = x.element;
        }

        return a;
    }

    /**
     * Add the specified element to the end of this list
     *
     * @param e - element
     * @return {@code true}
     */
    @Override
    public boolean add(E e) {
        linkLast(e);

        return true;
    }

    private void linkLast(E e) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, e, null);
        last = newNode;

        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
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
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.element == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (o.equals(x.element)) {
                    unlink(x);
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Unlinks node x
     *
     * @param x - node for unlinking
     * @return unlinked element
     */
    private E unlink(Node<E> x) {
        final E element = x.element;
        final Node<E> next = x.next;
        final Node<E> previous = x.previous;

        if (previous == null) {
            first = next;
        } else {
            previous.next = next;
            x.previous = null;
        }

        if (next == null) {
            last = previous;
        } else {
            next.previous = previous;
            x.next = null;
        }

        x.element = null;
        size--;

        return element;
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
        return addAll(size, c);
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
        checkPositionIndex(index);

        Object[] a = c.toArray();
        int numNew = a.length;
        if (numNew == 0)
            return false;

        Node<E> pred, succ;
        if (index == size) {
            succ = null;
            pred = last;
        } else {
            succ = node(index);
            pred = succ.previous;
        }

        for (Object o : a) {
            @SuppressWarnings("unchecked") E e = (E) o;
            Node<E> newNode = new Node<>(pred, e, null);
            if (pred == null)
                first = newNode;
            else
                pred.next = newNode;
            pred = newNode;
        }

        if (succ == null) {
            last = pred;
        } else {
            pred.next = succ;
            succ.previous = pred;
        }

        size += numNew;
        return true;
    }

    /**
     * Returns the Node at the specified element index
     *
     * @return the Node at the specified element index
     */
    private Node<E> node(int index) {
        Node<E> x;

        if (index < (size >> 1)) {
            x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
        } else {
            x = last;
            for (int i = size - 1; i > index; i--)
                x = x.previous;
        }

        return x;
    }

    private void checkPositionIndex(int index) {
        if (index < 0 || index > size) {
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
        Node<E> next;
        for (Node<E> x = first; x != null; x = next) {
            next = x.next;
            x.element = null;
            x.next = null;
            x.previous = null;
        }

        this.first = this.last = null;
        this.size = 0;
    }

    /**
     * Gets element at specified position
     *
     * @param index - position
     * @return element at specified position
     */
    @Override
    public E get(int index) {
        checkElementIndex(index);

        return node(index).element;
    }

    private void checkElementIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    getMessageForIndexOutOfBoundException(index));
        }
    }

    /**
     * Set element at specified position
     *
     * @param index   - position
     * @param element - element for replacing
     * @return replaced element
     */
    @Override
    public E set(int index, E element) {
        checkElementIndex(index);

        Node<E> x = node(index);
        E oldVal = x.element;
        x.element = element;

        return oldVal;
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
        checkPositionIndex(index);

        if (index == size) {
            linkLast(element);
        } else {
            linkBefore(element, node(index));
        }
    }

    /**
     * Inserts element before node
     *
     * @param element - element for inserting
     * @param node    - inserting before node
     */
    private void linkBefore(E element, Node<E> node) {
        final Node<E> pred = node.previous;
        final Node<E> newNode = new Node<>(pred, element, node);
        node.previous = newNode;

        if (pred == null)
            first = newNode;
        else
            pred.next = newNode;

        size++;
    }

    /**
     * Removes element at specified position
     *
     * @param index - specified position
     * @return removed element
     */
    @Override
    public E remove(int index) {
        checkElementIndex(index);

        return unlink(node(index));
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

    private static class Node<E> {
        private E element;
        private Node<E> next;
        private Node<E> previous;

        public Node(Node<E> previous, E element, Node<E> next) {
            this.element = element;
            this.next = next;
            this.previous = previous;
        }
    }
}
