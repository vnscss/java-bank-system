package com.vns.bank_api.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vns.bank_api.entity.Gerente;
import com.vns.bank_api.repository.GerenteRepository;

@Service
public class GerenteService {
    @Autowired
    private GerenteRepository gerenteRepository;

    public Gerente findFirstGerente() {
        return gerenteRepository.findFirstByOrderByIdAsc();
    }


    public Integer createGerente(Gerente gerente , Integer userId) {
        Optional<Gerente> gerenteById = gerenteRepository.findByUserId(userId);

        if (gerenteById.isEmpty()) {
            gerenteRepository.save(gerente);
            gerente.setUserId(userId);
            gerenteRepository.save(gerente);
            return 201;
        }
        if (gerenteById.isPresent()) {
            return 409;
        }
        else{
            return 500;
        }

    }
    
}
