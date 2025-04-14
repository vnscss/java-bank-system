package com.vns.bank_api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vns.bank_api.entity.Cliente;
import com.vns.bank_api.entity.Conta;
import com.vns.bank_api.repository.ClienteRepository;
import com.vns.bank_api.repository.ContaRepository;

import jakarta.persistence.criteria.CriteriaBuilder.In;

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

    public Optional<Double> consultarSaldo(String cpf) {
        List<Cliente> cliente = clienteRepository.findByCpf(cpf);
        if (cliente.isEmpty()) {
            return Optional.empty();
        }
        Optional<Conta> conta = contaRepository.findByClienteId(cliente.get(0).getId());
        if (conta.isPresent()) {
            return Optional.of(conta.get().getSaldo());
        } else {
            return Optional.empty();
        }
    }


    public Optional<Conta> safeGetContaByClienteId(String cpf) {
        List<Cliente> cliente = clienteRepository.findByCpf(cpf);
        if (cliente.isEmpty()) {
            return Optional.empty();
        }
        Optional<Conta> conta = contaRepository.findByClienteId(cliente.get(0).getId());
        if (conta.isPresent()) {
            return conta;
        } else {
            return Optional.empty();
        }
    }

    public Integer depositar(Double valor, String cpf) {
        List<Cliente> cliente = clienteRepository.findByCpf(cpf);
        if (cliente.isEmpty()) {
            return 500;
        }
        Optional<Conta> conta = contaRepository.findByClienteId(cliente.get(0).getId());
        if (conta.isPresent()) {
            Double saldoAtual = conta.get().getSaldo();
            Double novoSaldo = saldoAtual + valor;
            conta.get().setSaldo(novoSaldo);
            contaRepository.save(conta.get());
            return 200;
        } else {
            return 500;
        }
    }

    public Integer sacar(Double valor, String cpf) {
        List<Cliente> cliente = clienteRepository.findByCpf(cpf);
        if (cliente.isEmpty()) {
            return 500;
        }
        Optional<Conta> conta = contaRepository.findByClienteId(cliente.get(0).getId());
        if (conta.isPresent()) {
            Double saldoAtual = conta.get().getSaldo();
            if (valor > saldoAtual) {
                return 400; 
            } else {
                Double novoSaldo = saldoAtual - valor;
                conta.get().setSaldo(novoSaldo);
                contaRepository.save(conta.get());
                return 200;
            }
        } else {
            return 500;
        }
    }
}

