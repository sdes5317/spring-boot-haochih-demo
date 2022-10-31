package com.example.haochihdemo.controller;

import com.example.haochihdemo.dto.CurrencyDto;
import com.example.haochihdemo.service.ICurrencyService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyController {
  private final ICurrencyService service;
  @Autowired
  public CurrencyController(ICurrencyService service) {
    this.service = service;
    //如果資料庫沒有中文對照Mapping,則初始化
    service.initialMappingIfNotExist();
  }
  @Operation(summary = "呼叫資料轉換的 API，並顯示其內容")
  @RequestMapping(value = "/getCurrency", method = RequestMethod.GET)
  public Iterable<CurrencyDto> getCurrency() {
    return service.getCurrency();
  }
  @Operation(summary = "呼叫 coindesk API，並顯示其內容")
  @RequestMapping(value = "/callCoinDeskApi", method = RequestMethod.GET)
  public String callCoinDeskApi() {
    return service.getCoinDeskData();
  }
}
