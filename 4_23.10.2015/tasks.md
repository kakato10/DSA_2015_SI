Stacks and Queues
=====================

#Task 1: Brackets matcher
Write a Java class BracketsMatcher that contains the following function:

```Java
    public static boolean bracketsMatch(String expression)
```

The function should use a stack to check if all brackets in the expression are closed in the correct order. The  expression will contain only brackets. Don't worry if you see {} inside [] or something like that. We don't care about their math meaning in this task.

##Examples:
Input:

```
(())[{}]()
```

Output:

```
true
```
___

Input:

```
([{]){[()]}
```

Output:

```
false
```

#Task 2: Function call stack
Write a Java class functionCallStack, that contains the following function:

``` JAVA
public static Stack<String> biggestCallStack(String main, String[] functions)
``` 

* ```main``` is the main function that you'll have to read;
* ```functions``` is an array of functions that can be called. The array will contain at most 4 elements. A function is called when you meet a string like this one ```1()```. This will call the function at index 1 in the array;

The function should return the biggest function callstack that was built during the execution of the "program". There will be no recursions at all. Use the standart library called Stack that is implemented in Java.

##Example:
If you are passed this parameters:

```
main = "aasdfa2()asdfeqwer1()3()asdfasdf0()"
function = ["3()asdfsa", "asdfasd", "1()sfsd1()", "1()"]
```

The elements contained in the stack that will be the result should be:

```
1
3
0
main
```

#Task 3: Queued Stack
Write a Java class called Stack, that has the following interface:
```Java
public interface StackInterface {
    //Adds an element to the stack.
    public void add(int element);

    //returns the next element that should be poped without popping it.
    public int peak();

    //Returns and removes the last element of the stack.
    public int pop();

    //Returns the current size of the stack
    public int size();

    //Checks if the stack is empty.
    public boolean isEmpty();
}

```

In order to implement the logic use the standart library for Queues in Java. (Basically the task is to write stack that uses queue to store it's elements)
