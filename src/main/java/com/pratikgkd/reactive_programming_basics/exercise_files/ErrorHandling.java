package com.pratikgkd.reactive_programming_basics.exercise_files;

import com.pratikgkd.reactive_programming_basics.helpers.ReactiveSources;
import java.io.IOException;
import reactor.core.publisher.Flux;

/**
 * Exercise 8: ErrorHandling
 * https://www.youtube.com/watch?v=xzn2KbmkcPE&list=PLqq-6Pq4lTTYPR2oH7kgElMYZhJd4vOGI&index=29
 */
public class ErrorHandling {


  public static void main(String[] args) throws IOException {

    // Use ReactiveSources.intNumbersFluxWithException()

    // Print values from intNumbersFluxWithException and print a message when error happens
    ReactiveSources.intNumbersFluxWithException()
        .doOnError(e -> System.out.println("Error ala: " + e.getMessage()))
        .subscribe(num -> System.out.println(num));

    // Print values from intNumbersFluxWithException and continue on errors
    ReactiveSources.intNumbersFluxWithException()
        .onErrorContinue((e, item) -> System.out.println("Error ala pan contnue karel:: " + e.getMessage()))
        .subscribe(num -> System.out.println(num));

    // Print values from intNumbersFluxWithException and when errors
    // happen, replace with a fallback sequence of -1 and -2
    ReactiveSources.intNumbersFluxWithException()
        .onErrorResume(e -> Flux.just(-1, -2))  //on error use the flux given i.e. of -1,-2 and ignore the og flux
        .subscribe(num -> System.out.println(num));

    System.out.println("Press a key to end");
    System.in.read();
  }

}
