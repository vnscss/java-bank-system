package com.vns.bank_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vns.bank_api.entity.HelloEntity;


public interface HelloInterface extends JpaRepository<HelloEntity, Integer> {

}
