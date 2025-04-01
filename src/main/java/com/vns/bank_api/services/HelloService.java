package com.vns.bank_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vns.bank_api.entity.HelloEntity;
import com.vns.bank_api.repository.HelloInterface;

@Service
public class HelloService {

    @Autowired
    private HelloInterface helloInterface;

    public String createHello() {
        HelloEntity helloEntity = new HelloEntity();
        helloEntity.setMessage("Hello, Vinicius!");

        helloInterface.save(helloEntity); // Salva no banco de dados
        return helloEntity.getMessage();
    }
}
