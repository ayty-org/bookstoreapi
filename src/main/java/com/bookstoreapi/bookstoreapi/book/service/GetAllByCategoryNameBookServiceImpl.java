package com.bookstoreapi.bookstoreapi.book.service;

import com.bookstoreapi.bookstoreapi.book.BookDTO;
import com.bookstoreapi.bookstoreapi.book.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GetAllByCategoryNameBookServiceImpl implements GetAllByCategoryNameBookService{

    private final BookRepository bookRepository;


    @Override
    public List<BookDTO> findAllByCategoriesName(String name){
        return bookRepository.findAllByCategoriesNameIgnoreCase(name)
                .stream()
                .map(BookDTO::new).collect(Collectors.toList());
    }
}
