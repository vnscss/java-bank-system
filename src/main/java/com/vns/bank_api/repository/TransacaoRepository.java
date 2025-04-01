package com.vns.bank_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vns.bank_api.entity.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Integer> {
    // Aqui você pode adicionar métodos personalizados, se necessário
    // Exemplo: List<Transacao> findByTipo(String tipo);

}
