package com.vns.bank_api.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vns.bank_api.entity.Cliente;
import com.vns.bank_api.entity.Conta;
import com.vns.bank_api.repository.ClienteRepository;
import com.vns.bank_api.repository.ContaRepository;

@Service
public class ContaService {
    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public Optional<Conta> findByUserId(Integer userId) {
        Optional<Cliente> cliente = clienteRepository.findByUserId(userId);
        return contaRepository.findByClienteId(cliente.get().getId());
    }

    public Integer findClienteId(Integer userId) {
        Optional<Cliente> cliente = clienteRepository.findByUserId(userId);
        return cliente.get().getId();
    }

    public Integer createConta(Integer userId){
        Integer clienteId = findClienteId(userId);
        if (findByUserId(userId).isEmpty()) {
            Conta conta = new Conta();
            conta.setAgenciaId(null);
            conta.setClienteId(clienteId);
            conta.setSaldo(0.00);

            contaRepository.save(conta);
            System.out.println("Conta criada com sucesso!");
            return 201;
        } else {
            return 409;
        }
        

    }
}
