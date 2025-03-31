package com.vns.bank_api.Controler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vns.bank_api.Models.Pessoa;
import com.vns.bank_api.Services.PessoaService;

@RestController
public class Controlers {
    @GetMapping("/hello")
    public String hello(){
        return PessoaService.pessoaNome();
    }

    @GetMapping("/create")
    public String create(){
        Pessoa = new Pessoa();
        return PessoaService.create();
    }
}
