import java.util.Collection;
import java.util.Iterator;

public class LinkedList<E> implements Iterable<E> {
    private Node<E> head;
    private Node<E> tail;
    private Node<E> curr;


    // Inserts the specified element at the beginning of this list.
    void addFirst(E e) {        //*works*
        if (head == null) {
            head = new Node<>(e, null, null);
        } else {
            head = new Node<>(e, null, head);
        }
    }
    // Appends the specified element to the end of this list.
    void addLast(E e) { //*works*
        if(head == null){
            head = new Node<>(e, null, null);
        } else {
            curr = head;
            while(curr.next() != null){
                curr = curr.next();
            }
            curr.setNext(new Node<>(e, curr, null));
        }
    }
    // Retrieves, but does not remove, the first element of this list, or returns null if this list is empty.
    E peekFirst() { //*works*
        if (head == null) {
            return null;
        }
        return head.element();
    }
    // Retrieves, but does not remove, the last element of this list, or returns null if this list is empty.
    E peekLast() {  //*works*
        if (head == null) {
            return null;
        }
        tail = head;
        while(tail.next() != null){
            tail = tail.next();
        }
        return tail.element();
    }
    // Retrieves and removes the first element of this list, or returns null if this list is empty.
    E pollFirst() { //*works*
        if (head == null) {
            return null;
        }
        E temp = head.element();
        head = head.next();
        return temp;
    }
    // Retrieves and removes the last element of this list, or returns null if this list is empty.
    E pollLast() {  //*works*
        if (head == null) {
            return null;
        }
        curr = head;
        while(curr.next() != null){
            curr = curr.next();
        }
        E temp = curr.element();
        tail = tail.prev();
        tail.setNext(null);
        return temp;
    }
    
    public boolean add(E e) {   //*works*
        if(head == null){
            head = new Node<>(e, null, null);
        } else {
            tail = head;
            while(tail.next() != null){
                tail = tail.next();
            }
            tail.setNext(new Node<>(e, tail, null));
        }
        return true;
    }
   
    public void add(int index, E element) { //*works*

        try {
            if (index == 0) {
                addFirst(element);
            } else if (index == size()) {
                addLast(element);
            } else {
                curr = head;
                for (int i=1; i<index; i++) {
                    curr = curr.next();
                }
                Node<E> newNode = new Node<>(element, curr, curr.next());
                curr.setNext(newNode);
                newNode.next().setPrev(newNode);
            }         
        }catch (Exception e) {
            System.out.println("Make sure you enter a valid index");

        }
        
    }
    
    public boolean addAll(Collection<? extends E> c) { //*works*
        for (E e : c) {
            add(e);
        }
        return true;
    }
    
    public boolean addAll(int index, Collection<? extends E> c) { //*works*
        int i = index;
        for (E e : c) {
            add(i,e);
            i++;
        }
        return true;
    }
    
    public void clear() {   //*works*
        head = null;
        tail = null;
    }
    
    public boolean contains(Object o) { //*works*
        if(head == null) {
            return false;
        }else if (head.next() == null) {
            if(head.element().equals(o)) {
                return true;
            } else{
                return false;
            } 
        }else {
            curr = head;
            while(curr.next() != null) {
                if (curr.element().equals(o)) {
                    return true;
                }
                curr = curr.next();
            }
            if (curr.element().equals(o)) {
                return true;
            }
        }
        
        return false;
    }
    
    public boolean containsAll(Collection<?> c) {  //*works*
        for (Object e : c) {
            if (!contains(e)) {
                return false;
            }
        }
        return true;
    }
    
    public E get(int index) {   //*works*
        if (head == null) {
            return null;
        }
        curr = head;
        for (int i=0; i<index; i++) {
            curr = curr.next();
        }
        return curr.element();
    }
    
    public int indexOf(Object o) {  //*works*
        if (head == null) {
            return -1;
        }
        curr = head;
        int indexCount = 0;
        while (curr != null) {
            if (curr.element() == o) {
                return indexCount;
            } else {
            indexCount++;
            curr = curr.next();
            }
        }
        return -1;
    }
    
    public boolean isEmpty() {  //*works*
        if (head == null && tail == null) {
            return true;
        } else {
            return false;
        }
    }
    
    public Iterator<E> iterator() {
        Iterator<E> i = new Iterator<E>() {

            
            public boolean hasNext() {
                if (curr.next() != null) {
                    return true;
                } else {
                    return false;
                }
            }

            
            public E next() {
                return curr.next().element();
            }
            
        };
        return i;
    }
    
    public E remove(int index) {    //*works*
        if (head == null) {
            return null;
        }
        curr = head;
        for(int i=0; i<index; i++) {
            curr = curr.next();
        }
        if (curr.prev() == null) {
            return pollFirst();
        } else if (curr.next() == null) {
            return pollLast();

        }else {
            E temp = curr.element();
            curr.prev().setNext(curr.next());
            curr.next().setPrev(curr.prev());
            return temp;
        }
       
    }
    
    public boolean remove(Object o) {   //*works*
        if (head == null) {
            return false;
        }
        curr = head;
        while (curr != null) {
            if (curr.element().equals(o)) {
                if (curr.prev() != null) {
                    curr.prev().setNext(curr.next());
                } else {
                    head = head.next();
                }
                curr.next().setPrev(curr.prev());
                return true;
            }
            curr = curr.next();
        }
        return false;
    }
    
    
    
    public boolean removeAll(Collection<?> c) { //*works*
       
        for (Object e : c) {
                remove(e);
        }
        return true;
        
    }
    
    public E set(int index, E element) { //*works*
        if (head == null) {
            return null;
        }
        curr = head;
        for(int i=0; i<index; i++) {
            curr = curr.next();
        }
        E temp = curr.element();
        curr.setElement(element);
        return temp;
    }
    
    public int size() { //*works*
        if (head == null) {
            return 0;
        }
        int size = 1;
        curr = head;
        while(curr.next() != null) {
            size++;
            curr = curr.next();
        }
        return size;
    }
    
    public LinkedList<E> subList(int fromIndex, int toIndex) {  //*works*
        if (fromIndex < 0 || fromIndex > toIndex) {
            System.out.println("Impossible parameters");
            return null;
        }
        LinkedList<E> newList = new LinkedList<>();
        curr = head;
        for (int i=0; i<fromIndex; i++) {
            curr = curr.next();
        }
        for (int i=fromIndex; i<=toIndex; i++) {
            newList.add(curr.element());
            curr = curr.next();
        }
        return newList;
    }
    
    public Object[] toArray() { //*works*
        if (head == null) {
            return null;
        }
        int count = 0;
        curr = head; 
        while (curr != null) {
            count++;
            curr = curr.next();
        }
        Object[] array = new Object[count];
        curr = head;
        for (int i=0; i<count; i++) {
            array[i] = curr.element();
            curr = curr.next();
        }
        for (int i=0; i<array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
        return array;
    }
    
}
