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