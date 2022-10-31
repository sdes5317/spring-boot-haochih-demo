package com.example.haochihdemo.service;

import com.example.haochihdemo.dto.CurrencyDto;
import com.example.haochihdemo.dto.InsertCurrencyMappingDto;
import com.example.haochihdemo.entity.CurrencyMappingEntity;
import com.example.haochihdemo.dto.coindesk.CoinDesk;
import com.example.haochihdemo.dto.coindesk.Currency;
import com.example.haochihdemo.repository.ICurrencyMappingRepository;
import com.example.haochihdemo.utility.TimeFormatter;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService implements ICurrencyService {
  final ICurrencyMappingRepository currencyMappingRepository;
  final ICoinDeskParser parser;
  final CurrencyMapper currencyMapper;

  @Autowired
  public CurrencyService(ICurrencyMappingRepository currencyMappingRepository, ICoinDeskParser parser,
      CurrencyMapper currencyMapper) {
    this.currencyMappingRepository = currencyMappingRepository;
    this.parser = parser;
    this.currencyMapper = currencyMapper;
  }

  @Override
  public void initialMappingIfNotExist() {
    if (currencyMappingRepository.count() == 0) {
      currencyMappingRepository.saveAll(Arrays.asList(new CurrencyMappingEntity("USD", "美金"),
          new CurrencyMappingEntity("GBP", "新台幣"), new CurrencyMappingEntity("EUR", "歐元")));
    }
  }

  @Override
  public String getCoinDeskData() {
    return parser.parseCoinDeskData();
  }

  @Override
  public Iterable<CurrencyDto> getCurrency() {
    ArrayList<CurrencyDto> dtos = new ArrayList<CurrencyDto>();
    CoinDesk rawParserData = new Gson().fromJson(parser.parseCoinDeskData(), CoinDesk.class);
    List<Currency> currency =
        Arrays.asList(rawParserData.getBpi().getUsd(), rawParserData.getBpi().getGbp(),
            rawParserData.getBpi().getEur());

    currency.forEach(cur -> {
      String chineseName = getChineseNameMappingOrEmpty(cur);
      String twDateTimeString =
          TimeFormatter.coinDeskFormatToMissionTimeFormat(rawParserData.getTime().getUpdated());
      dtos.add(currencyMapper.mapTo(cur, twDateTimeString, chineseName));
    });

    return dtos;
  }

  @Override
  public String getChineseNameMappingOrEmpty(Currency cur) {
    CurrencyMappingEntity pattern = new CurrencyMappingEntity();
    pattern.setCode(cur.getCode());
    Optional<CurrencyMappingEntity> mappingEntity =
        currencyMappingRepository.findOne(Example.of(pattern));

    return mappingEntity.isPresent() ? mappingEntity.get().getChineseName() : "";
  }

  @Override
  public void insert(InsertCurrencyMappingDto dto) {
    currencyMappingRepository.save(currencyMapper.mapTo(dto));
  }

  @Override
  public CurrencyMappingEntity update(CurrencyMappingEntity entity) {
    return currencyMappingRepository.save(entity);
  }

  @Override
  public Iterable<CurrencyMappingEntity> selectAll() {
    return currencyMappingRepository.findAll();
  }

  @Override
  public void delete(Long id) {
    currencyMappingRepository.deleteById(id);
  }
}
