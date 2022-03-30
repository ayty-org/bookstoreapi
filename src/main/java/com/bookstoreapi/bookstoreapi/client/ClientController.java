package com.bookstoreapi.bookstoreapi.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RequestMapping("/clients")
@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ClientDTO> list(){
        return clientService.findAll();
    }

    @GetMapping("/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public ClientDTO find(@PathVariable Long clientId){
        return clientService.getDTO(clientId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDTO save( @RequestBody @Valid ClientDTO client){
        return clientService.save(client);
    }

    @PutMapping("/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public ClientDTO update(@PathVariable Long clientId, @RequestBody @Valid ClientDTO client){
        return clientService.update(clientId,client);
    }

    @DeleteMapping("/{clientId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long clientId){
        clientService.delete(clientId);
    }
}
