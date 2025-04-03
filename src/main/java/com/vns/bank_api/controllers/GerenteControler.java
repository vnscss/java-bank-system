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
import com.fasterxml.jackson.databind.annotation.JsonAppend.Attr;
import com.vns.bank_api.entity.Gerente;
import com.vns.bank_api.services.GerenteService;
import com.vns.bank_api.services.UserService;

@RestController
public class GerenteControler {
    @Autowired
    private GerenteService gerenteService;

    @Autowired
    private UserService userService;
    
    static class GerentePayload{

        private String username;
        private String password;

        public String getUsername(){
            return this.username;
        }
        public String getPassword(){
            return this.password;
        }
    }

    @PostMapping("create-gerente/")
    public ResponseEntity<String> createGerente(@RequestBody String request) throws JsonMappingException, JsonProcessingException{
        String json = request;
        ObjectMapper objectMapper = new ObjectMapper();
        GerentePayload gerentePayload =  objectMapper.readValue(json, GerentePayload.class);
        
        Integer loginStatus =  userService.login(gerentePayload.getUsername() , gerentePayload.getPassword());
        if (loginStatus == 200) {

                Gerente gerente = new Gerente();
                Integer status = gerenteService.createGerente(gerente);
                
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
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha incorretos!");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar cliente!");
        }
    }
}
