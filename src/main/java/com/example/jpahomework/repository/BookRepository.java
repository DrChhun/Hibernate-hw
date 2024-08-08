package com.example.jpahomework.repository;

import com.example.jpahomework.models.Book;
import com.example.jpahomework.models.BookRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@AllArgsConstructor
@Transactional
public class BookRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<?> findAllBooks() {
        TypedQuery<?> data = entityManager.createQuery("SELECT b FROM Book b", Book.class);
        return data.getResultList();
    }

    public Book insertBook(BookRequest body) {
        Book newBook = Book.builder()
                .id(null)
                .title(body.getTitle())
                .description(body.getDescription())
                .publicationYear(body.getPublicationYear())
                .author(body.getAuthor())
                .build();
        entityManager.persist(newBook);
        return newBook;
    }

    public Book findBookById(UUID id) {
        return entityManager.find(Book.class, id);
    }

    public List<Book> findBookByTitle(String title) {
        TypedQuery<Book> data = entityManager.createQuery("SELECT b FROM Book b WHERE LOWER(b.title) ilike LOWER(:title) ", Book.class);
        data.setParameter("title", "%" + title.toLowerCase() + "%");
        return data.getResultList();
    }

    public Book deleteBookById(UUID id) {
        Book getBook = entityManager.find(Book.class, id);
        entityManager.remove(getBook);
        return getBook;
    }

    public Object updateBookById(UUID id, BookRequest body) {
        Book getBook = entityManager.find(Book.class, id);
        entityManager.detach(getBook);
        getBook.setAuthor(body.getAuthor());
        getBook.setTitle(body.getTitle());
        getBook.setDescription(body.getDescription());
        getBook.setPublicationYear(body.getPublicationYear());
        entityManager.merge(getBook);
        return getBook;
    }
}
