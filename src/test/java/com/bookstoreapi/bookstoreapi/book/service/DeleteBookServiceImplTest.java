package com.bookstoreapi.bookstoreapi.book.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
public class DeleteBookServiceImplTest {
//
//    @InjectMocks
//    private DeleteBookServiceImpl deleteBookService;
//    @Mock
//    private BookRepository repository;
//    @Mock
//    private PurchaseService purchaseService;
//    @Mock
//    private BookService service;
//    private final Map<Long, Book> books = new HashMap<>();
//
//
//    @BeforeEach
//    void setUp() {
//        Book book1 = new Book();
//        book1.setId(1L);
//        book1.setTitle("book 1");
//
//        Book book2 = new Book();
//        book2.setId(2L);
//        book2.setTitle("book 2");
//
//        books.put(1L, book1);
//        books.put(2L, book2);
//    }
//
//    @Test
//    void deleteWhenIdExistTest(){
//        when(service.findById(1L)).thenReturn(books.get(1L));
//        deleteBookService.delete(1L);
//    }
//
//    @Test
//    void deleteWhenExistPurchaseWithClient(){
//        when(purchaseService.existsByBookId(anyLong())).thenReturn(true);
//        assertThrows(DataIntegrityViolationException.class, ()-> deleteBookService.delete(1L));
//    }
}