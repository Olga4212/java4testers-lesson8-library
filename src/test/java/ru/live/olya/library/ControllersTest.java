package ru.live.olya.library;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.live.olya.library.entity.Author;
import ru.live.olya.library.entity.Book;
import ru.live.olya.library.model.BookList;
import ru.live.olya.library.repository.AuthorRepository;
import ru.live.olya.library.repository.BookRepository;

import javax.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ControllersTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    BookRepository bookRepository;

    @Test
    public void testGetAuthorBooks() throws Exception {
        prepareData();

        //System.out.println(bookRepository.findAll().get(0).getAuthor().getBooks());
        //System.out.println(authorRepository.findAll().get(0));

        Author author = authorRepository.findAll().get(0);

        String contentAsString = mockMvc.perform(MockMvcRequestBuilders.get("/authors/" + author.getId() + "/books"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        BookList response = objectMapper.readValue(contentAsString, BookList.class);
        Assertions.assertThat(response.getValues().size())
                .isEqualTo(2);

    }



    protected void prepareData() {
        authorRepository.deleteAll();
        bookRepository.deleteAll();

        Author a1 = new Author();
        a1.setName("Lev Tolstoy");
        authorRepository.save(a1);

        Author a2 = new Author();
        a2.setName("Alex Pushkin");
        authorRepository.save(a2);

        Book b1 = new Book();
        b1.setName("Voyna i mir");
        b1.setAuthor(a1);
        bookRepository.save(b1);

        Book b2 = new Book();
        b2.setName("Anna Karenina");
        b2.setAuthor(a1);
        bookRepository.save(b2);

        Book b3 = new Book();
        b3.setName("Gene Onegin");
        b3.setAuthor(a2);
        bookRepository.save(b3);
    }
}
