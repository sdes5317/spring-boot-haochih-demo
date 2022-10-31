
package com.example.haochihdemo.dto.coindesk;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bpi {

    @SerializedName("USD")
    @Expose
    private Currency usd;
    @SerializedName("GBP")
    @Expose
    private Currency gbp;
    @SerializedName("EUR")
    @Expose
    private Currency eur;

    public Currency getUsd() {
        return usd;
    }

    public void setUsd(Currency usd) {
        this.usd = usd;
    }

    public Currency getGbp() {
        return gbp;
    }

    public void setGbp(Currency gbp) {
        this.gbp = gbp;
    }

    public Currency getEur() {
        return eur;
    }

    public void setEur(Currency eur) {
        this.eur = eur;
    }
}
