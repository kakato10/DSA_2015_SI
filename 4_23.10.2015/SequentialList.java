import java.util.HashSet;
import java.util.Set;

/**
 * Basic implementation of SequentialList (ArrayList)
 * @author Kostadin Hamanov
 */

public class SequentialList {
    private int[] array;
    private int elementsCount;
    private final int INITIAL_SIZE = 4;
    private final double GROWING_FACTOR = 2;
    private final int REDUCING_FACTOR = 2;
    private final int REDUCER = 4;

    /**
     * Creating a larger array when the size of the
     * array is equal to its elements count
     */
    private void tryGrow() {
        if (this.elementsCount == this.array.length) {
            int[] newArray = new int[(int)(this.array.length * this.GROWING_FACTOR)];
            for (int i = 0; i < this.array.length; i++) {
                newArray[i] = this.array[i];
            }
            this.array = newArray;
        }
    }

    /**
     * Reducing the capacity of the array when its elements count is equal or
     * smaller than the length of the array divided by @REDUCER
     */
    private void tryReduce() {
        if (this.elementsCount <= (this.array.length / REDUCER)) {
            int[] newArray = new int[this.array.length / REDUCING_FACTOR];
            System.arraycopy(this.array, 0, newArray, 0, this.elementsCount);
            this.array = newArray;
        }
    }

    public int[] getArray() {
        return array;
    }

    public int getElementsCount() {
        return elementsCount;
    }

    public int getINITIAL_SIZE() {
        return INITIAL_SIZE;
    }

    public double getGROWING_FACTOR() {
        return GROWING_FACTOR;
    }

    public int getREDUCING_FACTOR() {
        return REDUCING_FACTOR;
    }

    public int getREDUCER() {
        return REDUCER;
    }

    /**
     * Constructing a new array
     */
    public SequentialList() {
        this.array = new int[INITIAL_SIZE];
        this.elementsCount = 0;
    }


    /**
     * Adds an element to the end of the list.
     * @param newElement - the element to be added
     */
    public void add(int newElement) {
        this.tryGrow();
        this.array[elementsCount] = newElement;
        this.elementsCount++;
    }

    /**
     * Inserts an element to the list at specific index.
     * If the index is bigger, then to the count of the elements of the 
     * list just add the new element to the end.
     * @param newElement - the element to be added.
     * @param index - the index that the element will be insert on.
     */
    public void insert(int newElement, int index) {
        if (index >= this.elementsCount) {
            this.add(newElement);
            return;
        }

        tryGrow();
        for (int i = this.elementsCount - 1; i >= index; i--) {
            this.array[i + 1] = this.array[i];
        }
        this.array[index] = newElement;
        this.elementsCount++;
    }

    /**
     * Deletes an element from the list at specific index
     * @param index - the index of the element that will be deleted
     */
    public void deleteAt(int index) {
        for (int i = index; i < this.elementsCount - 1; i++) {
            this.array[i] = this.array[i + 1];
        }
        this.array[elementsCount - 1] = 0;
        this.elementsCount--;
        this.tryReduce();
    }

    /**
     * Printing the elements of the list
     */
    public void print() {
        for (int i = 0; i < this.array.length; i++) {
            System.out.println(this.array[i]);
        }
    }

    /**
     * Removes the first element that is matched
     * @param element - the element for removing
     * @return return true if there's such and false if not.
     */
    public boolean removeElement(int element) {
        for (int i = 0; i < this.elementsCount; i++) {
            if (this.array[i] == element) {
                this.deleteAt(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Gets an element from the list at specific index.
     * @param index
     * @return - the element on "index"
     */
    public int get(int index) {
        return this.array[index];
    }

    /**
     * Getting the first index where the specified 
     * element is found in the list.
     * @param element
     * @return the first index where the specified 
     * element is found in the list, otherwise -1
     */
    public int indexOf(int element) {
        for (int i = 0; i < this.elementsCount; i++) {
            if (this.array[i] == element) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns a copy of the entire list
     * @return the copy of the entire list
     */
    public SequentialList copy() {
        SequentialList listCopy = new SequentialList();
        for (int i = 0; i < this.elementsCount; i++) {
            listCopy.add(this.array[i]);
        }
        return listCopy;
    }

    /**
     * Returns a new list with the same elements, but in reversed order
     * @return the reversed array
     */
    public SequentialList reverse() {
        SequentialList reversed = new SequentialList();
        for (int i = this.elementsCount - 1; i >= 0; i--) {
            reversed.add(this.array[i]);
        }
        return reversed;
    }


    /**
     * Checks if both lists contain the same elements in the same order.
     * @param otherList
     * @return if they contain the same elements in the same order.
     */
    public boolean equals(SequentialList otherList) {
        if (this.elementsCount != otherList.elementsCount) {
            return false;
        }

        for (int i = 0; i < this.elementsCount; i++) {
            if (this.array[i] != otherList.get(i)) {
                return false;
            }
        }

        return true;
    }

    /**
     * @return A new list with all unique elements of the origin 
     * list in the same order, in that they're found.
     */
    public SequentialList removeDuplicates() {
        SequentialList uniqueList = new SequentialList();
        Set<Integer> uniqueElements =  new HashSet<Integer>();
        for (int i = 0; i < this.elementsCount; i++) {

            if (!(uniqueElements.contains(this.array[i]))) {
                uniqueElements.add(this.array[i]);
                uniqueList.add(this.array[i]);
            }
        }

        return uniqueList;
    }

    /**
     * Inserts all elements of @otherList with indexes >= @start 
     * and <@end into the current one.
     * @param otherList
     * @param start
     * @param end
     */
    public void splice(SequentialList otherList, int start, int end) {
        for (int i = start; i < end; i++) {
            this.add(otherList.get(i));
        }
    }

    /**
     * Returns a new list with all elements with indexes >= @index from the current list
     * the rest should remain in the current list.
     * @param index
     * @return - a new list
     */
    public SequentialList splitAt(int index) {
        SequentialList newList = new SequentialList();
        int currentListInititalSize = this.elementsCount;
        for (int i = 0; i < currentListInititalSize - index; i++) {
            newList.add(this.get(index));
            this.deleteAt(index);
        }
        return newList;
    }

    public int size() {
        return this.elementsCount;
    }
}
