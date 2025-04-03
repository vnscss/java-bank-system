package com.vns.bank_api.repository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vns.bank_api.entity.Admins;

@Repository
public interface AdminsRepository extends JpaRepository<Admins, Integer> {
    // Aqui você pode adicionar métodos personalizados, se necessário
    // Exemplo: List<Admins> findByNome(String username);
    Optional<Admins> findById(Integer id);
}
