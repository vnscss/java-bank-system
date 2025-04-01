package com.vns.bank_api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vns.bank_api.entity.Usuarios;
import com.vns.bank_api.repository.UsuariosRepository;

@Service
public class UserService {

    @Autowired
    private UsuariosRepository usuariosRepository;

    public List<Usuarios> validete(String username) {
    List<Usuarios> usuarios = usuariosRepository.findByUsername(username);
    return usuarios;
    }


    public Integer CreateUser(String username, String password) {
         List<Usuarios> usuarioReturn = validete(username);
        if (usuarioReturn.isEmpty()) {
            Usuarios usuario = new Usuarios();
            usuario.setUsername(username);
            usuario.setPassword(password);
            usuariosRepository.save(usuario);
            return 201;
        }
        else{
            return 409;
        }
        
    }
}