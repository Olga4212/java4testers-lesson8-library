package ru.live.olya.library.model;

import ru.live.olya.library.entity.Author;

import java.io.Serializable;
import java.util.List;

public class AuthorList implements Serializable {

    List<Author> values;

    public AuthorList(List<Author> values) {
        this.values = values;
    }

    public AuthorList() {
    }

    public List<Author> getValues() {
        return values;
    }

    public void setValues(List<Author> values) {
        this.values = values;
    }
}
