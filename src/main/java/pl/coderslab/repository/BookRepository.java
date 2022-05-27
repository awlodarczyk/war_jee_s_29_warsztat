package pl.coderslab.repository;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Repository;
import pl.coderslab.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BookRepository {

    private List<Book> list;
    private Faker faker;

    public BookRepository() {
        this.faker = new Faker();
        this.list = prepareFakeData();
    }

    private List<Book> prepareFakeData() {
        List<Book> bookList = new ArrayList<>();
        for (int i = 1; i < 20; i++) {
            Book book = new Book();
            book.setId((long)i);
            book.setTitle(faker.book().title());
            book.setPublisher(faker.book().publisher());
            book.setGenre(faker.book().genre());
            book.setIsbn(faker.random().hex());
            bookList.add(book);
        }
        return bookList;
    }

    public List<Book> findAll() {
        return list;
    }

    public Book findById(Long id) {
        return list.stream().filter(it-> it.getId().equals(id)).findFirst().orElse(null);
    }

    public Book create(Book book) {
        book.setId((long)list.size()+1);
        list.add(book);
        return book;
    }

    public Book update(Book book) {
        list = list.stream().peek(it->{
            if(it.getId().equals(book.getId())) {
                it.setTitle(book.getTitle());
                it.setPublisher(book.getPublisher());
                it.setGenre(book.getGenre());
                it.setIsbn(book.getIsbn());
                it.setAuthor(book.getAuthor());
            }
        }).collect(Collectors.toList());
        return findById(book.getId());
    }

    public void delete(Long id) {
        list.removeIf(it->it.getId().equals(id));
    }
}
