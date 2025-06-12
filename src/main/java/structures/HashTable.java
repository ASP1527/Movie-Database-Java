package structures;

public class HashTable {
    protected LinkedList[] table;
    private int numElements;
    // makes hash table of size 1000
    public HashTable() {
        this(1001);
        this.numElements = 0;
    }
    
    // makes linked list at each entry
    public HashTable(int size) {
        table = new LinkedList[size];
        initTable();
    }
    
    // initialises the table
    protected void initTable() {
        for(int i = 0; i < table.length; i++) {
            table[i] = new LinkedList<>();
        }
    }
    
    // hashes a string
    protected int hash(String key) {
        int code = key.hashCode();
        return code;    
    }

    // adds an element
    public void add(Object[] element) {
        // gets hash position of film id
        String h = element[0]+"";
        int hash_code = hash(h);
        int location = hash_code % table.length;
        // adds element into the linked list at that position
        this.numElements ++;
        table[location].add(element);
    }

    // gets the array at an index
    public Object[] get(int id) {
        // gets hash pos of item
        int hash_code = hash(id+"");
        int location = hash_code % table.length;
        // gets linked list and finds array position
        return (Object[])table[location].get(table[location].indexOf(id));
    }

    // removes an element
    public void remove(int id) {
        // gets hash pos of item
        int hash_code = hash(id+"");
        // gets location of item
        int location = hash_code % table.length;
        // reduces number of elements
        this.numElements --;
        // removes item
        table[location].remove(id);
    }

    public Boolean set(int id, Object[] list) {
        // removes item at location
        remove(id);
        // adds the item at the location
        add(list);
        return true;
    }

    public Boolean find(int id) {
        // gets hash position of film id
        String h = id+"";
        int hash_code = hash(h);
        int location = hash_code % table.length;
        // adds element into the linked list at that position
        return table[location].contains(id);
    }

    public int size() {
        return this.numElements;
    }
}



    /*
    public int find(K key) {
        //n comparisons where n is the placement of item in the linked list
        //returns the number of comparisons required to find element using Linear Search.
        
        int hash_code = hash(key);
        int location = hash_code % table.length;
        
        ListElement<KeyValuePair> ptr = table[location].getHead();
        System.out.println(ptr.getValue());
        int c = 0;
        Boolean found = false;
        while (!found) {
            c += 1;
            if (ptr.getValue().getKey() == key) {
                return c;
            } else {
                ptr = ptr.getNext();
            }
        }
        return 0;
    }*/