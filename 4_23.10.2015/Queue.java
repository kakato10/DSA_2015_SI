public class Queue {
    private SequentialList container;

    public Queue() {
        this.container = new SequentialList();
    }

    public void enqueue(int element) {
        this.container.add(element);
    }

    public int dequeue() throws Exception {
        try {
            int result = this.container.get(0);
            this.container.deleteAt(0);
            return result;
        } catch (Exception e) {
            if (this.container.size() == 0) {
                throw new Exception("Queue is enpty");
            } else {
                throw e;
            }
        }
    }

    public int peak() throws Exception{
        try {
            return this.container.get(0);
        } catch (Exception e) {
            if (this.container.size() == 0) {
                throw new Exception("Queue is enpty");
            } else {
                throw e;
            }
        }
    }

    public int size() {
        return this.container.size();
    }
}
