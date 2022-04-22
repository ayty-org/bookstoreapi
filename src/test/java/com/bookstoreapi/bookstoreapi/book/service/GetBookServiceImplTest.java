package com.bookstoreapi.bookstoreapi.book.service;

import com.bookstoreapi.bookstoreapi.book.Book;
import com.bookstoreapi.bookstoreapi.book.BookRepository;
import com.bookstoreapi.bookstoreapi.builders.BookBuilder;
import com.bookstoreapi.bookstoreapi.exception.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class GetBookServiceImplTest {

    private GetBookServiceImpl getBookService;
    @Mock
    private BookRepository repository;


    @BeforeEach
    void setUp() {
        this.getBookService = new GetBookServiceImpl(repository);
    }

    @Test
    void testGetByIdWhenIdExist() throws Exception {
        when(repository.findByUuid(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b")))
                .thenReturn(Optional.of(BookBuilder.book1L()));

        Book book = getBookService.getByUuid(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b"));

        verify(repository, times(1)).findByUuid(any());
        assertThat(book.getId(), is(1L));
        assertThat(book.getUuid().toString(), is("12d51c0a-a843-46fc-8447-5fda559ec69b"));
        assertThat(book.getCategories().size(), is(3));
        assertThat(book.getTitle(), is("JavaScript"));
        assertThat(book.getSynopsis(), is("Aprenda JavaScript"));
        assertThat(book.getIsbn(), is("9788533302273"));
        assertThat(book.getPublicationYear(), is(new Date(14032001)));
        assertThat(book.getPrice(), is(50.00));
        assertThat(book.getQuantityInStock(), is(23));
        assertThat(book.getAuthorName(), is("JN Papo"));
    }

    @Test
    void testGetByIdWhenIdDontExist() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> getBookService.getByUuid(null));
    }
}