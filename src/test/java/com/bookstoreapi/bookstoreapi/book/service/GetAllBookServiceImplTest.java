package com.bookstoreapi.bookstoreapi.book.service;

import com.bookstoreapi.bookstoreapi.book.Book;
import com.bookstoreapi.bookstoreapi.book.BookDTO;
import com.bookstoreapi.bookstoreapi.book.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class GetAllBookServiceImplTest {

    @InjectMocks
    private GetAllBookServiceImpl getAllBookService;
    @Mock
    private BookRepository bookRepository;
    private final List<Book> allBooks = new LinkedList<>();


    @BeforeEach
    void setUp(){
        Book book1 = new Book();
        book1.setId(1L);
        book1.setTitle("Book 1");

        Book book2 = new Book();
        book2.setId(2L);
        book2.setTitle("Book 2");

        Book book3 = new Book();
        book3.setId(3L);
        book3.setTitle("Book 3");

        allBooks.add(book1);
        allBooks.add(book2);
        allBooks.add(book3);
    }

    @Test
    void findAllTest(){
        when(bookRepository.findAll()).thenReturn(allBooks);
        List<BookDTO> listReturned = getAllBookService.findAll();
        assertThat(3, is(equalTo(listReturned.size())));
        for(int k = 0; k<3; k++){
            assertThat(allBooks.get(k).getTitle(), is(equalTo(listReturned.get(k).getTitle())));
        }
    }
}