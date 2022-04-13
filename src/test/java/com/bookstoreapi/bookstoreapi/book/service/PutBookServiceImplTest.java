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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class PutBookServiceImplTest {
//
//    @InjectMocks
//    private PutBookServiceImpl putBookService;
//    @Mock
//    private BookRepository repository;
//    @Mock
//    private BookService service;
//    private Book book;
//
//    @BeforeEach
//    void setUp(){
//        Book bookOld = new Book();
//        bookOld.setId(1L);
//        bookOld.setTitle("old");
//        bookOld.setIsbn("oldold");
//        this.book = bookOld;
//    }
//
//    @Test
//    void updateTest(){
//        BookDTO bookUpdated = new BookDTO();
//        bookUpdated.setIsbn("new new");
//        bookUpdated.setTitle("updated");
//
//        when(service.findById(anyLong())).thenReturn(book);
//        when(repository.save(any())).thenReturn(bookUpdated);
//
//        assertThat("updated", is(equalTo
//                (putBookService.update(1L, bookUpdated).getTitle())));
//        assertInstanceOf(BookDTO.class, putBookService.update(1L, bookUpdated));
//    }
}