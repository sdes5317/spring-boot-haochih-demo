package com.example.haochihdemo.utility;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeFormatterTest {

  @Test
  void coinDeskFormatToMissionTimeFormat() {
    String raw = "Oct 31, 2022 01:00:00 UTC";
    String expected = "2022/10/31 09:00:00";
    String actually = TimeFormatter.coinDeskFormatToMissionTimeFormat(raw);
    assertEquals(expected, actually);
  }
}
