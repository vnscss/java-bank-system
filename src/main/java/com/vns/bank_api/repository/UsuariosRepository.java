package com.vns.bank_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vns.bank_api.entity.Usuarios;

public interface UsuariosRepository extends JpaRepository<Usuarios, Integer> {

}
