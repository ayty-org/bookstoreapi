package com.bookstoreapi.bookstoreapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;


@SpringBootTest()
@TestPropertySource(locations = "classpath:application-books-test.properties")
public class BookstoreApiBooksTests {

	@Test
	void contextLoads() {
	}

}
