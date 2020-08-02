package ru.live.olya.library.repository;

import org.springframework.data.jpa.domain.Specification;
import ru.live.olya.library.entity.Author;
import ru.live.olya.library.entity.Book;
import ru.live.olya.library.entity.Reader;

import javax.persistence.criteria.*;

public class BookSpecification implements Specification<Book> {

    private Author author;

    public BookSpecification(Author author) {
        this.author = author;
    }

    @Override
    public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.equal(root.get("author_id"), this.author.getId());
    }
}
