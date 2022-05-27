package pl.coderslab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.model.Book;
import pl.coderslab.model.JsonResponse;
import pl.coderslab.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService service;


    @RequestMapping("/helloBook")
    public Book helloBook() {
        return new Book(1L, "9788324631766", "Thinking in Java",
                "Bruce Eckel", "Helion", "programming");
    }
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Book> findAll(){
        return service.findAll();
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Book findById(@PathVariable Long id){
        return service.findById(id).orElse(null);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Book create(@RequestBody Book book){
        return service.create(book);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Book update(@PathVariable Long id, @RequestBody Book book){
        return service.update(id, book);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public JsonResponse delete(@PathVariable Long id){
        if(service.delete(id)){
            return new JsonResponse(HttpStatus.OK,"book removed successfully");
        }else {
            return new JsonResponse(HttpStatus.NOT_FOUND,"book not found in database");
        }
    }


}
