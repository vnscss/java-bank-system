package com.vns.bank_api.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vns.bank_api.entity.Cliente;
import com.vns.bank_api.entity.Gerente;
import com.vns.bank_api.entity.Usuarios;
import com.vns.bank_api.services.ContaService;
import com.vns.bank_api.services.UserService;

import java.util.Optional;
import java.util.OptionalInt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


class DepositoPayload {
    private String username;
    private String password;
    private String cpf;


    public String getCpf() {
        return cpf;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
}


@RestController
public class ContaControler {

    @Autowired
    private ContaService contaService;

    @Autowired
    private UserService userService;

    @PostMapping("consultar-saldo/{cpf}")
    public ResponseEntity<String> consultarSaltoControler(@PathVariable String cpf, @RequestBody String request) throws JsonMappingException, JsonProcessingException {
        String json = request;
        ObjectMapper objectMapper = new ObjectMapper();
        Usuarios usuario = objectMapper.readValue(json, Usuarios.class);


       Integer loginStatus =  userService.login(usuario.getUsername(), usuario.getPassword());
               if (loginStatus == 200) {
                Optional<Double> saldo = contaService.consultarSaldo(cpf);
                if (saldo.isPresent()) {

                    return ResponseEntity.status(HttpStatus.OK).body("Seu saldo é: " + saldo.get());
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao consultar saldo!");
                }
        } else if (loginStatus == 404) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado!");
        } else if (loginStatus == 401) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha incorretos!");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar cliente!");
        }
    }

    @PostMapping("conta-depositar/{valor}")
    public ResponseEntity<String> contaDepositarControler(@PathVariable Double valor, @RequestBody String request) throws JsonMappingException, JsonProcessingException {
        String json = request;
        ObjectMapper objectMapper = new ObjectMapper();
        DepositoPayload depositoPayload = objectMapper.readValue(json, DepositoPayload.class);

        Integer loginStatus =  userService.login(depositoPayload.getUsername(), depositoPayload.getPassword());
               if (loginStatus == 200) {
                Integer status = contaService.depositar(valor , depositoPayload.getCpf());

                if (status == 200) {
                    return ResponseEntity.status(HttpStatus.OK).body("Deposito realizado com sucesso!");
                }else{
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao depositar!");
                }
        } else if (loginStatus == 404) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado!");
        } else if (loginStatus == 401) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha incorretos!");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao depositar!");
        }



        @PostMapping("conta-sacar/{valor}")
        public ResponseEntity<String> contaSacarControler(@PathVariable Double valor, @RequestBody String request) throws JsonMappingException, JsonProcessingException {
            String json = request;
            ObjectMapper objectMapper = new ObjectMapper();
            DepositoPayload depositoPayload = objectMapper.readValue(json, DepositoPayload.class);

            Integer loginStatus =  userService.login(depositoPayload.getUsername(), depositoPayload.getPassword());
            if (loginStatus == 200) {
             Integer status = contaService.sacar(valor , depositoPayload.getCpf());

             if (status == 200) {
                 return ResponseEntity.status(HttpStatus.OK).body("Saque realizado com sucesso!");
             }
            else if(status == 400){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Saldo insuficiente!");
            }
             else if(status == 500){
                 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao sacar!");
            }
             else{
                 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao sacar!");
             }
            } else if (loginStatus == 404) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado!");
            } else if (loginStatus == 401) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha incorretos!");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao depositar!");
            }

    }
}
