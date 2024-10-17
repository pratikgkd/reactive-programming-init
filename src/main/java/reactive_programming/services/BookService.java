package reactive_programming.services;

import java.time.Duration;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import reactive_programming.domain.Book;
import reactive_programming.domain.Review;
import reactive_programming.exception.BookException;
import reactor.core.Exceptions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;
import reactor.util.retry.RetryBackoffSpec;

@Slf4j
public class BookService {

  private final BookInfoService bookInfoService;
  private final ReviewService reviewService;

  public BookService(BookInfoService bookInfoService, ReviewService reviewService) {
    this.bookInfoService = bookInfoService;
    this.reviewService = reviewService;
  }

  public Flux<Book> getBooks() {
    var allBooksInfo = bookInfoService.getBooks();
    return allBooksInfo
        .flatMap(bookInfo -> {
          Mono<List<Review>> reviews =
              reviewService.getReviews(bookInfo.getId()).collectList();
          return reviews
              .map(review -> new Book(bookInfo, review));
        })
        .onErrorMap(throwable -> {
          log.error("Exception occured :" + throwable);
          return new BookException("Exception occurred while fetching Books");
        })
        .log();
  }

  public Flux<Book> getBooksRetry() {
    var allBooks = bookInfoService.getBooks();
    return allBooks
        .flatMap(bookInfo -> {
          Mono<List<Review>> reviews =
              reviewService.getReviews(bookInfo.getId()).collectList();
          return reviews
              .map(review -> new Book(bookInfo, review));
        })
        .onErrorMap(throwable -> {
          log.error("Exception is :" + throwable);
          return new BookException("Exception occurred while fetching books with retry");
        })
        .retry(3)
        .log();
  }

  public Flux<Book> getBooksRetryWhen() {
    var allBooks = bookInfoService.getBooks();
    return allBooks
        .flatMap(bookInfo -> {
          Mono<List<Review>> reviews =
              reviewService.getReviews(bookInfo.getId()).collectList();
          return reviews
              .map(review -> new Book(bookInfo, review));
        })
        .onErrorMap(throwable -> {
          log.error("Exception:" + throwable);
          return new BookException("Exception occurred while fetching Books with retry when");
        })
        .retryWhen(getRetryBackoffSpec())
        .log();
  }

  /**
   * Creates a RetryBackoffSpec with 3 retries and a 1000ms backoff, filtering for BookException
   * (useful if only for a particular exception code is to be done) Propagates the failure when
   * retries are exhausted.
   */
  private RetryBackoffSpec getRetryBackoffSpec() {
    return Retry.backoff(
            3,
            Duration.ofMillis(1000)
        ).filter(BookException.class::isInstance)
        .onRetryExhaustedThrow((retryBackoffSpec, retrySignal) ->
            Exceptions.propagate(retrySignal.failure())
        );
  }

  public Mono<Book> getBookById(long bookId) {
    var book = bookInfoService.getBookById(bookId);
    var review = reviewService
        .getReviews(bookId)
        .collectList();

    return book
        .zipWith(review, Book::new);

  }

}
