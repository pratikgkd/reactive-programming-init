package com.pratikgkd.reactive_programming_basics.exercise_files_yt_javabrains;

import com.pratikgkd.reactive_programming_basics.helpers_yt_javabrains.ReactiveSources;
import java.io.IOException;
import java.util.Optional;

/**
 * Exervice 4: Intro to Mono
 * https://www.youtube.com/watch?v=2C39c4iy0wI&list=PLqq-6Pq4lTTYPR2oH7kgElMYZhJd4vOGI&index=16
 */
public class IntroToMono {

  public static void main(String[] args) throws IOException {

    // Use ReactiveSources.intNumberMono()

    // Print the value from intNumberMono when it emits
    ReactiveSources.intNumberMono().subscribe(num -> System.out.println(num));

    // Get the value from the Mono into an integer variable
    Integer num = ReactiveSources.intNumberMono().block();
    Optional<User> userFoo = ReactiveSources.userMono().blockOptional();

    System.out.println("Press a key to end");
    System.in.read();
  }

}
