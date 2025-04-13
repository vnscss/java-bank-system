package com.vns.bank_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vns.bank_api.entity.Conta;

public interface ContaRepository extends JpaRepository<Conta, Integer> {
    // Aqui você pode adicionar métodos personalizados, se necessário
    Optional<Conta> findByClienteId(Integer clienteId);

}
