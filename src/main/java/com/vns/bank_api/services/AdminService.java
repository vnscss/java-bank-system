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

    Boolean findUserInAdmin(Usuarios usuarios) {
        Optional<Usuarios> user = userService.findUser(usuarios.getUsername());
        if (user.isPresent() && user.get().getAdmins() != null) {
            return true;
        }
        return false;
    }

    public Integer adminLogin(Usuarios usuarios) {
        Integer loginStatus = userService.login(usuarios.getUsername(), usuarios.getPassword());
        Boolean userInAdmins = findUserInAdmin(usuarios);

        if (loginStatus == 200 && userInAdmins) {
            return 200;
        }else{
            return 401;
            }
        }

    public Integer createAdmin(Usuarios usuarios) {
        Integer status = adminLogin(usuarios);
        if (status == 200) {
            Admins admins = adminsRepository.findById(1).orElse(null);
            admins.addUsuario(usuarios);
        }if(status == 401){
            return 401; 
        }else{
            return 500; 
        }
    }
}
