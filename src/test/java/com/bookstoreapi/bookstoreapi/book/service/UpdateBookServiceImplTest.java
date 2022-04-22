package com.bookstoreapi.bookstoreapi.book.service;

import com.bookstoreapi.bookstoreapi.book.Book;
import com.bookstoreapi.bookstoreapi.book.BookRepository;
import com.bookstoreapi.bookstoreapi.builders.BookBuilder;
import com.bookstoreapi.bookstoreapi.builders.CategoryBuilder;
import com.bookstoreapi.bookstoreapi.categories.CategoryRepository;
import com.bookstoreapi.bookstoreapi.exception.CategoryNotFoundException;
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
    void setUp() {
        this.updateBookService = new UpdateBookServiceImpl(repository, categoryRepository);
    }

    @Test
    void updateTest() throws Exception {
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(CategoryBuilder.categoryRomance()));
        when(categoryRepository.findById(2L)).thenReturn(Optional.of(CategoryBuilder.categoryComedy()));
        when(categoryRepository.findById(3L)).thenReturn(Optional.of(CategoryBuilder.categoryAdventure()));
        when(repository.save(any())).thenReturn(BookBuilder.book1L());
        when(repository.findByUuid(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b")))
                .thenReturn(Optional.of(BookBuilder.book1L()));

        Book book = updateBookService.update(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b"),
                BookBuilder.book1L());

        verify(categoryRepository, times(3)).findById(anyLong());
        verify(repository, times(1)).save(any());

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
    void updateWhenIdDontExist() {
        when(repository.findByUuid(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b")))
                .thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> updateBookService.update(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b"),
                        BookBuilder.book1L()));
        verify(categoryRepository, times(0)).existsById(anyLong());
        verify(repository, never()).save(any());
    }

    @Test
    void updateWhenCategoryDontExist() {
        when(repository.findByUuid(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b")))
                .thenReturn(Optional.of(BookBuilder.book1L()));

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(CategoryBuilder.categoryRomance()));
        when(categoryRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> updateBookService.update(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b"),
                BookBuilder.book1L()));
        verify(categoryRepository, times(2)).findById(anyLong());
        verify(repository, never()).save(any());
    }
}