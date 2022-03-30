package com.bookstoreapi.bookstoreapi.book;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;


    public List<BookDTO> findAll(){
        return bookRepository.findAll()
                .stream()
                .map(BookDTO::new).collect(Collectors.toList());
    }

    public List<BookDTO> findAllByCategoriesName(String name){
        return bookRepository.findAllByCategoriesName(name)
                .stream()
                .map(BookDTO::new).collect(Collectors.toList());
    }

    private Book findById(Long id){
        return bookRepository.findById(id).orElseThrow( () ->{
            throw new EntityNotFoundException("Book with id "+id+" not found");
        });
    }

    public BookDTO getDTO(Long id){
        return new BookDTO(this.findById(id));
    }

    public BookDTO save(BookDTO bookDTO){
        bookRepository.save(new Book(bookDTO));
        return bookDTO;
    }

    public void delete(Long id){
        bookRepository.delete(this.findById(id));
    }

    public BookDTO update(Long id, BookDTO bookDTO){
        Book bookSaved = this.findById(id);
        BeanUtils.copyProperties(bookDTO, bookSaved);
        return new BookDTO(bookRepository.save(bookSaved));
    }
}
