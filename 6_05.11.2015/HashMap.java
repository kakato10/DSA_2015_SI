public class HashMap {
    private Entry[] table;
    private static final int INITIAL_SIZE = 4;
    private static final int GROWING_FACTOR = 2;
    private static final double MAX_LOAD_FACTOR = 0.75;
    private int elementsCount;

    private int hash(String key) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            char currentChar = key.charAt(i);
            hash += currentChar + i * currentChar;
        }
        return hash;
    }

    private int indexer(int hash) {
        return hash % table.length;
    }

    private Entry[] table() {
        return this.table;
    }

    private HashMap(int size) {
        this.table = new Entry[size];
        this.elementsCount = 0;
    }

    public HashMap() {
        this.table = new Entry[INITIAL_SIZE];
        this.elementsCount = 0;
    }

    public void add(String key, int value) {
        int index= this.indexer(this.hash(key));
        Entry entry = new Entry(key, value);
        //check for collision
        if (table[index] == null) {
            this.table[index] = entry;
        } else {
            System.out.println("Collision!!!" + key);
            Entry head = this.table[index];
            if (head.key().equals(key)) {
                head.setValue(value);
                return;
            }
            while (head.next() != null) {
                head = head.next();
            }
            head.setNext(entry);
        }
        this.elementsCount++;

        //check if the table should grow
        double loadFactor = (double)this.elementsCount / this.table.length;
        if (loadFactor >= MAX_LOAD_FACTOR) {
            System.out.println("Copping!");
            HashMap composeTable = new HashMap(this.table.length * GROWING_FACTOR);
            for (int i = 0; i < this.table.length; i++) {
                Entry head = this.table[i];
                while (head != null) {
                    composeTable.add(head.key(), head.value());
                    head = head.next();
                }
            }
            this.table = composeTable.table();
            System.out.println("Table just got bigger " + this.table.length);
        }
    }

    public int get(String key) {
        int index = this.indexer(this.hash(key));
        int result = -1;
        Entry head = this.table[index];
        while (head != null) {
            if (head.key().equals(key)) {
                result = head.value();
                break;
            } else {
                head = head.next();
            }
        }
        return result;
    }

    public boolean remove(String key) {
        int index = this.indexer(this.hash(key));
        Entry head = this.table[index];
        boolean removed = false;
        if (head.key().equals(key)) {
            this.table[index] = null;
            removed = true;
            elementsCount--;
        } else {
            while (head.next() != null) {
                if (head.next().key().equals(key)) {
                    head.setNext(head.next().next());
                    removed = true;
                    elementsCount--;
                    break;
                }
                else {
                    head = head.next();
                }
            }
        }
        return removed;
    }

    public void print() {
        for (Entry head : this.table) {
            if (head != null) {
                while (head != null) {
                    System.out.println(head.toString());
                    head = head.next();
                }
            }
        }
    }

}
