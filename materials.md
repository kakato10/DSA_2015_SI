Materials
=====================

##Data structures
In order to make our algorithms effective we have to find the right data structures to use. Every data structure is useful in different situations. So there are three questions that we have to answer:
* What data structures are on our disposal?
* Which of them are appropriate for our goals?
* What are their benefits and downsides?

Basically, sometimes it's not that easy to choose the correct data structure. Here's a table that could help you in making that choise. (The table will be updated for every exercise that we have)

###Data structures comparison

| Data structure  | Get | Append | Insert | RemoveAt  | Search | Random Access? |
|-----------------|:---:|:------:|:------:|:---------:|:------:|:------:|
| Sequential List (Dynamic Array)  | `O(1)`  | `O(1)`<sub>A</sub> | `O(n)` | `O(n)` | `O(n)` | True |

* Subscript <sub>A</sub> means that the complexity is amortized.

The widely used data structures already have implementations in most of the programming languages.
Here's another table that show the names of these implementations in the respective languages.

###Standart library implementations

| Language  | SequentialList | LinkedList | Stack | Queue  | HashMap | Set | BST | Sorting algorithm
|-----------------|:---:|:------:|:------:|:---------:|:------:|:------:|:------:|:------:|
| C++ | `vector `  | `list` | `stack` | `queue` | `unordered_map` | `unordered_set` | `map` | `introsort` |
| Java | `ArrayList`  | `LinkedList` | `Stack` | `Queue` | `HashMap` | `HashSet` | `-` | `timsort` |
| C# | `List`  | `LinkedList` | `Stack` | `Queue` | `Dictionary` | `HashSet` | `SortedDictionary` | `introsort`
| Python | `list`  | `-` | `-` | `-` | `dict` | `set` | `OrderedDict` | `timsort` |
| JavaScript | `Array`  | `-` | `Array` | `Array` | `Object` | `-` | `-` | `NDS` |

* NDS - Not Described in Standard (i.e. the language has multiple implementations that use different algorithms)
