package featurea.collections;

public class Stack<T> {

    private final int capacity;
    private T[] array;
    private int top = -1;

    public Stack(char[] array) {
        this.capacity = array.length;
        this.array = (T[]) new Character[capacity];
        for (int i = 0; i < array.length; i++) {
            push((T) (Character) array[i]);
        }
    }

    public Stack(T[] array) {
        this.capacity = array.length;
        this.array = array;
    }

    public Stack(int capacity) {
        this.capacity = capacity;
        this.array = (T[]) new Object[capacity];
    }

    public void push(T ch) {
        array[++top] = ch;
    }

    public T pop() {
        return (T) array[top--];
    }

    public T peek() {
        return (T) array[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int getCapacity() {
        return capacity;
    }
}
