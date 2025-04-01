package com.vns.bank_api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "transacoes")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String tipo;
    private Integer data;
    private Integer remetente;
    private Integer destinatario;
    private Double valor;

    public Integer getId() {
        return id;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getData() {
        return data;
    }
    public void setData(Integer data) {
        this.data = data;
    }

    public Integer getRemetente() {
        return remetente;
    }
    public void setRemetente(Integer remetente) {
        this.remetente = remetente;
    }

    public Integer getDestinatario() {
        return destinatario;
    }
    public void setDestinatario(Integer destinatario) {
        this.destinatario = destinatario;
    }

    public Double getValor() {
        return valor;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }
}
