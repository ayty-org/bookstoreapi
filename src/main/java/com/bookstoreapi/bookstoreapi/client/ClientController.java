package com.bookstoreapi.bookstoreapi.client;

import com.bookstoreapi.bookstoreapi.client.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RequestMapping("/clients")
@RequiredArgsConstructor
@RestController
public class ClientController {

    private final GetAllClientService getAllClientService;
    private final GetClientService getClientService;
    private final PostClientService postClientService;
    private final PutClientService putClientService;
    private final DeleteClientService deleteClientService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ClientDTO> list(){
        return getAllClientService.findAll();
    }

    @GetMapping("/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public ClientDTO find(@PathVariable Long clientId){
        return getClientService.findById(clientId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDTO save(@RequestBody @Valid ClientDTO client){
        return postClientService.save(client);
    }

    @PutMapping("/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public ClientDTO update(@PathVariable Long clientId, @RequestBody @Valid ClientDTO client){
        return putClientService.update(clientId,client);
    }

    @DeleteMapping("/{clientId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long clientId){
        deleteClientService.delete(clientId);
    }
}
