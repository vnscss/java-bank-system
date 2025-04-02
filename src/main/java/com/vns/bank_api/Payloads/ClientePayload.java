package com.vns.bank_api.Payloads;
public class ClientePayload {

    private String username;
    private String password;

    private String nome;
    private String cpf;
    private Integer dataNasc;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNome() {
        return nome;
    }
    public String getCpf() {
        return cpf;
    }
    public Integer getDataNasc() {
        return dataNasc;
    }

}


