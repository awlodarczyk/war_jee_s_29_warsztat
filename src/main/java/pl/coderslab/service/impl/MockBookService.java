package pl.coderslab.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.model.Book;
import pl.coderslab.repository.BookRepository;
import pl.coderslab.service.BookService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MockBookService implements BookService {
    private final BookRepository bookRepository;
    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return Optional.ofNullable(bookRepository.findById(id));
    }

    @Override
    public Book create(Book book) {
        if(book.getAuthor().isEmpty() || book.getIsbn().isEmpty() || book.getTitle().isEmpty()){
            throw new RuntimeException("Fill all required properties of book object");
        }
        return bookRepository.create(book);
    }

    @Override
    public Book update(Long id, Book book) {
        if(bookRepository.findById(id)!=null){
            book.setId(id);
            return bookRepository.update(book);
        }
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        if(bookRepository.findById(id)!=null){
            bookRepository.delete(id);
            return true;
        }
        return false;
    }
}
