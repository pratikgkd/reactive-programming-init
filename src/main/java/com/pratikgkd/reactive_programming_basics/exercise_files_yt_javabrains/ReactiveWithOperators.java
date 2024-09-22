package com.pratikgkd.reactive_programming_basics.exercise_files_yt_javabrains;

import com.pratikgkd.reactive_programming_basics.helpers_yt_javabrains.ReactiveSources;
import java.io.IOException;

/**
 * Exercise 7: operators with mono/flux
 * https://www.youtube.com/watch?v=S1-_NdvlAyc&list=PLqq-6Pq4lTTYPR2oH7kgElMYZhJd4vOGI&index=25
 */
public class ReactiveWithOperators {


  public static void main(String[] args) throws IOException {

    // Use ReactiveSources.intNumberMono() and ReactiveSources.userMono()

    // Print all values from intNumbersFlux that's greater than 5
    ReactiveSources.intNumbersFlux()
        .filter(number -> number > 5)
        .subscribe(System.out::println);

    // Print 10 times each value from intNumbersFlux that's greater than 5
    ReactiveSources.intNumbersFlux()
        .filter(x -> x > 5)
        .log()
        .repeat(10)
        .subscribe(System.out::println);

    // Print 10 multiplied by each value from intNumbersFlux for the first 3 numbers emitted that's greater than 5
    ReactiveSources.intNumbersFlux()
        .filter(x -> x > 5)
        .take(3)
        .map(x -> x * 10)
        .subscribe(System.out::println);

    // Print each value from intNumbersFlux that's greater than 20. Print -1 if no elements are found
    ReactiveSources.intNumbersFlux()
        .filter(x -> x > 20)
        .defaultIfEmpty(-1)
        .subscribe(System.out::println);

    // Switch ints from intNumbersFlux to the right user from userFlux
    ReactiveSources.intNumbersFlux()
        .flatMap(num -> ReactiveSources.userFlux().filter(user -> user.getId() == num))
        .subscribe(System.out::println);

    // Print only distinct numbers from intNumbersFluxWithRepeat
    ReactiveSources.intNumbersFluxWithRepeat()
        .log()
        .distinct()
        .subscribe(System.out::println);

    // Print from intNumbersFluxWithRepeat excluding immediately repeating numbers
    ReactiveSources.intNumbersFluxWithRepeat()
        .log()
        .distinctUntilChanged()
        .subscribe(System.out::println);

    System.out.println("Press a key to end");
    System.in.read();
  }

}
