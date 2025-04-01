package com.vns.bank_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vns.bank_api.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
