package com.vns.bank_api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vns.bank_api.entity.Usuarios;
import com.vns.bank_api.repository.UsuariosRepository;

@Service
public class UserService {

    @Autowired
    private UsuariosRepository usuariosRepository;

    public Integer login( String username, String password) {
        List<Usuarios> usuarioReturn = findUser(username);
        if (usuarioReturn.isEmpty()) {
            return 404;
        } else {
            for (Usuarios usuario : usuarioReturn) {
                if (usuario.getPassword().equals(password)) {
                    return 200;
                }
            }
            return 401;
        }
        
    }


    public List<Usuarios> findUser(String username) {
    List<Usuarios> usuarios = usuariosRepository.findByUsername(username);
    return usuarios;
    }


    public Integer CreateUser(String username, String password) {
         List<Usuarios> usuarioReturn = findUser(username);
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