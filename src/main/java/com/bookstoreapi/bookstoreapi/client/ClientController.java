package com.bookstoreapi.bookstoreapi.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/clients")
@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Client> list(){
        return clientService.findAll();
    }

    @GetMapping("/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public Client find(@PathVariable Long clientId){
        return clientService.findById(clientId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client save(@RequestBody Client client){
        return clientService.save(client);
    }

    @PutMapping("/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public Client update(@PathVariable Long clientId, @RequestBody Client client){
        return clientService.update(clientId,client);
    }

    @DeleteMapping("/{clientId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long clientId){
        clientService.delete(clientId);
    }
}
