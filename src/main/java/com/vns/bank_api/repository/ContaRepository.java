package com.vns.bank_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vns.bank_api.entity.Conta;

public interface ContaRepository extends JpaRepository<Conta, Integer> {
    // Aqui você pode adicionar métodos personalizados, se necessário
    // Exemplo: List<Conta> findByClienteId(Integer clienteId);

}
