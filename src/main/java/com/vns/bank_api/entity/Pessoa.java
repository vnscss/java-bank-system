package com.vns.bank_api.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String cpf;
    private String nome;
    private Integer dataNasc;
    private String role;
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }   


    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }


    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }


    public Integer getDataNasc() {
        return dataNasc;
    }
    public void setDataNasc(Integer dataNasc) {
        this.dataNasc = dataNasc;
    }


    public String getRole() {
        return role;
    }
    public void setRole(Integer gerenteId ,String role) {
        this.role = role;
    }

}
