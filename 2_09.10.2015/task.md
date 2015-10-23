Sequential List
=====================

Write a Java class called SequentialList that implements the following interface.
Assume that all data, passed is correct.

```Java

public interface ListInterface {
    //Adds an element to the end of the list.
    public void add(int newElement);

    //Inserts an element to the list at specific index.
    //If the index is bigger, than the count of the elements of the list just add the new element to the end.
    public void insert(int newElement, int index);

    //Deletes an element from the list at specific index
    public void deleteAt(int index);

    //Removes the first element that is matched, return true if there's such and false if not.
    public boolean removeElement(int element);

    //Gets an element from the list at specific index.
    public int get(int index);

    //Returns the first index where the specified element is found in the list.
    public int indexOf(int element);

    //Returns a copy of the entire list.
    public SequentialList copy();

    //Returns a new list with the same elements, but in reversed order.
    public SequentialList reverse();

    //Checks if both lists contain the same elements in the same order.
    public boolean equals(SequentialList otherList);

    //Returns new list with all unique elements of the origin list in the same order, in that they're found.
    public SequentialList removeDuplicates();

    //Inserts all elements of @otherList with indeces >= @start and <@end into the current one.
    public void splice(SequentialList otherList, int start, int end);

    //Returns a new list with all elements with indeces >= @inbex from the current list
    //the rest should remain in the current list.
    public SequentialList splitAt(int index);
}

```
