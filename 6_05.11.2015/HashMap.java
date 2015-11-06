public class HashMap {
    private Entry[] table;
    private static final int INITIAL_SIZE = 4;
    private static final int GROWING_FACTOR = 2;
    private static final int SHRINKING_FACTOR = 2;

    private static final double MAX_LOAD_FACTOR = 0.75;
    private static final double MIN_LOAD_FACTOR = 0.25;
    private int elementsCount;

    private int hash(String key) {
        int hash = 21;
        for (int i = 0; i < key.length(); i++) {
            char currentChar = key.charAt(i);
            hash += currentChar + i * currentChar;
        }
        return hash;
    }

    private int indexer(int hash) {
        return hash % this.table.length;
    }

    private Entry[] table() {
        return this.table;
    }

    private void swapTables(HashMap hash) {
        for (Entry head : this.table) {
            while (head != null) {
                hash.add(head.key(), head.value());
                head = head.next();
            }
        }
        this.table = hash.table();
    }

    private void tryGrow() {
        double loadFactor = (double) this.elementsCount / this.table.length;
        if (loadFactor >= MAX_LOAD_FACTOR) {
            HashMap hash = new HashMap(this.table.length * GROWING_FACTOR);
            swapTables(hash);
            System.out.println("GROWING to: " + this.table.length);
        }
    }

    private void tryShrink() {
        double loadFactor = this.elementsCount / this.table.length;
        if (loadFactor <= MIN_LOAD_FACTOR) {
            HashMap hash = new HashMap(this.table.length / SHRINKING_FACTOR);
            swapTables(hash);
            System.out.println("SHRINKING to: " + this.table.length);
        }
    }

    public HashMap() {
        this.table = new Entry[INITIAL_SIZE];
        this.elementsCount = 0;
    }

    public HashMap(int size) {
        this.table = new Entry[size];
        this.elementsCount = 0;
    }


    public void add(String key, int value) {
        int hash = this.hash(key);
        int index = this.indexer(hash);
        Entry entry = new Entry(key, value);
        if (this.table[index] == null)
            this.table[index] = entry;
        else {
            Entry head = this.table[index];
            while (head.next() != null) {
                if (head.key().equals(key)) {
                    head.setValue(value);
                    return;
                }
                head = head.next();
            }
            head.setNext(entry);
        }
        elementsCount++;
        this.tryGrow();
    }

    public int get(String key) {
        int hash = this.hash(key);
        int index = this.indexer(hash);
        int value = -1;
        Entry head = this.table[index];
        while (head != null) {
            if (head.key().equals(key)) {
                value = head.value();
                break;
            }
            head = head.next();
        }
        return value;
    }

    public boolean remove(String key) {
        boolean removed = false;
        int hash = this.hash(key);
        int index = this.indexer(hash);
        Entry head = this.table[index];
        if (head.key().equals(key)) {
            if (head.next() != null)
                this.table[index] = head.next();
            else
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
                } else {
                    head = head.next();
                }
            }
        }
        tryShrink();
        return removed;
    }

    public void print() {
        for (Entry head : this.table) {
            while (head != null) {
                System.out.println(head.toString());
                head = head.next();
            }
        }
    }
}
