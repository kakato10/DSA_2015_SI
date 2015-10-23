public class Node {
    private int element;
    private Node left, right;

    public Node(int element) {
        this.element = element;
    }

    public Node(int element, Node left, Node right) {
        this.element = element;
        this.left = left;
        this.right = right;
    }

    public void setLeft(Node neighbour) {
        this.left = neighbour;
    }

    public void setRight(Node neighbour) {
        this.right = neighbour;
    }

    public Node getRight() {
        return this.right;
    }

    public Node getLeft() {
        return this.left;
    }

    public int getElement() {
        return this.element;
    }
}