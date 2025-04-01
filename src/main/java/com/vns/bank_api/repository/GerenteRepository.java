package com.vns.bank_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vns.bank_api.entity.Gerente;

public interface GerenteRepository extends JpaRepository<Gerente, Integer> {
    // Aqui você pode adicionar métodos personalizados, se necessário
    // Exemplo: List<Gerente> findByNome(String nome);

}
