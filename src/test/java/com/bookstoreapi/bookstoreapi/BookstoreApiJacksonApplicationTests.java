package com.bookstoreapi.bookstoreapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;


@SpringBootTest()
@TestPropertySource(locations = "classpath:application-test.yml")
public class BookstoreApiJacksonApplicationTests {

	@Test
	void contextLoads() {
	}

}
