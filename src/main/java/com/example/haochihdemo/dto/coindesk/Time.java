package com.example.haochihdemo.dto.coindesk;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

public class Time {

  @SerializedName("updated")
  @Expose
  private String updated;
  @SerializedName("updatedISO")
  @Expose
  private String updatedISO;
  @SerializedName("updateduk")
  @Expose
  private String updateduk;

  public String getUpdated() {
    return updated;
  }

  public void setUpdated(String updated) {
    this.updated = updated;
  }

  public String getUpdatedISO() {
    return updatedISO;
  }

  public void setUpdatedISO(String updatedISO) {
    this.updatedISO = updatedISO;
  }

  public String getUpdateduk() {
    return updateduk;
  }

  public void setUpdateduk(String updateduk) {
    this.updateduk = updateduk;
  }

}
