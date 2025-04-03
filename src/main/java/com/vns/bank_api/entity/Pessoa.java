package com.vns.bank_api.entity;

import java.util.List;

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
    protected List<String> role;
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


    public List<String> getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role.add(role);
    }

}
