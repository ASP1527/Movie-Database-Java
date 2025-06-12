package structures;

/**
 * An interface for a generic list.
 */
public interface ILinkedList<E> {

    // Adds element to the list, returns true on success and false otherwise.
    public boolean add(Object[] element);
        
    // Clears (empties) the list.
    public void clear();
    
    // Returns true when element is in the list, false otherwise.
    public boolean contains(int element);
    
    // Returns true when the list contains no elements.
    public boolean isEmpty();
    
    // Removes an element from the list. 
    // Returns true on success, false if the element was not found.
    public boolean remove(int element);
    
    // Returns the number of elements stored in the list.
    public int size();
    
    // Returns the element stored at position index.
    public Object[] get(int index);
    
    // Returns the index of element in the list, returns -1 if element was not found.
    public int indexOf(int element);
    

    // Sets position index of the list to element.
    public Boolean set(int index, Object[] element);
        
}
