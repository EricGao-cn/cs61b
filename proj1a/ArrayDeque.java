public class ArrayDeque<T> {
    private int size;
    private int allocatedSize;
    private int nextFirst;
    private int nextLast;
    T[] arr;

    public ArrayDeque() {
        size = 0;
        allocatedSize = 8;
        nextFirst = allocatedSize / 2 - 1;
        nextLast = allocatedSize / 2;
        arr = (T[]) new Object[allocatedSize];

    }
    private void sizeLarger() {
        allocatedSize *= 2;
        T[] newArr = (T[]) new Object[allocatedSize];
        System.arraycopy(arr, nextFirst + 1, newArr, allocatedSize / 2, size);
        arr = newArr;
        nextFirst = allocatedSize / 2 - 1;
        nextLast = allocatedSize / 2 + size;
    }
    private void sizeSmaller() {
        allocatedSize /= 2;
        T[] newArr = (T[]) new Object[allocatedSize];
        System.arraycopy(arr, nextFirst + 1, newArr, allocatedSize / 2, size);
        arr = newArr;
        nextFirst = allocatedSize / 2 - 1;
        nextLast = allocatedSize / 2 + size;
    }

    public int size() {
        return size;
    }
    public T get(int index) {
        return arr[nextFirst + index + 1];
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public void addFirst(T i) {
        if (size >= allocatedSize / 2) {
            sizeLarger();
        }
        arr[nextFirst--] = i;
        size++;
    }
    public void addLast(T i) {
        if (size >= allocatedSize / 2) {
            sizeLarger();
        }
        arr[nextLast++] = i;
        size++;
    }
    public T removeFirst() {
        T tmp = arr[++nextFirst];
        size--;
        if (size < allocatedSize / 4) {
            sizeSmaller();
        }
        return tmp;
    }
    public T removeLast() {
        T tmp = arr[--nextLast];
        size--;
        if (size < allocatedSize / 4) {
            sizeSmaller();
        }
        return tmp;
    }
    public void printDeque() {
        int i = nextFirst + 1;
        while (i < nextLast) {
            System.out.print(arr[i] + " ");
            i++;
        }
        System.out.println();
    }
}
