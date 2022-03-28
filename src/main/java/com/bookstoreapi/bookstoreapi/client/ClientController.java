package com.bookstoreapi.bookstoreapi.client;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/clients")
@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;


    @ApiOperation(value = "Return a list of all saved clients")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Client> list(){
        return clientService.findAll();
    }

    @ApiOperation(value = "Returns a unique client found by id")
    @GetMapping("/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public Client find(@PathVariable Long clientId){
        return clientService.findById(clientId);
    }

    @ApiOperation(value = "Save a new client")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client save(@RequestBody Client client){
        return clientService.save(client);
    }

    @ApiOperation(value = "Updates all fields of a saved client by the client passed as a parameter")
    @PutMapping("/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public Client update(@PathVariable Long clientId, @RequestBody Client client){
        return clientService.update(clientId,client);
    }

    @ApiOperation(value = "Deletes a client by id")
    @DeleteMapping("/{clientId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long clientId){
        clientService.delete(clientId);
    }
}
