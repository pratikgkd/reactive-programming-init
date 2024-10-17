package com.pratikgkd.reactive_programming_basics.exercise_files;

import com.pratikgkd.reactive_programming_basics.helpers.ReactiveSources;
import java.io.IOException;
import reactor.core.publisher.Flux;

/**
 * Exercise 2 of JavaBrains Reactive Programming from Youtube
 * https://www.youtube.com/watch?v=6jjx6OFObBU&list=PLqq-6Pq4lTTYPR2oH7kgElMYZhJd4vOGI&index=12
 */
public class SubscribeExample {

  public static void main(String[] args) throws IOException {

    //Task: Print all numbers in the ReactiveSources.intNumbersFlux stream
    //Explanation: subscribe() will wait and consume msgs once emitted
    ReactiveSources.intNumbersFlux().subscribe(System.out::println);

    //Task: Print all users in the ReactiveSources.userFlux stream
    ReactiveSources.userFlux().subscribe(System.out::println);

    //Example to show order is not guaranteed
    Flux<Integer> flux = ReactiveSources.intNumbersFlux();
    flux.subscribe(num -> System.out.println("First subscriber" + num));
    flux.subscribe(num -> System.out.println("Second subscriber" + num));
    flux.subscribe(num -> System.out.println("Third subscriber" + num));

    //To hold the program until events are emitted and received, else main will complete and program will emit
    System.out.println("Press a key to end");
    System.in.read();

  }

}
