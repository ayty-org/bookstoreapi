package com.bookstoreapi.bookstoreapi.book;

import com.bookstoreapi.bookstoreapi.book.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private GetAllBookService getAllBookService;
    @MockBean
    private GetBookService getBookService;
    @MockBean
    private GetAllByCategoryNameBookService getAllByCategoryNameBookService;
    @MockBean
    private PostBookService postBookService;
    @MockBean
    private PutBookService putBookService;
    @MockBean
    private DeleteBookService deleteBookService;


    ObjectMapper mapper = new ObjectMapper();

    private final List<BookDTO> books = new ArrayList<>();


    @BeforeEach
    void setUp(){
        BookDTO book1 = new BookDTO();
        book1.setTitle("Book 1");
        book1.setSynopsis("Synopsis test");
        book1.setPublicationYear(new Date());

        BookDTO book2 = new BookDTO();
        book2.setTitle("Book 2");
        book2.setSynopsis("Synopsis!!! 2");
        book2.setPublicationYear(new Date());
        books.add(book1);
        books.add(book2);
    }

    @Test
    void listTest() throws Exception {
        when(getAllBookService.findAll()).thenReturn(books);
        mockMvc.perform(get("/books"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$[0].title", is("Book 1")))
                .andExpect(jsonPath("$[0].synopsis", is("Synopsis test")))
                .andExpect(jsonPath("$[1].title", is("Book 2")))
                .andExpect(jsonPath("$[1].synopsis", is("Synopsis!!! 2")));
    }

    @Test
    void listByCategoryTest() throws Exception{
        when(getAllByCategoryNameBookService.findAllByCategoriesName("Test")).thenReturn(books);
        mockMvc.perform(get("/books/categories/Test"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$[0].title", is("Book 1")))
                .andExpect(jsonPath("$[0].synopsis", is("Synopsis test")))
                .andExpect(jsonPath("$[1].title", is("Book 2")))
                .andExpect(jsonPath("$[1].synopsis", is("Synopsis!!! 2")));
    }

    @Test
    void findWhenIdExistTest() throws Exception{
        when(getBookService.findById(1L)).thenReturn(books.get(0));
        mockMvc.perform(get("/books/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.title", is("Book 1")))
                .andExpect(jsonPath("$.synopsis", is("Synopsis test")));
    }

    @Test
    void findWhenIdDontExist() throws Exception{
        when(getBookService.findById(3L)).thenThrow(EntityNotFoundException.class);
        mockMvc.perform(get("/books/3"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void saveTest() throws Exception{
        BookDTO bookDTO = new BookDTO("new book","synopsis", "9788533302273", new Date(), 40.99,
                4, "John", new ArrayList<>());
        when(postBookService.save(any())).thenReturn(bookDTO);
        String json = mapper.writeValueAsString(bookDTO);
        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("$.title", is("new book")))
                .andExpect(jsonPath("$.synopsis", is("synopsis")))
                .andExpect(jsonPath("$.isbn", is("9788533302273")));
    }

    @Test
    void saveWhenBodyIsInvalid() throws Exception{
        BookDTO bookDTO = new BookDTO("new book","synopsis", "9788533302273", new Date(), 40.99,
                4, null, new ArrayList<>());
        String json = mapper.writeValueAsString(bookDTO);
        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("$", is("author name cannot be null or void")));
    }

    @Test
    void updateTest() throws Exception{
        BookDTO bookDTO = new BookDTO("new book","synopsis", "9788533302273", new Date(), 40.99,
                4, "test", new ArrayList<>());

        when(putBookService.update(anyLong(),any())).thenReturn(bookDTO);
        String json = mapper.writeValueAsString(bookDTO);
        mockMvc.perform(put("/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.title", is("new book")))
                .andExpect(jsonPath("$.synopsis", is("synopsis")))
                .andExpect(jsonPath("$.isbn", is("9788533302273")));
    }

    @Test
    void updateWhenBodyIsInvalid() throws Exception{
        BookDTO bookDTO = new BookDTO("new book","synopsis", "39788533302273", new Date(), 40.99,
                4, "test", new ArrayList<>());

        String json = mapper.writeValueAsString(bookDTO);
        mockMvc.perform(put("/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("$", is("invalid ISBN")));
    }

    @Test
    void deleteTest() throws Exception {
        mockMvc.perform(delete("/books/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
        verify(deleteBookService).delete(1L);
    }

    @Test
    void deleteWhenIdDontExist() throws Exception{
        when(mockMvc.perform(delete("/books/1"))).thenThrow(EntityNotFoundException.class);
        mockMvc.perform(delete("/books/1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
        verify(deleteBookService).delete(1L);
    }

    @Test
    void deleteWhenHasPurchaseWithThisBook() throws Exception{
        when(mockMvc.perform(delete("/books/1"))).thenThrow(DataIntegrityViolationException.class);
        mockMvc.perform(delete("/books/1"))
                .andExpect(MockMvcResultMatchers.status().isConflict());
        verify(deleteBookService).delete(1L);
    }
}