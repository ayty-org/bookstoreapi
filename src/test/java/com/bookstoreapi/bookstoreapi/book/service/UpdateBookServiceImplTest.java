package com.bookstoreapi.bookstoreapi.book.service;

import com.bookstoreapi.bookstoreapi.book.Book;
import com.bookstoreapi.bookstoreapi.book.BookRepository;
import com.bookstoreapi.bookstoreapi.builders.BookBuilder;
import com.bookstoreapi.bookstoreapi.builders.CategoryBuilder;
import com.bookstoreapi.bookstoreapi.categories.CategoryRepository;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class UpdateBookServiceImplTest {

    private UpdateBookServiceImpl updateBookService;
    @Mock
    private BookRepository repository;
    @Mock
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp(){
        this.updateBookService = new UpdateBookServiceImpl(repository);
    }

    @Test
    void updateTest() throws Exception{
        when(categoryRepository.existsById(anyLong())).thenReturn(true);
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(CategoryBuilder.categoryRomance()));
        when(categoryRepository.findById(2L)).thenReturn(Optional.of(CategoryBuilder.categoryComedy()));
        when(categoryRepository.findById(3L)).thenReturn(Optional.of(CategoryBuilder.categoryAdventure()));
        when(repository.save(any())).thenReturn(BookBuilder.book1L());
        when(repository.existsById(1L)).thenReturn(true);

        Book book = updateBookService.update(null, BookBuilder.book1L());

        verify(categoryRepository, times(3)).existsById(anyLong());
        verify(categoryRepository, times(3)).findById(anyLong());
        verify(repository, times(1)).save(any());

        assertThat(1L, is(book.getId()));
        assertThat(3, is(book.getCategories().size()));
        assertThat("JavaScript", is(book.getTitle()));
        assertThat("Aprenda JavaScript", is(book.getSynopsis()));
        assertThat("9788533302273", is(book.getIsbn()));
        assertThat(new Date(14032001), is(book.getPublicationYear()));
        assertThat(50.00, is(book.getPrice()));
        assertThat(23, is(book.getQuantityInStock()));
        assertThat("JN Papo", is(book.getAuthorName()));
    }

    @Test
    void updateWhenIdDontExist() throws Exception{
        when(repository.existsById(1L)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, ()->updateBookService.update(null, BookBuilder.book1L()));
        verify(categoryRepository, times(0)).existsById(anyLong());
        verify(repository, never()).save(any());
    }

    @Test
    void updateWhenCategoryDontExist() throws Exception{
        when(repository.existsById(1L)).thenReturn(true);
        when(categoryRepository.existsById(1L)).thenReturn(true);
        when(categoryRepository.existsById(2L)).thenReturn(false);
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(CategoryBuilder.categoryRomance()));

        assertThrows(EntityNotFoundException.class, ()-> updateBookService.update(null, BookBuilder.book1L()));
        verify(categoryRepository, times(2)).existsById(anyLong());
        verify(categoryRepository, times(1)).findById(anyLong());
        verify(repository, never()).save(any());
    }
}