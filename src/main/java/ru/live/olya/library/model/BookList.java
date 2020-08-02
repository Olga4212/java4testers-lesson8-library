package ru.live.olya.library.model;

import ru.live.olya.library.entity.Book;
import ru.live.olya.library.entity.Reader;

import java.io.Serializable;
import java.util.List;

public class BookList implements Serializable {

    List<Book> values;

    public BookList(List<Book> values) {
        this.values = values;
    }

    public BookList() {
    }

    public List<Book> getValues() {
        return values;
    }

    public void setValues(List<Book> values) {
        this.values = values;
    }
}
