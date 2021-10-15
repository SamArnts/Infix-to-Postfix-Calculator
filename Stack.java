public class Stack<E> {
    
    LinkedList<E> Stack = new LinkedList<>();
    private int size;

    void push(E e) {
        Stack.addFirst(e);
        size++;
    }
    E pop () {
        if (size == 0) {
            return null;
        }else {
            size--;
            return Stack.pollFirst();
        }
    }
    E peek () {
     return Stack.peekFirst();
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

}
