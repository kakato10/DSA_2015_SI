public class Entry {
    private String key;
    private int value;
    private Entry next;

    public Entry(String key, int value) {
        this.key = key;
        this.value = value;
    }

    public int value() {
        return this.value;
    }

    public String key() {
        return this.key;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setNext(Entry next) {
        this.next = next;
    }

    public Entry next() {
        return this.next;
    }

    public String toString() {
        return this.key + ": " + this.value;
    }
}
