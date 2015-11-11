Hash Tables
=====================
Write a Java class called WordCounter, which implements the following interface:

```Java
public interface BookWordsAnalyzer {
    //reads a book and finds how many times is every word used
    //when new book is read, erase the data for the old one 
    void readBook(String book);

    //returns how many times the word is repeated in the book
    int wordOccurrences(String word);

    //returns how many unique words are used in the book
    int uniqueWordsCount();

    //returns the count of appearances of the mostly used word/words
    int mostlyUsedWordAppearances();

    //returns what type of words is used the most (repeated words do matter)
    //adjectives, nouns, verbs, unknown
    String mostlyUsedWordType();
}
```

The book that the class should analyze is a string, in which the words will be separated
by a simple space. Every word will consist only of lower case letters. The words will be in the following format: `t_word` 
* `t` is the type of the word - n for noun, a for adjective and v for verb;
* `word` will be the word itself;

###Example usage

```Java
readBook("a_strange n_man v_is a_strange n_man");
wordOccurrences("strange"); //2
wordOccurrences("man"); //2
wordOccurrences("is"); //1
uniqueWordsCount(); //1
mostlyUsedWordAppearances(); //2
mostlyUsedWordType(); //unknown
```

####PS: Think very good of the algorithm that you'll implement. There are going to be standings, how fast does your program run! Yep, consider this as a competition!!!