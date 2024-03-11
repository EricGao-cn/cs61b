public class ArrayDeque<T> {
    private int size = 0;
    private int allocatedSize = 8;
    private T[] arr = (T[]) new Object[allocatedSize];
    private int nextFirst = allocatedSize / 2 - 1;
    private int nextLast = allocatedSize / 2;

    public void sizeLarger() {
        allocatedSize *= 2;
        T[] newArr = (T[]) new Object[allocatedSize];
        System.arraycopy(arr, nextFirst + 1, newArr, allocatedSize / 2, size);
        arr = newArr;
        nextFirst = allocatedSize / 2 - 1;
        nextLast = allocatedSize / 2 + size;
    }
    public void sizeSmaller() {
        allocatedSize /= 2;
        T[] newArr = (T[]) new Object[allocatedSize];
        System.arraycopy(arr, nextFirst + 1, newArr, allocatedSize / 2, size);
        arr = newArr;
        nextFirst = allocatedSize / 2 - 1;
        nextLast = allocatedSize / 2 + size;
    }

    public int size() { return size;}
    public T get(int index) { return arr[nextFirst + index +1]; }
    public boolean isEmpty() { return size == 0; }
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
        T tmp = arr[nextFirst++];
        size--;
        if (size < allocatedSize / 4) {
            sizeSmaller();
        }
        return tmp;
    }
    public T removeLast() {
        T tmp = arr[nextLast--];
        size--;
        if (size < allocatedSize / 4) {
            sizeSmaller();
        }
        return tmp;
    }
}
