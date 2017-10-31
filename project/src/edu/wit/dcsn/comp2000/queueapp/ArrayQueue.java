package edu.wit.dcsn.comp2000.queueapp;

import com.pearson.carrano.EmptyQueueException;
import com.pearson.carrano.QueueInterface;

/**ArrayQueue Implementation, with removed excessive debugging*/
public final class ArrayQueue<T>
        implements QueueInterface<T> {
    private T[] queue;
    private int frontIndex;
    private int backIndex;
    private boolean initialized = false;
    private static final int DEFAULT_CAPACITY = 3;
    private static final int MAX_CAPACITY = 10000;

    public ArrayQueue() {
        this(3);
    }

    public ArrayQueue(int initialCapacity) {
        checkCapacity(initialCapacity);

        Object[] tempQueue = new Object[initialCapacity + 1];
        this.queue = (T[]) tempQueue;
        this.frontIndex = 0;
        this.backIndex = initialCapacity;
        this.initialized = true;
    }

    public void enqueue(T newEntry) {
        checkInitialization();
        ensureCapacity();
        this.backIndex = ((this.backIndex + 1) % this.queue.length);
        this.queue[this.backIndex] = newEntry;
    }

    public T getFront() {
        checkInitialization();
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        return (T) this.queue[this.frontIndex];
    }

    public T dequeue() {
        checkInitialization();
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        T front = this.queue[this.frontIndex];
        this.queue[this.frontIndex] = null;
        this.frontIndex = ((this.frontIndex + 1) % this.queue.length);
        return front;
    }

    public boolean isEmpty() {
        return this.frontIndex == (this.backIndex + 1) % this.queue.length;
    }

    public void clear() {
        checkInitialization();
        if (!isEmpty()) {
            for (int index = this.frontIndex; index != this.backIndex; index = (index + 1) % this.queue.length) {
                this.queue[index] = null;
            }
            this.queue[this.backIndex] = null;
        }
        this.frontIndex = 0;
        this.backIndex = (this.queue.length - 1);
    }

    private void checkInitialization() {
        if (!this.initialized) {
            throw new SecurityException("ArrayQueue object is not initialized properly.");
        }
    }

    private void checkCapacity(int capacity) {
        if (capacity > 10000) {
            throw new IllegalStateException("Attempt to create a queue whose capacity exceeds allowed maximum.");
        }
    }

    private void ensureCapacity() {
        if (this.frontIndex == (this.backIndex + 2) % this.queue.length) {
            Object[] oldQueue = this.queue;
            int oldSize = oldQueue.length;
            int newSize = 2 * oldSize;
            checkCapacity(newSize - 1);

            Object[] tempQueue = new Object[newSize];
            this.queue = (T[]) tempQueue;
            for (int index = 0; index < oldSize - 1; index++) {
                this.queue[index] = (T) oldQueue[this.frontIndex];
                this.frontIndex = ((this.frontIndex + 1) % oldSize);
            }
            this.frontIndex = 0;
            this.backIndex = (oldSize - 2);
        }
    }
}
