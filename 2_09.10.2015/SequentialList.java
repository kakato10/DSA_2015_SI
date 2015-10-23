public class SequentialList {
    private static final int INITIAL_SIZE = 2;
    private static final double GROWING_FACTOR = 2;
    private int elementsCount;
    private int[] array;

    private void tryGrow() {
        if (this.elementsCount == this.array.length) {
            int[] newArray = new int[(int) (this.array.length * this.GROWING_FACTOR)];
            for (int i = 0; i < this.array.length; i++) {
                newArray[i] = this.array[i];
            }
            this.array = newArray;
        }
    }

    public SequentialList() {
        this.array = new int[INITIAL_SIZE];
        this.elementsCount = 0;
    }
    
    public void add(int newElement) {
        this.tryGrow();
        this.array[this.elementsCount] = newElement;
        this.elementsCount++;
    }

    public int get(int index) {
        return this.array[index];
    }

    public void insert(int newElement, int index) {
        if (index >= this.elementsCount) {
            this.add(newElement);
            return;
        }
        this.tryGrow();
        for (int i = this.elementsCount - 1; i >= index; i--) {
            this.array[i + 1] = this.array[i];
        }
        this.array[index] = newElement;
        this.elementsCount++;
    }

    public int indexOf(int element) {
        for (int i = 0; i < this.elementsCount; i++) {
            if (element == this.array[i]) {
                return i;
            }
        }
        return -1;
    }

    public SequentialList copy() {
        SequentialList listCopy = new SequentialList();
        for (int i = 0; i < this.elementsCount; i++) {
            listCopy.add(this.array[i]);
        }
        return listCopy;
    }

    public void print() {
        for (int i = 0; i < this.array.length; i++) {
            System.out.println(this.array[i]);
        }
    }
}
