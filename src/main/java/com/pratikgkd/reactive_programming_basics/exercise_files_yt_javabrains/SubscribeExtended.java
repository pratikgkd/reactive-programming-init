package com.pratikgkd.reactive_programming_basics.exercise_files_yt_javabrains;

import com.pratikgkd.reactive_programming_basics.helpers_yt_javabrains.ReactiveSources;
import java.io.IOException;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;

/**
 * Exercise 5: overloaded and new way of subcribe method
 * https://www.youtube.com/watch?v=w3NT4ltKT1s&list=PLqq-6Pq4lTTYPR2oH7kgElMYZhJd4vOGI&index=20
 */
public class SubscribeExtended {

  public static void main(String[] args) throws IOException {

    //More on subscribe (overloads)
    // 1. with implementation and error handling params viz 2 consumers
    ReactiveSources.intNumberMono().subscribe(num1 -> System.out.println(num1),
        err -> System.out.println(err.getMessage()));

    // 2. with implementation, error & completion handling params
    ReactiveSources.intNumberMono().subscribe(num1 -> System.out.println(num1),
        err -> System.out.println(err.getMessage()),
        () -> System.out.println("Completed flow"));

// --------------------------------------------------------------------------------------------------

    // Subscribe to a flux using the error and completion hooks
    ReactiveSources.intNumberMono().subscribe(
        num -> System.out.println(num),
        err -> System.out.println(err.getMessage()),
        () -> System.out.println("Completed")
    );

    // Subscribe to a flux using an implementation of BaseSubscriber
    ReactiveSources.intNumbersFlux().subscribe(new CustomSubscriber<>());

    System.out.println("Press a key to end");
    System.in.read();
  }

}

/**
 * subscribe but don't start consuming we mention max count of data we are ok with consuming in
 * request(n), WE DON'T  pull that much data, just mention the count pushing data still we producer
 * the pattern still remains PUSH and not PULL
 * @param <T>
 */
class CustomSubscriber<T> extends BaseSubscriber<T> {

  public void hookOnSubscribe(Subscription subscription) {
    System.out.println("Subscribe happened");

    // that we are ok with receiving 1 item, when will be sent depends on emitter; we just mention out limit
    // on subscription 1 item will be received
    request(1);
  }

  public void hookOnNext(T value) {
    System.out.println(value.toString());

    //if not given, won't recieve next items.
    // will receive when emitter emits
    request(2);
  }

}