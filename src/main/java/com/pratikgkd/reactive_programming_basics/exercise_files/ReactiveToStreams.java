package com.pratikgkd.reactive_programming_basics.exercise_files;

import com.pratikgkd.reactive_programming_basics.helpers.ReactiveSources;
import java.io.IOException;
import java.util.List;

/**
 * Exercise 3: reactive to streams
 * https://www.youtube.com/watch?v=QIqE3Nzfkfc&list=PLqq-6Pq4lTTYPR2oH7kgElMYZhJd4vOGI&index=19
 */
public class ReactiveToStreams {

  public static void main(String[] args) throws IOException {

    // Get all numbers in the ReactiveSources.intNumbersFlux stream
    // into a List and print the list and its size

    // Output is displayed after sometime since it waits till all the elements are emitted, hence blocking op
    List<Integer> integerList = ReactiveSources.intNumbersFlux().toStream().toList();
    System.out.println("List: " + integerList);

    System.out.println("Press a key to end");
    System.in.read();
  }

}
