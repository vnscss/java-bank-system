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
import com.vns.bank_api.entity.Cliente;
import com.vns.bank_api.entity.Gerente;
import com.vns.bank_api.services.ClienteService;
import com.vns.bank_api.services.ContaService;
import com.vns.bank_api.services.GerenteService;
import com.vns.bank_api.services.UserService;

class ClientePayload {

    private String username;
    private String password;

    private String nome;
    private String cpf;
    private Integer dataNasc;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNome() {
        return nome;
    }
    public String getCpf() {
        return cpf;
    }
    public Integer getDataNasc() {
        return dataNasc;
    }

}


@RestController
public class ClienteControler {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ContaService contaService;

    @Autowired
    private GerenteService gerenteService;

    @Autowired
    private UserService userService;

    @PostMapping("create-cliente/")
    public ResponseEntity<String> createClienteControler(@RequestBody String request) throws JsonMappingException, JsonProcessingException {

        String json = request;
        ObjectMapper objectMapper = new ObjectMapper();
        ClientePayload clientePayload = objectMapper.readValue(json, ClientePayload.class);

        Integer loginStatus =  userService.login(clientePayload.getUsername(), clientePayload.getPassword());
        if (loginStatus == 200) {
                Integer userId = userService.findUser(clientePayload.getUsername()).get().getId();   
                Gerente gerente = gerenteService.findFirstGerente();
                Integer gerenteId;
                if(gerente == null){
                    gerenteId = 0;
                }
                else{
                    gerenteId = gerente.getId();
                }
                Cliente cliente = new Cliente();
                cliente.setNome(clientePayload.getNome());
                cliente.setCpf(clientePayload.getCpf());
                cliente.setDataNasc(clientePayload.getDataNasc());
                cliente.setGerenteId(gerenteId);


                Integer status = clienteService.createCliente(cliente , userId);


                
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
