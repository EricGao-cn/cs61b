public class LinkedListDeque<T> {
    private static class IntNode<T> {
        public T item;
        public IntNode<T> prev;
        public IntNode<T> next;

        public IntNode(T i, IntNode<T> p, IntNode<T> n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    private int size;
    private IntNode<T> sensinel;

    public LinkedListDeque() {
        size = 0;
        sensinel = new IntNode<T>(null, null, null);
        sensinel.next = sensinel;
        sensinel.prev = sensinel;
    }

//    public LinkedListDeque(T i) {
//        size = 1;
//        sensinel = new IntNode<T>(null, null, null);
//        sensinel.next = new IntNode<T>(i, sensinel, sensinel);
//        sensinel.prev = sensinel.next;
//    }

    private LinkedListDeque(LinkedListDeque<T> other) {
        size = other.size;
        sensinel = new IntNode<T>(null, null, null);
        IntNode<T> p = sensinel.next;
        IntNode<T> q = other.sensinel.next;
        p = new IntNode<T>(q.item, sensinel, null);
        while (q.next != other.sensinel) {
            p.next = new IntNode<T>(q.next.item, p, null);
            p = p.next;
            q = q.next;
        }
        p.next = sensinel;
        sensinel.prev = p;
    }


    public int size() {
        return size;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public void printDeque() {
        IntNode<T> p = sensinel.next;
        while (p != sensinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
    }
    public void addFirst(T i) {
        // sensinel.next.prev 不能直接用，可能会是空指针 改改改
        IntNode<T> first = new IntNode<T>(i, sensinel, sensinel.next);
        sensinel.next.prev = first;
        sensinel.next = first;
        size++;
    }
    public T removeFirst() {
        T tmp = sensinel.next.item;
        sensinel.next = sensinel.next.next;
        sensinel.next.next.prev = sensinel;
        size--;
        return tmp;
    }
    public void addLast(T i) {
        IntNode<T> last = new IntNode<T>(i, sensinel.prev, sensinel);
        sensinel.next.prev.next = last;
        sensinel.next.prev = last;
        size++;
    }
    public T removeLast() {
        T tmp = sensinel.prev.item;
        sensinel.prev.prev.next = sensinel;
        sensinel.prev = sensinel.prev.prev;
        size--;
        return tmp;
    }
    public T get(int index) {
        int i = 0;
        IntNode<T> p = sensinel.next;
        while (i < index && p != sensinel) {
            p = p.next;
            i++;
        }
        if (i != index) {
            System.out.println("IndexOutOfRange");
        }
        return p.item;
    }

    public T getRecursive(int index) {
        IntNode<T> p = sensinel.next;
        if (index == 0) {
            return p.item;
        }
        LinkedListDeque<T> copy = new LinkedListDeque<T>(this);
        copy.removeFirst();
        return copy.getRecursive(index - 1);
    }
}
