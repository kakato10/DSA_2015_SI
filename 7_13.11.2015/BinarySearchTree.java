package BinarySearchTrees;

/**
 * Created by kakato10 on 11/11/2015.
 */
public class BinarySearchTree {
    private BinarySearchTree left;
    private BinarySearchTree right;
    private String key;
    private int value;

    private int searchInSubtree(BinarySearchTree subtree, String key) {
        if (subtree == null) {
            return -1;
        }
        return subtree.search(key);
    }

    private void printPreorderImplementation(int indentetion) {
        String indent = "";
        for (int i = 0; i < indentetion; i++)
            indent += " ";
        System.out.println(indent + this.key + " " + this.value);
        int newIndentation = indentetion + 4;
        if (this.left != null) {
            System.out.println(indent + "left");
            this.left.printPreorderImplementation(newIndentation);
        }
        if (this.right != null) {
            System.out.println(indent + "right");
            this.right.printPreorderImplementation(newIndentation);
        }
    }

    private void removeImplementation(String key, BinarySearchTree parent) {
        // check in which subtree is the element that has to be removed
        if (this.key.compareTo(key) < 0) {
            if (this.right == null)
                return;
            this.right.removeImplementation(key, this);
        } else if (this.key.compareTo(key) > 0) {
            if (this.left == null)
                return;
            this.left.removeImplementation(key, this);
        } else {
            // check whether the current element can be removed
            // directly or it has subtree that we have to take care of
            if (this.left == null && this.right == null) {
                if (parent.left() == this)
                    parent.setLeft(null);
                else
                    parent.setRight(null);
            } else if (this.left != null && this.right == null) {
                if (parent.left == this)
                    parent.setLeft(this.left);
                else
                    parent.setRight(this.left);
            } else if (this.left == null && this.right != null) {
                if (parent.left == this)
                    parent.setLeft(this.right);
                else
                    parent.setRight(this.left);
            } else {
            	// find the rightmost element and swap it with the one
            	// that has to be removed
                BinarySearchTree rightmostParent, rightmost;
                if (this.left.right() != null) {
                    rightmostParent = this.left;
                    while (rightmostParent.right().right() != null)
                        rightmostParent = rightmostParent.right();
                    rightmost = rightmostParent.right();
                } else {
                    rightmostParent = this;
                    rightmost = this.left;
                }
                String thisKey = this.key;
                int thisValue = this.value;
                this.key = rightmost.key();
                this.value = rightmost.value();
                rightmost.setKey(thisKey);
                rightmost.setValue(thisValue);
                rightmost.removeImplementation(key, rightmostParent);
            }
        }
    }

    public BinarySearchTree left() {
        return this.left;
    }

    public void setLeft(BinarySearchTree newLeft) {
        this.left = newLeft;
    }

    public BinarySearchTree right() {
        return this.right;
    }

    public void setRight(BinarySearchTree newRight) {
        this.right = newRight;
    }

    public String key() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int value() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public BinarySearchTree(String key, int value) {
        this.left = null;
        this.right = null;
        this.key = key;
        this.value = value;
    }

    public void add(String key, int value) {
    	//check in which subtree we should add the new node
        if (this.key.compareTo(key) < 0) {
            if (this.right == null) {
                this.right = new BinarySearchTree(key, value);
                return;
            }
            this.right.add(key, value);
        } else if (this.key.compareTo(key) > 0) {
            if (this.left == null) {
                this.left = new BinarySearchTree(key, value);
                return;
            }
            this.left.add(key, value);
        } else {
            this.value = value;
        }
    }

    public int search(String key) {
    	//check in which subtree is the searched node
        if (this.key.compareTo(key) < 0) {
            return this.searchInSubtree(this.right, key);
        } else if (this.key.compareTo(key) > 0) {
            return this.searchInSubtree(this.left, key);
        }
        return this.value;
    }

    public void remove(String key) {
        this.removeImplementation(key, this);
    }

    public void printPreorder() {
        this.printPreorderImplementation(0);
    }
}
