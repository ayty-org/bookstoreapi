package com.bookstoreapi.bookstoreapi.client.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
public class DeleteClientServiceImplTest {
//
//    @InjectMocks
//    private DeleteClientServiceImpl deleteClientService;
//    @Mock
//    private ClientRepository repository;
//    @Mock
//    private PurchaseService purchaseService;
//    @Mock
//    private ClientService service;
//    private Client client;
//
//
//    @BeforeEach
//    void setUp() {
//        Client client = new Client();
//        client.setId(1L);
//        client.setName("Jenipapo da Silva");
//
//       this.client = client;
//    }
//
//    @Test
//    void deleteWhenIdExistTest(){
//        when(purchaseService.existsByClientId(anyLong())).thenReturn(false);
//        when(service.findById(1L)).thenReturn(client);
//        deleteClientService.delete(1L);
//    }
//
//    @Test
//    void deleteWhenIdDontExistTest(){
//        when(purchaseService.existsByClientId(anyLong())).thenReturn(false);
//        when(service.findById(1L)).thenThrow(EntityNotFoundException.class);
//        assertThrows(EntityNotFoundException.class, ()-> deleteClientService.delete(1L));
//    }
//
//    @Test
//    void deleteWhenExistPurchaseWithClient(){
//        when(purchaseService.existsByClientId(anyLong())).thenReturn(true);
//        assertThrows(DataIntegrityViolationException.class, ()-> deleteClientService.delete(1L));
//    }
}