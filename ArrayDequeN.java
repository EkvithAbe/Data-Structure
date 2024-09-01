import java.util.NoSuchElementException;

public class ArrayDequeN<E> {
    private E[] elements;
    private int head;
    private int tail;
    private int size;

    private static final int DEFAULT_CAPACITY = 16;

    @SuppressWarnings("unchecked")
    public ArrayDequeN() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
        head = 0;
        tail = 0;
        size = 0;
    }

    public void addFirst(E e) {
        if (size == elements.length) {
            resize();
        }
        head = (head - 1 + elements.length) % elements.length;
        elements[head] = e;
        size++;
    }

    public void addLast(E e) {
        if (size == elements.length) {
            resize();
        }
        elements[tail] = e;
        tail = (tail + 1) % elements.length;
        size++;
    }

    public E removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        E value = elements[head];
        elements[head] = null;  // Help GC
        head = (head + 1) % elements.length;
        size--;
        return value;
    }

    public E removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        tail = (tail - 1 + elements.length) % elements.length;
        E value = elements[tail];
        elements[tail] = null;  // Help GC
        size--;
        return value;
    }

    public E peekFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return elements[head];
    }

    public E peekLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return elements[(tail - 1 + elements.length) % elements.length];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        int newCapacity = elements.length * 2;
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[(head + i) % elements.length];
        }
        elements = newElements;
        head = 0;
        tail = size;
    }

    public static void main(String[] args) {
        ArrayDequeN<Integer> deque = new ArrayDequeN<>();
        
        deque.addFirst(1);
        deque.addLast(2);
        deque.addFirst(0);
        
        System.out.println(deque.removeLast());   // Output: 2
        System.out.println(deque.removeFirst());  // Output: 0
        System.out.println(deque.peekFirst());    // Output: 1
    }
}
