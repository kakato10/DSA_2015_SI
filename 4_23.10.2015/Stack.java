public class Stack {
    private SequentialList container;

    public Stack() {
        this.container = new SequentialList();
    }

    public void push(int element) {
        this.container.add(element);
    }

    public int peak() throws Exception{
        try {
            return this.container.get(this.container.size() - 1);
        } catch (Exception e) {
            if (this.container.size() == 0) {
                throw new Exception("Stack is empty");
            } else {
                throw e;
            }
        }
    }

    public int pop() throws Exception{
        try {
            int lastElementIndex = this.container.size() - 1;
            int result = this.container.get(lastElementIndex);
            this.container.deleteAt(lastElementIndex);
            return result;
        } catch (Exception e) {
            if (this.container.size() == 0) {
                throw new Exception("Stack is empty!");
            } else {
                throw e;
            }
        }

    }

    public int size() {
        return this.container.size();
    }
}
