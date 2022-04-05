package com.bookstoreapi.bookstoreapi.book.service;

import com.bookstoreapi.bookstoreapi.book.Book;
import com.bookstoreapi.bookstoreapi.book.BookRepository;
import com.bookstoreapi.bookstoreapi.categories.Category;
import com.bookstoreapi.bookstoreapi.categories.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class BookServiceTest {

    @InjectMocks
    private BookService bookService;
    @Mock
    private BookRepository repository;
    @Mock
    private CategoryService categoryService;
    private final HashMap<Long, Category> categories = new HashMap<>();

    @BeforeEach
    void setUp(){
        Category romance = new Category(1L, "Romance");
        Category action = new Category(2L, "Action");
        categories.put(1L, romance);
        categories.put(2L, action);
    }

    @Test
    void findByIdWhenIdExistTest(){
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Rocky IV");
        when(repository.findById(anyLong())).thenReturn(Optional.of(book));

        Book bookSaved = bookService.findById(1L);
        assertThat("Rocky IV", is(bookSaved.getTitle()));
    }

    @Test
    void findByIdWhenIdDontExist(){
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, ()-> bookService.findById(1L));
    }

    @Test
    void getCategoriesTest(){
        when(categoryService.findById(1L)).thenReturn(categories.get(1L));
        when(categoryService.findById(2L)).thenReturn(categories.get(2L));

        Category category1 = new Category();
        category1.setId(1L);
        Category category2 = new Category();
        category2.setId(2L);
        List<Category> listToFind = new ArrayList<>();
        listToFind.add(category1);
        listToFind.add(category2);

        List<Category> founds = bookService.getCategories(listToFind);
        assertThat(2, is(equalTo(founds.size())));
        for(Category found: founds){
            Category categorySaved = categories.get(found.getId());
            assertThat(categorySaved.getName(), is(equalTo(found.getName())));
        }
    }

    @Test
    void saveAllTest(){
        Book bookSaved1 = new Book();
        bookSaved1.setId(1L);
        bookSaved1.setTitle("book 1");
        Book bookSaved2 = new Book();
        bookSaved1.setId(2L);
        bookSaved1.setTitle("book 2");

        List<Book> books = new ArrayList<>();
        books.add(bookSaved1);
        books.add(bookSaved2);
        when(repository.saveAll(anyCollection())).thenReturn(books);

        Book book1 = new Book();
        book1.setTitle("book 1");
        Book book2 = new Book();
        book2.setTitle("book 2");
        List<Book> toSave = new ArrayList<>();
        toSave.add(book1);
        toSave.add(book2);

        assertThat(2, is(equalTo(bookService.saveAll(toSave).size())));
    }
}