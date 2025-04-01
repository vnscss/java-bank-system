package com.vns.bank_api.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "contas")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    
    private Double saldo;
    private Integer agenciaId;
    private Integer clienteId;

    public Integer getId() {
        return id;
    }

    public Double getSaldo() {
        return saldo;
    }
    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }


    public Integer getAgenciaId() {
        return agenciaId;
    }
    public void setAgenciaId(Integer agenciaId) {
        this.agenciaId = agenciaId;
    }

    
    public Integer getClienteId() {
        return clienteId;
    }
    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

}
