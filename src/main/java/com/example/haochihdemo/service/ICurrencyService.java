package com.example.haochihdemo.service;

import com.example.haochihdemo.dto.CurrencyDto;
import com.example.haochihdemo.dto.InsertCurrencyMappingDto;
import com.example.haochihdemo.dto.coindesk.Currency;
import com.example.haochihdemo.entity.CurrencyMappingEntity;

public interface ICurrencyService {
  void initialMappingIfNotExist();

  String getCoinDeskData();

  Iterable<CurrencyDto> getCurrency();

  String getChineseNameMappingOrEmpty(Currency cur);

  void insert(InsertCurrencyMappingDto dto);

  CurrencyMappingEntity update(CurrencyMappingEntity entity);

  Iterable<CurrencyMappingEntity> selectAll();

  void delete(Long id);
}
