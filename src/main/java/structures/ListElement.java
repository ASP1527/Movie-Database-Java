package structures;
public class ListElement<E> {
    private final Object[] value;
    private ListElement<E> next;
    
    // Sets the value of the element to an array
    public ListElement(Object[] value) {
        this.value = value;
    }
    
    // Gets the array (value) of an element
    public Object[] getValue() {
        return this.value;
    }
    
    // Gets the next item
    public ListElement<E> getNext() {
        return this.next;
    }
    
    // Sets the next pointer of an element
    public void setNext(ListElement<E> e) {
        this.next = e;
    }
}
