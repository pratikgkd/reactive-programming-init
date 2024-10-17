package reactive_programming.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

class BookServiceTest {

  private final BookInfoService bookInfoService = new BookInfoService();
  private final ReviewService reviewService = new ReviewService();
  private final BookService bookService = new BookService(bookInfoService, reviewService);

  @Test
  void getBooks() {
    var books = bookService.getBooks();
    StepVerifier.create(books)
        .assertNext(book -> {
          assertEquals("Book One", book.getBookInfo().getTitle());
          assertEquals(2, book.getReviews().size());
        })
        .assertNext(book -> {
          assertEquals("Book Two", book.getBookInfo().getTitle());
          assertEquals(2, book.getReviews().size());
        })
        .assertNext(book -> {
          assertEquals("Book Three", book.getBookInfo().getTitle());
          assertEquals(2, book.getReviews().size());
        })
        .verifyComplete();
  }

  @Test
  void getBookById() {
    var book = bookService.getBookById(1).log();
    StepVerifier.create(book)
        .assertNext(b -> {
          assertEquals("Book One", b.getBookInfo().getTitle());
          assertEquals(2, b.getReviews().size());
        })
        .verifyComplete();
  }
}