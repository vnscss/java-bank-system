package com.vns.bank_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vns.bank_api.Payloads.ClientePayload;
import com.vns.bank_api.entity.Cliente;
import com.vns.bank_api.services.ClienteService;
import com.vns.bank_api.services.UserService;


@RestController
public class ClienteControler {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClientePayload clientePayload;

    @Autowired
    private UserService userService;

    @PostMapping("create-cliente/")
    public ResponseEntity<String> createClienteControler(@RequestBody String request) throws JsonMappingException, JsonProcessingException {

        String json = request;
        ObjectMapper objectMapper = new ObjectMapper();
        ClientePayload clientePayload = objectMapper.readValue(json, ClientePayload.class);

        Integer loginStatus =  userService.login(clientePayload.getUsername(), clientePayload.getPassword());
        if (loginStatus == 200) {

                Cliente cliente = new Cliente();
                cliente.setNome(clientePayload.getNome());
                cliente.setCpf(clientePayload.getCpf());
                cliente.setDataNasc(clientePayload.getDataNasc());


                Integer status = clienteService.createCliente(cliente);
                if (status == 201) {
                    return ResponseEntity.status(HttpStatus.CREATED).body("Cliente criado com sucesso!");
                } else if (status == 409) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("Cliente já existe!");
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar cliente!");
                }
        } else if (loginStatus == 404) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado!");
        } else if (loginStatus == 401) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha incorreta!");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar cliente!");
        }
    }

}
