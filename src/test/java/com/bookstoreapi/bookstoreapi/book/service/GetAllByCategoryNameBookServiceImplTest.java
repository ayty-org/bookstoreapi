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

        assertThat(books.get(0).getId(), is(1L));
        assertThat(books.get(0).getUuid().toString(), is("12d51c0a-a843-46fc-8447-5fda559ec69b"));
        assertThat(books.get(0).getCategories().size(), is(3));
        assertThat(books.get(0).getTitle(), is("JavaScript"));
        assertThat(books.get(0).getSynopsis(), is("Aprenda JavaScript"));
        assertThat(books.get(0).getIsbn(), is("9788533302273"));
        assertThat(books.get(0).getPublicationYear(), is(new Date(14032001)));
        assertThat(books.get(0).getPrice(), is(50.00));
        assertThat(books.get(0).getQuantityInStock(), is(23));
        assertThat(books.get(0).getAuthorName(), is("JN Papo"));

        assertThat(books.get(1).getId(), is(2L));
        assertThat(books.get(1).getUuid().toString(), is("df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14"));
        assertThat(books.get(1).getCategories().size(), is(3));
        assertThat(books.get(1).getTitle(), is("Angular JS"));
        assertThat(books.get(1).getSynopsis(), is("Aprenda a primeira versão do Angular"));
        assertThat(books.get(1).getIsbn(), is("9788533302273"));
        assertThat(books.get(1).getPublicationYear(), is(new Date(15042000)));
        assertThat(books.get(1).getPrice(), is(80.00));
        assertThat(books.get(1).getQuantityInStock(), is(4));
        assertThat(books.get(1).getAuthorName(), is("Gu Gou"));

        assertThat(books.get(2).getId(), is(3L));
        assertThat(books.get(2).getUuid().toString(), is("27eaa649-e8fa-4889-bd5a-ea6825b71e61"));
        assertThat(books.get(2).getCategories().size(), is(3));
        assertThat(books.get(2).getTitle(), is("Algoritmos"));
        assertThat(books.get(2).getSynopsis(), is("Entenda lógica de programação"));
        assertThat(books.get(2).getIsbn(), is("9788533302273"));
        assertThat(books.get(2).getPublicationYear(), is(new Date(30042000)));
        assertThat(books.get(2).getPrice(), is(100.00));
        assertThat(books.get(2).getQuantityInStock(), is(23));
        assertThat(books.get(2).getAuthorName(), is("JN Papo"));
    }
}