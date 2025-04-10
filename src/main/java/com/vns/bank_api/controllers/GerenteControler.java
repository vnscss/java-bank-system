package com.vns.bank_api.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vns.bank_api.entity.Gerente;
import com.vns.bank_api.services.GerenteService;
import com.vns.bank_api.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


class GerentePayload {

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
public class GerenteControler {

    @Autowired
    private GerenteService gerenteService;

    @Autowired
    private UserService userService;

    @PostMapping("create-gerente/")
    public ResponseEntity<String> createGerenteControler(@RequestBody String request) throws JsonMappingException, JsonProcessingException {

        String json = request;
        ObjectMapper objectMapper = new ObjectMapper();
        GerentePayload gerentePayload = objectMapper.readValue(json, GerentePayload.class);
        

        Integer loginStatus =  userService.login(gerentePayload.getUsername(), gerentePayload.getPassword());
        if (loginStatus == 200) {
            Integer userId = userService.findUser(gerentePayload.getUsername()).get().getId();   

            Gerente gerente = new Gerente();
            gerente.setNome(gerentePayload.getNome());
            gerente.setCpf(gerentePayload.getCpf());
            gerente.setDataNasc(gerentePayload.getDataNasc());
            gerente.setRole("ge-0000");
            Integer status = gerenteService.createGerente(gerente , userId);

            if (status == 201) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Gerente criado com sucesso!");
            } else if (status == 409) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Gerente já existe!");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar gerente!");
            }
        }
        else if (loginStatus == 404) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado!");
        } else if (loginStatus == 401) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha incorretos!");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar gerente!");
        }


    }
    

}
