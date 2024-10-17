package com.pratikgkd.reactive_programming_basics.exercise_files;

import com.pratikgkd.reactive_programming_basics.helpers.StreamSources;

/**
 * Exercise 1 of JavaBrains Reactive Programming from Youtube
 * https://youtu.be/cSJK67USyXA?si=9Q9lsR4iapIPJMtQ
 */
public class BasicReactive {

  public static void main(String[] args) {

    // Print all numbers in the intNumbersStream stream
    StreamSources.intNumbersStream()
        .forEach(num -> System.out.println(num));

    // Print numbers from intNumbersStream that are less than 5
    StreamSources.intNumbersStream()
        .filter(num -> num < 5)
        .forEach(num -> System.out.println(num));

    // Print the second and third numbers in intNumbersStream that's greater than 5
    StreamSources.intNumbersStream()
        .filter(num -> num > 5)
        .skip(1)
        .limit(2)
        .forEach(num -> System.out.println(num));

    //  Print the first number in intNumbersStream that's greater than 5.
    //  If nothing is found, print -1
    Integer greateThenFive = StreamSources.intNumbersStream()
        .filter(num -> num > 5)
        .findFirst()
        .orElse(-1);
    System.out.println("First greater than 5 number is " + greateThenFive);

    // Print first names of all users in userStream
    StreamSources.userStream()
        .forEach(user -> System.out.println(user.getFirstName()));

    // Print first names in userStream for users that have IDs from number stream
    StreamSources.userStream()
        .filter(user -> StreamSources.intNumbersStream().anyMatch(id -> id == user.getId()))
        .forEach(System.out::println);

    //with flatMap
    System.out.println("\nWith flatmap::::");
    StreamSources.intNumbersStream()
        .flatMap(id -> StreamSources.userStream().filter(user -> user.getId() == id))
        .map(User::getFirstName)
        .forEach(System.out::println);
  }

}
