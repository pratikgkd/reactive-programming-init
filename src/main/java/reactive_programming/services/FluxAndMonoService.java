package reactive_programming.services;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FluxAndMonoService {

  public Flux<String> fluxOfSports() {
    return Flux.fromIterable(List.of("Cricket", "Chess", "Football", "Hockey")).log();
  }

  public Mono<String> monoOfSports() {
    return Mono.just("Football").log();
  }

  public Flux<String> fluxOfSportsWithMap() {
    return Flux.fromIterable(List.of("Cricket", "Chess", "Football", "Hockey"))
        .map(String::toUpperCase);
  }

  public Flux<String> fluxOfSportsWithFilter(int length) {
    return Flux.fromIterable(List.of("Cricket", "Chess", "Football", "Hockey"))
        .filter(x -> x.length() == length);
  }

  /**
   * when inner returns a flux and not single item use flatmap to flatten it
   *
   * @return
   */
  public Flux<String> fluxOfSportsWithFlatmap() {
    return Flux.fromIterable(List.of("Cricket", "Chess", "Football", "Hockey"))
        .flatMap(x -> Flux.just(x.split("")))
        .log();
  }

  /**
   * order is not preserved when done async
   *
   * @return
   */
  public Flux<String> fluxOfSportsWithFlatmapAsync() {
    return Flux.fromIterable(List.of("Cricket", "Chess", "Football", "Hockey"))
        .flatMap(x -> Flux.just(x.split("")))
        .delayElements(
            Duration.ofMillis(new Random().nextInt(1000)))
        .log();
  }

  /**
   * same as flatmap but preserves order
   *
   * @return
   */
  public Flux<String> fluxOfSportsWithConcatmap() {
    return Flux.fromIterable(List.of("Cricket", "Chess", "Football", "Hockey"))
        .concatMap(x -> Flux.just(x.split("")))
        .delayElements(
            Duration.ofMillis(new Random().nextInt(1000)))
        .log();
  }

  public Flux<String> fluxOfSportsWithDefaultEmpty(int length) {
    return Flux.fromIterable(List.of("Cricket", "Chess", "Football", "Hockey"))
        .filter(x -> x.length() == length)
        .defaultIfEmpty("Default value sent since empty")
        .log();
  }

  public Flux<String> fluxOfSportsWithSwitchIfEmpty(int length) {
    return Flux.fromIterable(List.of("Cricket", "Chess", "Football", "Hockey"))
        .filter(x -> x.length() == length)
        .switchIfEmpty(Flux.just("Dancing"))
        .log();
  }

  /* concat: 'sequentially' combines multiple publishers (streams) into one, emitting elements from one after the previous completes.
   * concatWith: perform chaining with another publisher.
   * merge: combines multiple publishers 'concurrently', interleaving their emissions as they arrive
   * mergeWith: mergeWith is the method form of `merge`, used to merge another publisher with the current one
   * mergeSequential: merge with sequence maintained
   */


  // doOn...  : sideEffect or extra functionality at various lifecycle stages of a reactive stream; 'without modifying the actual data flow.'
  public Flux<String> fluxOfSportsWithDoOn(int length) {
    return Flux.fromIterable(List.of("Cricket", "Chess", "Football", "Hockey"))
        .filter(x -> x.length() == length)
        .doOnNext(s -> System.out.println("In doOnNext :: Element is:" + s))
        .doOnSubscribe(subscription -> {
          System.out.println("Subscription object :: " + subscription.toString());
        })
        .doOnComplete(() -> System.out.println("Completed"))
        .log();
  }

  // Exception handling //
  public Flux<String> fluxOfSportsWithOnErrorReturn() {
    return Flux.just("Cricket", "Chess")
        .concatWith(Flux.error(new RuntimeException("Runtime exception thrown")))
        .onErrorReturn("Football");
  }

  public Flux<String> fluxOfSportsWithOnErrorContinue() {
    return Flux.just("Cricket", "Chess", "Football")
        .map(sport -> {
          if (sport.equalsIgnoreCase("CrickET")) {
            throw new RuntimeException("Runtime exception thrown");
          }
          return sport.toUpperCase();
        })
        .onErrorContinue((e, f) -> {
          System.out.println("throwable :: " + e);
          System.out.println("object :: " + f);
        });
  }

  public static void main(String[] args) {
    FluxAndMonoService service = new FluxAndMonoService();
    System.out.println(":::: Printing Flux ::::");
    service.fluxOfSports().subscribe(System.out::println);

    System.out.println(":::: Printing Flux with map operator() ::::");
    service.fluxOfSportsWithMap().subscribe(System.out::println);

    System.out.println("\n :::: Printing Mono ::::");
    service.monoOfSports().subscribe(System.out::println);
  }

}
