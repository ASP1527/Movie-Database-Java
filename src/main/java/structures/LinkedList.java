package structures;
public class LinkedList<E> implements ILinkedList<E> {
    
    ListElement<E> head;
    
    // Sets the linked list as empty
    public LinkedList() {
        this.head = null;
    }

    // Searches the linked list until it gets to the index
    public Object[] get(int index) {
        ListElement<E> ptr = head;
        for (int i=0;i<index;i++) {
            ptr = ptr.getNext();
        }
        // returns the array
        return ptr.getValue();
    }

    // Gets the index of an array based on film id
    public int indexOf(int element) {
        // gets the first item
        ListElement<E> ptr = head;
        // index variable to hold index of current item
        int i=0;
        // uses an integer wrapper to represent the film id
        Integer value = element;
        // check if list is not empty before checking pointers
        if (size() != 0) {
            while (ptr.getNext() != null)  {
                // gets the current item
                Object[] list = (ptr.getValue());
                // checks if its the film id
                if (list[0].equals(value)) {
                    // returns the index
                    return i;
                }
                if (list[1].equals(value)) {
                    // returns the index
                    return i;
                }
                i++;
                // gets next item
                ptr = ptr.getNext();
            }
            // runs once more to check final item
            Object[] list = (ptr.getValue());
            if (list[0].equals(value)) {
                return i;
            }
            if (list[1].equals(value)) {
                return i;
            }
        }
        // returns -1 if not found
        return -1;
    }

    // sets a new array at an index
    public Boolean set(int index, Object[] element) {
        ListElement<E> ptr = head;
        // finds where to put the array
        for (int i=0;i<index;i++) {
            ptr = ptr.getNext();
        }
        // sets the array as the element 
        ListElement<E> newlink = new ListElement<>(element);
        newlink.setNext(ptr.getNext().getNext());
        ptr.setNext(newlink);
        return true;
    }

    // sets new item at the head
    public boolean add(Object[] element) {
        ListElement<E> temp = new ListElement<>(element);
        
        // if the list is not empty, point the new link to head
        if (head != null) {
            temp.setNext(head);
        }
        // Make the new item the head element
        head = temp;
        
        return true;
    }

    // sets new item at the tail
    public boolean addTail(Object[] element) {
        ListElement<E> temp = new ListElement<>(element);
        
        // if the list is not empty, point the new link to head
        if (head == null) {
            // Make the new item the head element
            head = temp;
            head.setNext(null);
        } else {
            // gets the head of the list
            ListElement<E> ptr = head;
            // finds end of list
            while (ptr.getNext() != null) {
                ptr = ptr.getNext();
            }
            ptr.setNext(temp);
            ptr.getNext().setNext(null);
            return true;
            
        }
        
        return true;
    }

    //clears the list
    public void clear() {
        head = null;
    }

    // checks if an element is in the list
    public boolean contains(int element) {
        return indexOf(element) != -1;
    }

    // checks if the list has any items
    public boolean isEmpty() {
        return head == null;
    }

    public boolean removeHead() {
        // checks if there is an item to remove
        if (isEmpty()) {
            return false;
        }
        // removes the head of the list
        ListElement<E> ptr = head;
        if (size() == 0) {
            clear();
            return true;
        }
        head = ptr.getNext();
        return true;
    }

    // removes an item from the list
    public boolean remove(int element) {
        // checks if there is an item to remove
        if (isEmpty()) {
            return false;
        }
        if (contains(element)) {
            // if there is an item to remove
            // sets element to remove in an integer wrapper
            Integer e = element;
            // gets the head of the list
            ListElement<E> ptr = head;
            // finds the item and removes it
            // checks if there is only 1 element
            if (size() == 1) {
                clear();
                return true;
            }
            // checks if the item to remove is the head
            if (indexOf(e) == 0) {
                head = ptr.getNext();
                return true;
            }
            // finds and removes element
            while (ptr.getNext().getValue() != get(e)) {
                ptr = ptr.getNext();
            }
            ptr.setNext(ptr.getNext().getNext());
            ptr.getNext().setNext(null);
            return true;
        }
        return false;
    }

    // gets size of linked list
    public int size() {
        // checks if its empty
        if (isEmpty()) return 0;
        // follows pointers until the end
        ListElement<E> ptr = head;
        int i = 1;
        while (ptr.getNext() != null) {
            i++;
            ptr = ptr.getNext();
        }
        // returns the size
        return i;
    }

    // removes an item from the list with a composite key
    public boolean removeComposite(int id1, int id2) {
        // checks if there is an item to remove
        if (isEmpty()) {
            return false;
        }
        // if there is an item to remove
        // sets element to remove in an integer wrapper
        Integer ID1 = id1;
        Integer ID2 = id2;
        // gets the head of the list
        ListElement<E> ptr = head;
        // finds the item and removes it
        if (size() == 1) {
            clear();
            return true;
        }
        while (ptr.getNext().getValue() != null) {
            if ((int)ptr.getValue()[0] == id1 & (int)ptr.getValue()[1] == id2) {
                ptr.setNext(ptr.getNext().getNext());
                ptr.getNext().setNext(null);
                return true;
            }
            ptr = ptr.getNext();
        }
        // returns false if item was not found
        return false;
    }

    // method to return a list of objects that are equal to the id using the key at pos; userID pos=0, movieID pos=1 for example
    public Object[][] getRow(int id, int pos) {
        // gets the head of the list
        ListElement<E> ptr = head;
        // new buffer object to hold the items
        Object[][] buffer = new Object[size()][];
        // index variable to hold the size
        int i = 0;
        // checks if the key matches the item
        while (ptr.getNext() != null) {
            if ((int)ptr.getValue()[pos] == id) {
                // gets the array at the position
                Object[] temp = ptr.getValue();
                // creates a 4 length array at the position of the buffer to hold the ratings
                buffer[i] = new Object[4];
                // sets the ratings data
                buffer[i][0] = temp[0];
                buffer[i][1] = temp[1];
                buffer[i][2] = temp[2];
                buffer[i][3] = temp[3];
                i ++;
            }
            ptr = ptr.getNext();
        }
        // does it for the last list item
        if ((int)ptr.getValue()[pos] == id) {
            Object[] temp = ptr.getValue();
            buffer[i] = new Object[4];
            buffer[i][0] = temp[0];
            buffer[i][1] = temp[1];
            buffer[i][2] = temp[2];
            buffer[i][3] = temp[3];
            i ++;
        }
        // removes the null objects by creating the array to have only the number of elements that were found as the size using the variable i
        Object[][] ret = new Object[i][];
        for (int k=0; k < i; k++) {
            Object[] temp2 = buffer[k];
            ret[k] = new Object[4];
            ret[k][0] = temp2[0];
            ret[k][1] = temp2[1];
            ret[k][2] = temp2[2];
            ret[k][3] = temp2[3];
        }
        return ret;
    }

    // gets all items in the list
    public Object[][] getAll() {
        // Array of arrays for the return object
        Object[][] ret = new Object[size()][];
        // gets the head element
        ListElement<E> ptr = head;
        // goes through the list and adds the data
        for (int i=0;i<size();i++) {
            ret[i] = ptr.getValue();
            ptr = ptr.getNext();
        }
        // returns the array
        return ret;
    }

    public int find(int id) {
        return -1;
    }
    
    /*
    public String toString() {
        String ret = "";
        ListElement<E> ptr = head;
        while (ptr.getNext() != null) {
            ret += ptr.getValue()[0]+" "+ptr.getValue()[1]+", ";
            ptr = ptr.getNext();
        }
        ret += ptr.getValue()[0]+" "+ptr.getValue()[1];
        return ret;
    }
    */
}