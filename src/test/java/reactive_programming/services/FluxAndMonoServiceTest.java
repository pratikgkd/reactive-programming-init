package reactive_programming.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

class FluxAndMonoServiceTest {

  FluxAndMonoService service = new FluxAndMonoService();

  @Test
  @DisplayName("Test flux")
  void testFluxOfSports() {
    StepVerifier.create(service.fluxOfSports())
        .expectNext("Cricket", "Chess", "Football", "Hockey")
        .verifyComplete();
  }

  @Test
  @DisplayName("Test mono")
  void testMonoOfSports() {
    StepVerifier.create(service.monoOfSports())
        .expectNext("Football")
        .verifyComplete();
  }

  @Test
  @DisplayName("Test flux with map")
  void testFluxOfSportsWithMap() {
    StepVerifier.create(service.fluxOfSportsWithMap())
        .expectNext("CRICKET", "CHESS", "FOOTBALL", "HOCKEY")
        .verifyComplete();
  }

  @Test
  @DisplayName("Test flux with filter")
  void testFluxOfSportsWithFilter() {
    StepVerifier.create(service.fluxOfSportsWithFilter(5))
        .expectNext("Chess")
        .verifyComplete();
  }

  @Test
  @DisplayName("Test flux with flatmap")
  void testFluxOfSportsWithFlatmap() {
    StepVerifier.create(service.fluxOfSportsWithFlatmap())
        .expectNextCount(26)
        .verifyComplete();
  }

  @Test
  @DisplayName("Test flux with flatmap async")
  void testFluxOfSportsWithFlatmapAsync() {
    StepVerifier.create(service.fluxOfSportsWithFlatmapAsync().log())
        .expectNextCount(26)
        .verifyComplete();
  }

  @Test
  @DisplayName("Test flux with concat map async")
  void testFluxOfSportsWithConcatmapAsync() {
    StepVerifier.create(service.fluxOfSportsWithConcatmap().log())
        .expectNextCount(26)
        .verifyComplete();
  }

  @Test
  @DisplayName("Test flux with default if empty")
  void testFluxOfSportsWithDefaultEmpty() {
    StepVerifier.create(service.fluxOfSportsWithDefaultEmpty(2).log())
        .expectNext("Default value sent since empty")
        .verifyComplete();
  }

  @Test
  @DisplayName("Test flux with default if empty")
  void testFluxOfSportsWithSwitchIfEmpty() {
    StepVerifier.create(service.fluxOfSportsWithSwitchIfEmpty(2).log())
        .expectNext("Dancing")
        .verifyComplete();
  }

  @Test
  @DisplayName("Test flux with default if empty")
  void testFluxOfSportsWithDoOn() {
    StepVerifier.create(service.fluxOfSportsWithDoOn(5).log())
        .expectNext("Chess")
        .verifyComplete();
  }

  @Test
  @DisplayName("Test flux with onErrorReturn")
  void testFluxOfSportsWithOnErrorReturn() {
    StepVerifier.create(service.fluxOfSportsWithOnErrorReturn().log())
        .expectNext("Cricket", "Chess", "Football")
        .verifyComplete();
  }

  @Test
  @DisplayName("Test flux with onErrorReturn")
  void testFluxOfSportsWithOnErrorContinue() {
    StepVerifier.create(service.fluxOfSportsWithOnErrorContinue().log())
        .expectNext("CHESS", "FOOTBALL")
        .verifyComplete();
  }

}