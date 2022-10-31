package com.example.haochihdemo.service;

import com.example.haochihdemo.dto.coindesk.CoinDesk;
import com.google.gson.Gson;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CoinDeskParser implements ICoinDeskParser {
  private String url = "https://api.coindesk.com/v1/bpi/currentprice.json";
  private HttpHeaders headers;
  private RestTemplate rest;

  public CoinDeskParser() {
    rest = new RestTemplate();
    headers = new HttpHeaders();
    headers.add("Content-Type", "application/json");
    headers.add("Accept", "*/*");
  }

  @Override
  public String parseCoinDeskData() {
    HttpEntity<String> requestContent = new HttpEntity<String>("", headers);
    ResponseEntity<String> response =
        rest.exchange(url, HttpMethod.GET, requestContent, String.class);
    if (response.getStatusCode().isError()) {
      //todo throw exception
    }

    return response.getBody();
  }
}
