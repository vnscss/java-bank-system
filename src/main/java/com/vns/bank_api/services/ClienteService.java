package com.vns.bank_api.services;

import java.util.List;
import java.util.Optional;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vns.bank_api.entity.Cliente;
import com.vns.bank_api.entity.Gerente;
import com.vns.bank_api.repository.ClienteRepository;
import com.vns.bank_api.repository.ContaRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ContaService contaService;

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private GerenteService gerenteService;

    public List<Cliente> findByCpf(String cpf) {
        return clienteRepository.findByCpf(cpf);
    }

    public Integer createCliente(Cliente cliente , Integer userId) {
        List<Cliente> existingClientes = findByCpf(cliente.getCpf());
        Optional<Cliente> clienteByUser = clienteRepository.findByUserId(userId);
        if (existingClientes.isEmpty() && clienteByUser.isEmpty()) {
            clienteRepository.save(cliente);
            cliente.setUserId(userId);
            clienteRepository.save(cliente);

            contaService.createConta(userId);
            cliente.setContaNumero(contaRepository.findByClienteId(cliente.getId()).get().getId());

            Gerente gerente = gerenteService.findFirstGerente();
            Integer gerenteId;
            if(gerente == null){
                gerenteId = 0;
            }
            else{
                gerenteId = gerente.getId();
            }
            cliente.setGerenteId(gerenteId);


            clienteRepository.save(cliente);

            return 201; 
        }
        if (!existingClientes.isEmpty() || !clienteByUser.isEmpty()) {
            return 409;
        } else {
            return 500; 
        }
}

}