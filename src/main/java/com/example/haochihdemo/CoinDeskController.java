package com.example.haochihdemo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoinDeskController {
    @RequestMapping("/HelloWorld")
  public String HelloWorld(){
      return "Hello World";
    }
}
