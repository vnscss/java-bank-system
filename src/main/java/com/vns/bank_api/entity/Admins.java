package com.vns.bank_api.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "admins")
public class Admins{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Usuarios> usuarios = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public List<Usuarios> getUsuarios() {
        return usuarios;
    }

    public void addUsuario(Usuarios usuario) {
        this.usuarios.add(usuario);
    }

    public void removeUsuario(Usuarios usuario) {
        this.usuarios.remove(usuario);
    }
}

