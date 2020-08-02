package ru.live.olya.library.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.live.olya.library.entity.Author;
import ru.live.olya.library.model.BookList;
import ru.live.olya.library.repository.AuthorRepository;
import ru.live.olya.library.repository.BookRepository;
import ru.live.olya.library.repository.BookSpecification;

import java.util.Optional;

@RestController
public class LibraryController {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public LibraryController(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @GetMapping("/authors/{author_id}/books")
    public BookList authorBooks(@PathVariable Long author_id) throws Exception {
        Author author = authorRepository.findById(author_id).orElseThrow(() -> new Exception());

        BookSpecification spec = new BookSpecification(author);
        return new BookList(bookRepository.findAll(spec));
    }

}
