package com.example.haochihdemo.utility;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class TimeFormatter {
  static final DateTimeFormatter rawPattern =
      DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm:ss z", Locale.ENGLISH);
  static final DateTimeFormatter missionPattern =
      DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss", Locale.TAIWAN);
  static final ZoneId taipeiZoneId = ZoneId.of("Asia/Taipei");
  static final ZoneId utcZoneId = ZoneOffset.UTC;

  public TimeFormatter() {

  }

  public static String coinDeskFormatToMissionTimeFormat(String rawDateTime) {
    ZonedDateTime utc = ZonedDateTime.parse(rawDateTime, rawPattern);

    ZonedDateTime taipei = utc.withZoneSameInstant(taipeiZoneId);
    return taipei.format(missionPattern);
  }
}
