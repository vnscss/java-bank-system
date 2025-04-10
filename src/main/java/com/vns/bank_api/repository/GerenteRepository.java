package com.vns.bank_api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vns.bank_api.entity.Gerente;

public interface GerenteRepository extends JpaRepository<Gerente, Integer> {
    // Aqui você pode adicionar métodos personalizados, se necessário
    List<Gerente> findByNome(String nome);  

    Optional<Gerente> findByUserId(Integer userId);

    Gerente findFirstByOrderByIdAsc();

}
