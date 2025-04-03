package com.vns.bank_api.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vns.bank_api.entity.Gerente;
import com.vns.bank_api.entity.Usuarios;
import com.vns.bank_api.repository.GerenteRepository;

@Service
public class GerenteService {
    @Autowired
    private GerenteRepository gerenteRepository;

    @Autowired
    private UserService userService;
    

    public Gerente findFirstGerente() {
        return gerenteRepository.findFirstByOrderByIdAsc();
    }


    public Integer createGerente(Gerente gerente) {
        gerenteRepository.save(gerente);
        return 201;
    }
    
    public Integer gerenteLogin(String username , String password ,Gerente gerente){
        Integer status = userService.login(username, password);
        if (status == 200) {
            if(gerente.getRole().contains("ROLE_GERENTE")){
                return 200;
            }else{
                return 401;
            }
        }else{
            return 500;
        }
    }
}
