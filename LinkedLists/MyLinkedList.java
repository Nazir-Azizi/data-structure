import java.util.Collection;
import java.util.Iterator;

class MyLinkedList<T extends Object>{
    /*
     * Node is an inner class inside MyLinkedList class
     * It is declared as private so that it wouldn't be accessed from
     * anywhere else but inside MyLinkedList class
     */
    private class Node{
        T data;
        Node next;
        Node(T data){
            this.data = data;
            this.next = null;
        }
    }
    // size keeps track of the number of nodes presesnt inside the MyLinkedList
    private int size = 0;
    // head is the begining of MyLinkedList
    private Node head = null;
    private Node tail = null;

    public int getSize(){
        return size;
    }
    /*
     * returns if the MyLinkedList is empty
     */
    public boolean isEmpty(){
        return head == null;
    }
    /*
     * addFirst adds the new Node at the beginning of MyLinkedList
     * It works by assigning the head to newNode's next and then
     * assigning newNode to head
     */
    public boolean addFirst(T data){
        Node newNode = new Node(data);
        if (head == null){
            head = newNode;
            tail = newNode;
            return true;
        }
        newNode.next = head;
        head = newNode;
        size++;
        return true;
    }
    /*
     * addLast adds the new Node at the end of MyLinkedList chain
     * First it goes to the end of the list and assign the newNode to
     * the last Node's next
     */
    public boolean addLast(T data){
        Node newNode = new Node(data);
        if (head == null){
            head = newNode;
            tail = newNode;
            return true;
        }
        Node current = head;
        while(current.next != null)
            current = current.next;
        current.next = newNode;
        tail = newNode;
        size++;
        return true;
    }
    /*
     * insertAtIndex adds new node at the specified location
     * It uses 1 based indexing
     * First is finds the node at (specified location - 1) 
     * because it has to connect the new node to the chain
     */
    public boolean insertAtIndex(T data, int location){
        if (location < 1){
            return false;
        }
        Node newNode = new Node(data);
        if (head == null){
            head = newNode;
            tail = newNode;
        }
        else if (location == 1){
            newNode.next = head;
            head = newNode;
        }else if (location > size){
            Node current = head;
            while(current.next != null)
            current = current.next;
            current.next = newNode;
            tail = newNode;
        }else{
            int count = 1;
            Node current = head;
            while (count < location - 1){
                count++;
                current = current.next;
            }
            Node nextNode = current.next;
            current.next = newNode;
            newNode.next= nextNode;
        }
        size++;
        return true;
    }
    /*
     * returns the data of the first node (head)
     */
    public T getFirst(){
        return head.data;
    }
    /*
     * returns the data of the last node (tail)
     */
    public T getLast(){
        return tail.data;
    }
    /*
     * addAll method accepts a Collection and then adds
     * all the elements inside that Collection to MyLinkedList by
     * getting the iterator and iterating over the collection
     */
    public boolean addAll(Collection<T> collection){
        Iterator<T> it = collection.iterator();
        Node current = tail;
        while(it.hasNext()){
            Node newNode = new Node(it.next());
            current.next = newNode;
            current = newNode;
            size++;
        }
        tail = current;
        return true;
    }
    /*
     * getAt method gets the data of a node at a specific index
     * the implementation is straightforward
     */
    public T getAt(int index){
        if (index < 1 || index > size){
            return null;
        }
        Node current = head;
        int count = 1;
        while (count < index){
            current = current.next;
            count++;
        }
        return current.data;
    }
    /*
     * contains checks if a there exist any element 
     * with the specified data
     */
    public boolean contains(T data){
        Node current = head;
        while (current != null){
            if (current.data.equals(data))
                return true;
            current = current.next;
        }
        return false;
    }
    /*
     * removeFirst removes the first node if it exists
     */
    public boolean removeFirst(){
        if (head == null)
            return false;
        if (size == 1){
            tail = null;    
        }
        head = head.next;
        size--;
        return true;
    }
    /*
     * removeLast removes the last node if it exist
     */
    public boolean removeLast(){
        if (tail == null)
            return false;
        if (size == 1){
            head = null;
            tail = null;
            size--;
            return true;
        }
        Node current = head;
        while (current.next.next != null)
            current = current.next;
        current.next = null;
        tail = current;
        size--;
        return true;
    }
    /*
     * removeAt method removes the specified node
     * from the MyLinkedList
     */
    public boolean removeAt(int index){
        if (index > size || index < 1){
            return false;
        }
        if (index == 1)
            return removeFirst();
        if (index == size)
            return removeLast();
        
        int count = 1;
        Node current = head;
        while (count < index - 1){
            current = current.next;
            count++;
        }
        current.next = current.next.next;
        size--;
        return true;
    }
    /*
     * removeAll removes all the nodes form the linkedList
     * it will initalize both head and tail to null and other
     * nodes will be distroyed by GC because there will be no
     * refrence variable to refer to the first node, so the first
     * node will be distroyed and there will be no refrence variable to
     * refer to the second node and this way all nodes will be destroyed
     */
    public boolean removeAll(){
        head = null;
        tail = null;
        return true;
    }
    public void reverse(){
        if (head == null || size == 1)
            return;
        Node current, next, prev;
        current = head.next;
        prev = head;
        next = current.next;

        prev.next = null;
        while (next != null){
            current.next = prev;
            prev = current;
            current = next;
            next = next.next;
        }
        current.next = prev;
        head = current;
    }
    /*
     * toString method is overriden to print all elements to console
     * it iterates over the elements until it reaches null
     */
    @Override
    public String toString(){
        Node current = head;
        String str = "";
        while (current != null){
            str += current.data + " -> ";
            current = current.next;
        }
        str += "null";
        return str;
    }
}