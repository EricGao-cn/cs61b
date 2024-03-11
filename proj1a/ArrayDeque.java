public class ArrayDeque {
    private int size = 0;
    private int allocatedSize = 8;
    private int[] arr = new int[allocatedSize];
    private int nextFirst = allocatedSize / 2 - 1;
    private int nextLast = allocatedSize / 2;

    public void sizeLarger() {
        int[] newArr = new int[allocatedSize*2];
        allocatedSize *= 2;
        System.arraycopy(arr, nextFirst + 1, newArr, allocatedSize / 2, size);
        arr = newArr;
        nextFirst = allocatedSize / 2 - 1;
        nextLast = allocatedSize / 2 + size;
    }
    public void sizeSmaller() {
        int[] newArr = new int[allocatedSize / 2];
        allocatedSize /= 2;
        System.arraycopy(arr, nextFirst + 1, newArr, allocatedSize / 2, size);
        arr = newArr;
        nextFirst = allocatedSize / 2 - 1;
        nextLast = allocatedSize / 2 + size;
    }

    public int size() { return size;}
    public int get(int index) { return arr[nextFirst + index +1]; }
    public void addFirst(int i) {
        if (size >= allocatedSize / 2) {
            sizeLarger();
        }
        arr[nextFirst--] = i;
        size++;
    }
    public void addLast(int i) {
        if (size >= allocatedSize / 2) {
            sizeLarger();
        }
        arr[nextLast++] = i;
        size++;
    }
    public void removeFirst() {
        nextFirst++;
        size--;
        if (size < allocatedSize / 4) {
            sizeSmaller();
        }
    }
    public void removeLast() {
        nextLast--;
        size--;
        if (size < allocatedSize / 4) {
            sizeSmaller();
        }
    }

}
