package com.pratikgkd.reactive_programming_basics.helpers;

import com.pratikgkd.reactive_programming_basics.exercise_files.User;
import java.util.stream.Stream;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StreamSources {

  public static Stream<String> stringNumbersStream() {
    return Stream.of("one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten");
  }

  public static Stream<Integer> intNumbersStream() {
    return Stream.iterate(0, i -> i + 2)
        .limit(10);
  }

  public static Stream<User> userStream() {
    return Stream.of(
        new User(1, "Lionel", "Messi"),
        new User(2, "Cristiano", "Ronaldo"),
        new User(2, "Diego", "Maradona"),
        new User(4, "Zinedine", "Zidane"),
        new User(5, "Jürgen", "Klinsmann"),
        new User(6, "Gareth", "Bale")
    );
  }
}
