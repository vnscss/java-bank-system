package com.vns.bank_api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente extends Pessoa{
    private Integer gererentId;
    private Integer contaNumero;

    public Integer getGerenteId() {
        return gererentId;
    }
    public void setGerenteId(Integer gerenteId) {
        this.gererentId = gerenteId;
    }

    public Integer getContaNumero() {
        return contaNumero;
    }

}
