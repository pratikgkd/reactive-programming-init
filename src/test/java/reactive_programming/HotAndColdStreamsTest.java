package reactive_programming;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

import java.time.Duration;

class HotAndColdStreamsTest {

  // Tests cold stream behavior where each subscriber receives the full sequence of emitted elements independently.
  @Test
  void coldStreamTest() {
    var numbers = Flux.range(1,10);

    numbers.subscribe(integer -> System.out.println("Subscriber 1 = " + integer));
    numbers.subscribe(integer -> System.out.println("Subscriber 2 = " + integer));
  }

  // Tests hot stream behavior where subscribers share the same emission, with delayed second subscriber observing only later elements.
  @SneakyThrows
  @Test
  void hotStreamTest() {
    var numbers = Flux.range(1,10)
        .delayElements(Duration.ofMillis(1000));

    ConnectableFlux<Integer> publisher = numbers.publish();
    publisher.connect();

    publisher.subscribe(integer -> System.out.println("Subscriber 1 = " + integer));
    Thread.sleep(4000);
    publisher.subscribe(integer -> System.out.println("Subscriber 2 = " + integer));
    Thread.sleep(10000);
  }
}
