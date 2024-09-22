package com.pratikgkd.reactive_programming_basics.exercise_files_yt_javabrains;

import com.pratikgkd.reactive_programming_basics.helpers_yt_javabrains.ReactiveSources;
import java.io.IOException;
import java.time.Duration;

/**
 * Exervice 6: handle unresponsive mono
 * https://www.youtube.com/watch?v=uOfTSkDy-mk&list=PLqq-6Pq4lTTYPR2oH7kgElMYZhJd4vOGI&index=21
 */
public class HandleUnresponsiveMono {


  public static void main(String[] args) throws IOException {

    // Get the value from the Mono into a String variable but give up after 5 seconds
    ReactiveSources.unresponsiveMono().block(Duration.ofMinutes(2));

    // Get the value from unresponsiveFlux into a String list but give up after 5 seconds
    // Come back and do this when you've learnt about operators!
    ReactiveSources.unresponsiveFlux().collectList().block(Duration.ofMinutes(2));

    System.out.println("Press a key to end");
    System.in.read();
  }

}
