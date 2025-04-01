package com.vns.bank_api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "gerentes")
public class Gerente extends Pessoa {
    private String clientes;

    public String getClientes() {
        return clientes;
    }
    public void setClientes(String clientes) {
        this.clientes = clientes;
    }

}
