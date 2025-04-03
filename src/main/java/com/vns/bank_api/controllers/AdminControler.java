package com.vns.bank_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vns.bank_api.entity.Admins;
import com.vns.bank_api.entity.Usuarios;
import com.vns.bank_api.repository.AdminsRepository;
import com.vns.bank_api.services.AdminService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class AdminControler {

    private final AdminsRepository adminsRepository;

    @Autowired
    private AdminService adminService;

    static class CreateAdminPayload {
        private String username;
        private String password;
        private String targetUser;

        public String getTargetUser() {
            return targetUser;
        }

        public String getUsername() {
            return username;
        }
        public String getPassword() {
            return password;
        }
    }

    AdminControler(AdminsRepository adminsRepository) {
        this.adminsRepository = adminsRepository;
    }

    @PostMapping("create-admin/")
    public ResponseEntity<String> postMethodName(@RequestBody String request) throws JsonMappingException, JsonProcessingException {
        String json = request;
        ObjectMapper objectMapper = new ObjectMapper();
        CreateAdminPayload createAdminPayload = objectMapper.readValue(json, CreateAdminPayload.class);

        Usuarios usuarios = new Usuarios();
        usuarios.setUsername(createAdminPayload.getUsername());
        usuarios.setPassword(createAdminPayload.getPassword());

        Integer status = adminService.adminLogin(usuarios);

        if(status == 200) {
            Admins admins = adminsRepository.findById(1).orElse(null);
            if (admins != null) {
                admins.getUsuarios().add(usuarios); // Ensure the relationship is updated
                adminsRepository.saveAndFlush(admins); // Use saveAndFlush to persist changes immediately
                return ResponseEntity.status(HttpStatus.OK).body("Admin created successfully");
            } 
            }
        if (status == 401) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating admin");
        }

    }
}
