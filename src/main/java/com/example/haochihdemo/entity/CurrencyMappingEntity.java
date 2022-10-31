package com.example.haochihdemo.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "CurrencyMapping")
public class CurrencyMappingEntity implements Serializable {
  public CurrencyMappingEntity(String code, String traditionalChinese) {
    this.code = code;
    this.chineseName = traditionalChinese;
  }

  public CurrencyMappingEntity() {
  }

  @Column(unique = true)
  private String code;


  @Column
  private String chineseName;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;


  public void setCode(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;
  }

  public void setChineseName(String chineseName) {
    this.chineseName = chineseName;
  }

  public String getChineseName() {
    return chineseName;
  }
  
  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }
}
