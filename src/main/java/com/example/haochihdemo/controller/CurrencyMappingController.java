package com.example.haochihdemo.controller;

import com.example.haochihdemo.dto.InsertCurrencyMappingDto;
import com.example.haochihdemo.entity.CurrencyMappingEntity;
import com.example.haochihdemo.service.ICurrencyService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyMappingController {
  private ICurrencyService service;

  @Autowired
  public CurrencyMappingController(ICurrencyService service) {
    this.service = service;
  }
  @Operation(summary = "查詢幣別對應表資料，並顯示其內容")
  @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
  public Iterable<CurrencyMappingEntity> selectAll() {
    return service.selectAll();
  }
  @Operation(summary = "新增幣別對應表資料")
  @RequestMapping(value = "/insert", method = RequestMethod.POST)
  public void insert(InsertCurrencyMappingDto dto) {
    service.insert(dto);
  }
  @Operation(summary = "更新幣別對應表資料，並顯示其內容")
  @RequestMapping(value = "/update", method = RequestMethod.PUT)
  public CurrencyMappingEntity update(CurrencyMappingEntity entity) {
    return service.update(entity);
  }
  @Operation(summary = "刪除幣別對應表資料")
  @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
  public void delete(Long id) {
    service.delete(id);
  }
}
