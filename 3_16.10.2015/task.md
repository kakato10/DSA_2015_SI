Linked List
=====================

Write a Java class called LinkedList that implements the following interface.
Assume that all data, passed is correct.

```Java

public interface LinkedListInterface {
    //Adds an element to the end of the list.
    public void add(int element);

    //Gets the node from the list is at the specific index.
    public Node getNode(int index);

    //Prints all elements of the list
    public void print();

    //Inserts an element to the right of @leftNeighbour
    //If @leftNeighbour is not specified add it as first element of the list.
    public void insert(int element, Node leftNeighbour);

    //Returns the first node with element matching the specified value
    public Node nodeOf(int element);

    //Returns the current size of the list;
    public int getSize();

    //Removes the given node from the list
    public void removeAt(Node node);

    //Removes the first node with element matching the specified value, return true if there's such and false if not.
    public boolean removeElement(int element);

    //Returns a copy of the entire list.
    public LinkedList copy();

    //Returns a new list with the same elements, but in reversed order.
    public LinkedList reverse();

    //Checks if both lists contain the same elements in the same order and have the same size.
    public boolean equals(LinkedList otherList);

    //Returns new list with all unique elements of the original list in the same order, in that they're found.
    public LinkedList removeDuplicates();

    //Inserts coppies of all elements of @otherList that are between @start and @end including @start
    public void splice(Node start, Node end);

    //Returns a new list with all elements on the right of @node including @node and removes them from the original list
    //Тhe rest (to the left of @node) should remain in the current list.
    public LinkedList splitAt(Node node);
}

```
