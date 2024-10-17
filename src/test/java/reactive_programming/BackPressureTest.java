package reactive_programming;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

class BackPressureTest {


  // Tests backpressure handling by requesting 3 elements from a Flux and canceling after receiving the third.
  @Test
  void testBackPressure() {
    var numbers = Flux.range(1,100).log();

    numbers.subscribe(new BaseSubscriber<Integer>() {
      @Override
      protected void hookOnSubscribe(Subscription subscription) {
        request(3);
      }

      @Override
      protected void hookOnNext(Integer value) {
        System.out.println("value = " + value);
        if(value ==3) cancel();
      }

      @Override
      protected void hookOnComplete() {
        System.out.println("Completed!!");
      }

      @Override
      protected void hookOnError(Throwable throwable) {
        super.hookOnError(throwable);
      }

      @Override
      protected void hookOnCancel() {
        super.hookOnCancel();
      }
    });
  }

  // Tests backpressure drop behavior, where excess elements are dropped and logged after requesting 3 elements.
  @Test
  void testBackPressureDrop() {
    var numbers = Flux.range(1,100).log();

    numbers
        .onBackpressureDrop(integer -> {
          System.out.println("Dropped Values = " + integer);
        })
        .subscribe(new BaseSubscriber<Integer>() {
          @Override
          protected void hookOnSubscribe(Subscription subscription) {
            request(3);
          }

          @Override
          protected void hookOnNext(Integer value) {
            System.out.println("value = " + value);
            if(value ==3) hookOnCancel();
          }

          @Override
          protected void hookOnComplete() {
            System.out.println("Completed!!");
          }

          @Override
          protected void hookOnError(Throwable throwable) {
            super.hookOnError(throwable);
          }

          @Override
          protected void hookOnCancel() {
            super.hookOnCancel();
          }
        });
  }

  // Tests backpressure buffer handling, buffering up to 10 elements and logging them before canceling after 3 requests.
  @Test
  public void testBackPressureBuffer() {
    var numbers = Flux.range(1,100).log();

    numbers
        .onBackpressureBuffer(10,
            i -> System.out.println("Buffered Value = " + i))
        .subscribe(new BaseSubscriber<Integer>() {
          @Override
          protected void hookOnSubscribe(Subscription subscription) {
            request(3);
          }

          @Override
          protected void hookOnNext(Integer value) {
            System.out.println("value = " + value);
            if(value ==3) hookOnCancel();
          }

          @Override
          protected void hookOnComplete() {
            System.out.println("Completed!!");
          }

          @Override
          protected void hookOnError(Throwable throwable) {
            super.hookOnError(throwable);
          }

          @Override
          protected void hookOnCancel() {
            super.hookOnCancel();
          }
        });
  }

  // Tests backpressure error handling by throwing an error when requests are not able to keep up with the emitted elements.
  @Test
  public void testBackPressureError() {
    var numbers = Flux.range(1,100).log();

    numbers
        .onBackpressureError()
        .subscribe(new BaseSubscriber<Integer>() {
          @Override
          protected void hookOnSubscribe(Subscription subscription) {
            request(3);
          }

          @Override
          protected void hookOnNext(Integer value) {
            System.out.println("value = " + value);
            if(value ==3) hookOnCancel();
          }

          @Override
          protected void hookOnComplete() {
            System.out.println("Completed!!");
          }

          @Override
          protected void hookOnError(Throwable throwable) {
            System.out.println("throwable = " + throwable);
          }

          @Override
          protected void hookOnCancel() {
            super.hookOnCancel();
          }
        });
  }
}
