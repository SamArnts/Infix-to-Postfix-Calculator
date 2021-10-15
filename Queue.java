public class Queue<E> {
    LinkedList <E> Queue = new LinkedList<>();
    private int size;

    void enque(E e) {
        if (size == 0) {
            Queue.addFirst(e);
            size++;
        }else {
            Queue.addLast(e);
            size++;
        }
    }
    E deque () {
        if (size == 0) {
            return null;
        }else {
            size++;
            return Queue.pollFirst();
        }
    }

    E peek () {
        return Queue.peekFirst();
    }

    Boolean isEmpty () {
        if (size == 0) {
            return true;
        }else{
            return false;
        }
    }

    int getSize () {
        return size;
    }

    E get(int index) {
        return Queue.get(index);
    }
}
