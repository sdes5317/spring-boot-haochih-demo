package com.example.haochihdemo.dto;

import com.example.haochihdemo.entity.CurrencyMappingEntity;
import org.modelmapper.ModelMapper;

public class InsertCurrencyMappingDto {

  private String code;
  private String chineseName;

  public String getCode() {
    return code;
  }

  public String getChineseName() {
    return chineseName;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public void setChineseName(String chineseName) {
    this.chineseName = chineseName;
  }
}
