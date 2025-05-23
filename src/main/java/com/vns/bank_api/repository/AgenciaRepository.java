package com.vns.bank_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vns.bank_api.entity.Agencia;

public interface AgenciaRepository extends JpaRepository<Agencia, Integer> {
    // Aqui você pode adicionar métodos personalizados, se necessário
    // Exemplo: List<Agencia> findByNome(String nome);

}
