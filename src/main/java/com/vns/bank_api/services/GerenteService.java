package com.vns.bank_api.services;

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

    
}
