package com.pratikgkd.reactive_programming_basics.exercise_files;

import com.pratikgkd.reactive_programming_basics.helpers.ReactiveSources;
import java.io.IOException;

/**
 * Exercise 9 : Convert Flux to Mono and vice versa
 * https://www.youtube.com/watch?v=xrpqIYC-asc&list=PLqq-6Pq4lTTYPR2oH7kgElMYZhJd4vOGI&index=29
 */
public class InterConversionFluxMono {

  public static void main(String[] args) throws IOException {

    // Print size of intNumbersFlux after the last item returns
    ReactiveSources.intNumbersFlux().count().subscribe(System.out::println);

    // Collect all items of intNumbersFlux into a single list and print it
    ReactiveSources.intNumbersFlux().collectList().subscribe(System.out::println);

    // Transform to a sequence of sums of adjacent two numbers
    ReactiveSources.intNumbersFlux()
        .buffer(2)
        .map(list -> list.get(0) + list.get(1))
        .subscribe(System.out::println);

    System.out.println("Press a key to end");
    System.in.read();
  }

}
