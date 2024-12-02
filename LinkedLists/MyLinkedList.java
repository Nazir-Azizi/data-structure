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
    // numberOfCurrentNodes keeps track of the number of nodes presesnt inside the MyLinkedList
    private int numberOfCurrentNodes = 0;
    // head is the begining of MyLinkedList
    private Node head = null;
    /*
     * addBeginning adds the new Node at the beginning of MyLinkedList
     * It works by assigning the head to newNode's next and then
     * assigning newNode to head
     */
    public boolean addBeginning(T data){
        Node newNode = new Node(data);
        numberOfCurrentNodes++;
        newNode.next = head;
        head = newNode;
        return true;
    }
    /*
     * addEnd adds the new Node at the end of MyLinkedList chain
     * First it goes to the end of the list and assign the newNode to
     * the last Node's next
     */
    public boolean addEnd(T data){
        Node newNode = new Node(data);
        numberOfCurrentNodes++;
        if (head == null){
            head = newNode;
            return true;
        }
        Node current = head;
        while(current.next != null)
            current = current.next;
        current.next = newNode;
        return true;
    }
    /*
     * display prints all the nodes to console by iterating over
     * MyLinkedList until it reaches null
     */
    public void display(){
        Node current = head;
        while (current != null){
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public int getCurrentNumberOfNodes(){
        return numberOfCurrentNodes;
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
        int numberOfCurrentNodess = getCurrentNumberOfNodes();
        Node temp = new Node(data);
        if (location == 1){
            temp.next = head;
            head = temp;
        }else if (location > numberOfCurrentNodess){
            Node current = head;
            while(current.next != null)
                current = current.next;
            current.next = temp;
        }else{
            int count = 1;
            Node current = head;
            while (count < location - 1){
                count++;
                current = current.next;
            }
            Node nextNode = current.next;
            current.next = temp;
            temp.next= nextNode;
        }
        return true;
    }
}