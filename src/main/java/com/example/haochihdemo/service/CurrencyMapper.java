package com.example.haochihdemo.service;

import com.example.haochihdemo.dto.CurrencyDto;
import com.example.haochihdemo.dto.InsertCurrencyMappingDto;
import com.example.haochihdemo.entity.CurrencyMappingEntity;
import com.example.haochihdemo.dto.coindesk.Currency;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CurrencyMapper {
  private ModelMapper modelMapper = new ModelMapper();

  public CurrencyMappingEntity mapTo(InsertCurrencyMappingDto dto) {
    return modelMapper.map(dto, CurrencyMappingEntity.class);
  }

  public CurrencyDto mapTo(Currency currency, String updatedTime, String chineseName){
    CurrencyDto dto = new CurrencyDto();
    dto.setCode(currency.getCode());
    dto.setRate(currency.getRateFloat());
    dto.setUpdateTime(updatedTime);
    dto.setChineseName(chineseName);
    return dto;
  }
}
