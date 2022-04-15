package com.bookstoreapi.bookstoreapi.book.service;

import com.bookstoreapi.bookstoreapi.book.Book;
import com.bookstoreapi.bookstoreapi.book.BookRepository;
import com.bookstoreapi.bookstoreapi.builders.BookBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class GetAllByCategoryNameBookServiceImplTest {


    private GetAllByCategoryNameBookServiceImpl getAllByCategoryNameBookService;
    @Mock
    private BookRepository bookRepository;


    @BeforeEach
    void setUp(){
        this.getAllByCategoryNameBookService = new GetAllByCategoryNameBookServiceImpl(bookRepository);
    }

    @Test
    void findAllTest(){
        when(bookRepository.findAllByCategoriesNameIgnoreCase("Horror")).thenReturn(BookBuilder.bookList());

        List<Book> books = getAllByCategoryNameBookService.findAllByCategoriesName("Horror");

        assertThat(3, is(books.size()));
        verify(bookRepository, times(1)).findAllByCategoriesNameIgnoreCase("Horror");

        assertThat(1L, is(books.get(0).getId()));
        assertThat(3, is(books.get(0).getCategories().size()));
        assertThat("JavaScript", is(books.get(0).getTitle()));
        assertThat("Aprenda JavaScript", is(books.get(0).getSynopsis()));
        assertThat("1111111111111", is(books.get(0).getIsbn()));
        assertThat(new Date(14032001), is(books.get(0).getPublicationYear()));
        assertThat(50.00, is(books.get(0).getPrice()));
        assertThat(23, is(books.get(0).getQuantityInStock()));
        assertThat("JN Papo", is(books.get(0).getAuthorName()));

        assertThat(2L, is(books.get(1).getId()));
        assertThat(3, is(books.get(1).getCategories().size()));
        assertThat("Angular JS", is(books.get(1).getTitle()));
        assertThat("Aprenda a primeira versão do Angular", is(books.get(1).getSynopsis()));
        assertThat("2222222222222", is(books.get(1).getIsbn()));
        assertThat(new Date(15042000), is(books.get(1).getPublicationYear()));
        assertThat(80.00, is(books.get(1).getPrice()));
        assertThat(4, is(books.get(1).getQuantityInStock()));
        assertThat("Gu Gou", is(books.get(1).getAuthorName()));

        assertThat(3L, is(books.get(2).getId()));
        assertThat(3, is(books.get(2).getCategories().size()));
        assertThat("Algoritmos", is(books.get(2).getTitle()));
        assertThat("Entenda lógica de programação", is(books.get(2).getSynopsis()));
        assertThat("3333333333333", is(books.get(2).getIsbn()));
        assertThat(new Date(30042000), is(books.get(2).getPublicationYear()));
        assertThat(100.00, is(books.get(2).getPrice()));
        assertThat(23, is(books.get(2).getQuantityInStock()));
        assertThat("JN Papo", is(books.get(2).getAuthorName()));
    }
}