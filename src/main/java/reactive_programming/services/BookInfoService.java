package reactive_programming.services;

import java.util.List;
import reactive_programming.domain.BookInfo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class BookInfoService {

  public Flux<BookInfo> getBooks() {
    var books = List.of(
        new BookInfo(1, "Book One", "Author One"),
        new BookInfo(2, "Book Two", "Author Two"),
        new BookInfo(3, "Book Three", "Author Three")
    );

    return Flux.fromIterable(books);
  }

  public Mono<BookInfo> getBookById(long bookId) {
    var book = new BookInfo(bookId, "Book One", "Author One");
    return Mono.just(book);
  }

}
