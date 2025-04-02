package com.vns.bank_api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vns.bank_api.entity.Cliente;
import com.vns.bank_api.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;


    public List<Cliente> findByCpf(String cpf) {
        return clienteRepository.findByCpf(cpf);
    }

    public Integer createCliente(Cliente cliente) {
        List<Cliente> existingClientes = findByCpf(cliente.getCpf());
        if (existingClientes.isEmpty()) {
            clienteRepository.save(cliente);
            return 201; // Created
        } else {
            return 409; // Conflict
        }
    }

}