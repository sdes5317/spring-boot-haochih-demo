package com.example.haochihdemo.service;

import com.example.haochihdemo.dto.CurrencyDto;
import com.example.haochihdemo.dto.InsertCurrencyMappingDto;
import com.example.haochihdemo.entity.CurrencyMappingEntity;
import com.example.haochihdemo.repository.ICurrencyMappingRepository;
import com.google.gson.Gson;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class CurrencyServiceTest {
  private ICoinDeskParser parser = Mockito.mock(CoinDeskParser.class);
  private ICurrencyMappingRepository repository = Mockito.mock(ICurrencyMappingRepository.class);
  private CurrencyMapper mapper = new CurrencyMapper();
  private ICurrencyService ICurrencyService =
      Mockito.spy(new CurrencyService(repository, parser, mapper));
  private String testCurrentPrice = getFakeJson();
  private List<CurrencyMappingEntity> testMappingEntity = getFakeCurrencyMappingEntities();

  CurrencyServiceTest() throws IOException, URISyntaxException {
    Mockito.when(parser.parseCoinDeskData()).thenReturn(testCurrentPrice);
    Mockito.when(repository.findAll()).thenReturn(testMappingEntity);
    Mockito.when(repository.save(Mockito.any(CurrencyMappingEntity.class)))
        .thenAnswer(i -> i.getArgument(0));
    Mockito.when(
            repository.findOne(Mockito.argThat(c -> c != null && c.getProbe().getCode().equals("USD"))))
        .thenAnswer(i -> Optional.of(testMappingEntity.get(0)));
    Mockito.when(
            repository.findOne(Mockito.argThat(c -> c != null && c.getProbe().getCode().equals("GBP"))))
        .thenAnswer(i -> Optional.of(testMappingEntity.get(1)));
    Mockito.when(
            repository.findOne(Mockito.argThat(c -> c != null && c.getProbe().getCode().equals("EUR"))))
        .thenAnswer(i -> Optional.of(testMappingEntity.get(2)));
  }

  @Test
  void getCurrency() {
    List<CurrencyDto> actually = Lists.newArrayList(ICurrencyService.getCurrency());
    Assertions.assertEquals("USD", actually.get(0).getCode());
    Assertions.assertEquals("美金", actually.get(0).getChineseName());
    Assertions.assertEquals(20532.1945F, actually.get(0).getRate(), 0.0001);
    Assertions.assertEquals("2022/10/31 12:22:00", actually.get(0).getUpdateTime());
    Assertions.assertEquals("GBP", actually.get(1).getCode());
    Assertions.assertEquals("新台幣", actually.get(1).getChineseName());
    Assertions.assertEquals(17156.5374F, actually.get(1).getRate(), 0.0001);
    Assertions.assertEquals("2022/10/31 12:22:00", actually.get(1).getUpdateTime());
    Assertions.assertEquals("EUR", actually.get(2).getCode());
    Assertions.assertEquals("歐元", actually.get(2).getChineseName());
    Assertions.assertEquals(20001.3551F, actually.get(2).getRate(), 0.0001);
    Assertions.assertEquals("2022/10/31 12:22:00", actually.get(2).getUpdateTime());

    System.out.println(new Gson().toJson(actually));
  }

  @Test
  void callCoinDesk() {
    System.out.println(ICurrencyService.getCoinDeskData());
  }

  @Test
  void insert() {
    InsertCurrencyMappingDto dto = new InsertCurrencyMappingDto();
    dto.setCode("test");
    dto.setChineseName("測試貨幣");
    ICurrencyService.insert(dto);
  }

  @Test
  void selectAll() {
    Iterable<CurrencyMappingEntity> entities = ICurrencyService.selectAll();
    Assertions.assertTrue(entities.spliterator().getExactSizeIfKnown() > 0);
    System.out.println(new Gson().toJson(entities));
  }

  @Test
  void update() {
    CurrencyMappingEntity except = new CurrencyMappingEntity();
    except.setId(1L);
    except.setCode("USD");
    except.setChineseName("美金");

    CurrencyMappingEntity actually = ICurrencyService.update(except);
    Assertions.assertEquals(except.getId(), actually.getId());
    Assertions.assertEquals(except.getCode(), actually.getCode());
    Assertions.assertEquals(except.getChineseName(), actually.getChineseName());

    System.out.println(new Gson().toJson(actually));
  }

  @Test
  void delete() {
    ICurrencyService.delete(1L);
  }

  private static List<CurrencyMappingEntity> getFakeCurrencyMappingEntities() {
    return Arrays.asList(new CurrencyMappingEntity("USD", "美金"),
        new CurrencyMappingEntity("GBP", "新台幣"), new CurrencyMappingEntity("EUR", "歐元"));
  }

  private String getFakeJson() throws URISyntaxException, IOException {
    URI path = getClass().getClassLoader().getResource("test/testcurrentprice.json").toURI();
    return new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
  }

}
