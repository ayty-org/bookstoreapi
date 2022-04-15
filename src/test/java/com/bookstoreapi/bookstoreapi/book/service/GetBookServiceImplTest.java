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
    void setUp(){
        this.getBookService = new GetBookServiceImpl(repository);
    }

    @Test
    void testGetByIdWhenIdExist(){
        when(repository.findById(1L)).thenReturn(Optional.of(BookBuilder.book1L()));

        Book book = getBookService.findById(1L);

        verify(repository, times(1)).findById(1L);
        assertThat(1L, is(book.getId()));
        assertThat(3, is(book.getCategories().size()));
        assertThat("JavaScript", is(book.getTitle()));
        assertThat("Aprenda JavaScript", is(book.getSynopsis()));
        assertThat("1111111111111", is(book.getIsbn()));
        assertThat(new Date(14032001), is(book.getPublicationYear()));
        assertThat(50.00, is(book.getPrice()));
        assertThat(23, is(book.getQuantityInStock()));
        assertThat("JN Papo", is(book.getAuthorName()));
    }

    @Test
    void testGetByIdWhenIdDontExist(){
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, ()-> getBookService.findById(3L));
    }
}