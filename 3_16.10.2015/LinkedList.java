public class LinkedList {
    private Node head, tail;

    public void add(int element) {
        Node node = new Node(element);

        if(this.tail == null) {
            this.head = node;
            this.tail = node;
            return;
        }
        node.setLeft(this.tail);
        this.tail.setRight(node);
        this.tail = node;
    }

    public Node getNode(int index) {
        Node node = this.head;
        for (int i = 0; i < index; i++) {
            node = node.getRight();
        }
        return node;
    }

    public void insert(int element, Node leftNeighbour) {
        Node node = new Node(element);
        if (this.tail == leftNeighbour) {
            leftNeighbour.setRight(node);
            node.setLeft(leftNeighbour);
            this.tail = node;
        } else if (leftNeighbour == null) {
            this.head.setLeft(node);
            node.setRight(this.head);
            this.head = node;
        } else {
            Node rightNeighbour = leftNeighbour.getRight();
            leftNeighbour.setRight(node);
            rightNeighbour.setLeft(node);
            node.setRight(rightNeighbour);
            node.setLeft(leftNeighbour);
        }
    }

    public void print() {
        Node node = this.head;
        while (node != null) {
            System.out.println(node.getElement());
            node = node.getRight();
        }
    }

    public int getSize() {
        Node node = this.head;
        int size = 0;
        while (node != null) {
            size += 1;
            node = node.getRight();
        }
        return size;
    }

    public Node nodeOf(int element) {
        Node node = this.head;
        while (node != null) {
            if (node.getElement() == element)
                return node;
            node = node.getRight();
        }
        return null;
    }
}