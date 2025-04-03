package com.vns.bank_api.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Service;

import com.vns.bank_api.entity.Admins;
import com.vns.bank_api.entity.Usuarios;
import com.vns.bank_api.repository.AdminsRepository;

@Service
public class AdminService {

    @Autowired
    private AdminsRepository adminsRepository;


    @Autowired
    private UserService userService;

    Admins findUserInAdmin(Usuarios usuarios) {
        if (usuarios.getAdmins() != null) {
            Optional<Admins> userInAdmin = adminsRepository.findById(usuarios.getAdmins().getId());
            return userInAdmin.orElse(null);
        }
        return null;
    }

    public Integer adminLogin(Usuarios usuarios) {
        Integer loginStatus = userService.login(usuarios.getUsername(), usuarios.getPassword());
        Admins userInAdmins = findUserInAdmin(usuarios);

        if (loginStatus == 200 && userInAdmins != null) {
            return 200;
        }

        return 401;
    }
}
