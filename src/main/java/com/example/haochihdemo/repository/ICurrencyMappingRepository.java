package com.example.haochihdemo.repository;

import com.example.haochihdemo.entity.CurrencyMappingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICurrencyMappingRepository extends JpaRepository<CurrencyMappingEntity, Long> {

}
