package com.vns.bank_api.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vns.bank_api.entity.Usuarios;
import com.vns.bank_api.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class UsuariosControler {

    @Autowired
    private UserService userService;

    @PostMapping("create-user/")
    public ResponseEntity<String> CreateUserControler(@RequestBody String request) throws JsonMappingException, JsonProcessingException {

        String json = request;
        ObjectMapper objectMapper = new ObjectMapper();
        Usuarios usuario = objectMapper.readValue(json, Usuarios.class);
        String username = usuario.getUsername();
        String password = usuario.getPassword();

        Integer status = userService.CreateUser(username, password);
        if(status == 201){
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario criado com sucesso!");
        }
        else if (status == 409){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuario ja existe!");
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar usuario!");
        }
    }
    
}
