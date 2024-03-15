/**要写成循环的数组，不然一个数字一个数字往前移就OutOfRange了*/
public class ArrayDeque<T> {
    private int size = 0;
    private int allocatedSize = 8;
    private T[] arr = (T[]) new Object[allocatedSize];
    private int nextFirst = allocatedSize / 2 - 1;
    private int nextLast = allocatedSize / 2;

    /*public ArrayDeque() {
        size = 0;
        allocatedSize = 8;
        nextFirst = allocatedSize / 2 - 1;
        nextLast = allocatedSize / 2;
        arr = (T[]) new Object[allocatedSize];
    }*/
    private void sizeLarger() {
        allocatedSize *= 2;
        T[] newArr = (T[]) new Object[allocatedSize];
        if (nextLast < nextFirst) {
            System.arraycopy(arr, nextFirst + 1,
                    newArr, allocatedSize / 2,
                    allocatedSize / 2 - nextFirst - 1);
            System.arraycopy(arr, 0,
                    newArr, allocatedSize - nextFirst - 1,
                    size + nextFirst + 1 - allocatedSize / 2);
        } else {
            System.arraycopy(arr, nextFirst + 1, newArr, allocatedSize / 2, size);
        }
        arr = newArr;
        nextFirst = allocatedSize / 2 - 1;
        nextLast = allocatedSize / 2 + size;

//        this.printDeque();
    }

    private void sizeSmaller() {
        allocatedSize /= 2;
        T[] newArr = (T[]) new Object[allocatedSize];
        if (nextLast < nextFirst) {
            System.arraycopy(arr, nextFirst + 1,
                    newArr, allocatedSize / 2,
                    allocatedSize * 2 - nextFirst - 1);
            System.arraycopy(arr, 0,
                    newArr, allocatedSize * 5 / 2 - nextFirst - 1,
                    size + nextFirst + 1 - allocatedSize * 2);
        } else {
            System.arraycopy(arr, nextFirst + 1, newArr, allocatedSize / 2, size);
        }
        arr = newArr;
        nextFirst = allocatedSize / 2 - 1;
        nextLast = allocatedSize / 2 + size;
    }

    public int size() {
        if (size < 0) {
            return 0;
        }
        return size;
    }
    public T get(int index) {
        return arr[(nextFirst + index + 1) % allocatedSize];
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public void addFirst(T i) {
        if (size >= allocatedSize / 2) {
            sizeLarger();
        }
        arr[nextFirst] = i;
        nextFirst = (nextFirst - 1 + allocatedSize) % allocatedSize;
        size++;
    }
    public void addLast(T i) {
        if (size >= allocatedSize / 2) {
            sizeLarger();
        }
        arr[nextLast] = i;
        nextLast = (nextLast + 1) % allocatedSize;
        size++;
    }
    public T removeFirst() {
        nextFirst = (nextFirst + 1) % allocatedSize;
        T tmp = arr[nextFirst];
        size--;
        if (allocatedSize > 8 && size < allocatedSize / 4) {
            sizeSmaller();
        }
        return tmp;
    }
    public T removeLast() {
        nextLast = (nextLast - 1 + allocatedSize) % allocatedSize;
        T tmp = arr[nextLast];
        size--;
        if (allocatedSize > 8 && size < allocatedSize / 4) {
            sizeSmaller();
        }
        return tmp;
    }
    public void printDeque() {
        int i = (nextFirst + 1 + allocatedSize) % allocatedSize;
        while (i != nextLast) {
            System.out.print(arr[i] + " ");
            i = (i + 1) % allocatedSize;
        }
        System.out.println();
        System.out.println("nextFirst:" + nextFirst + " nextLast:" + nextLast + " size:" + size);
    }
}
