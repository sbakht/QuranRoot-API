package com.sbakht.QuranRoots.AlQuranApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Ayat {
    private String text;
    @JsonProperty("numberInSurah")
    private int ayatNumber;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getAyatNumber() {
        return ayatNumber;
    }

    public void setAyatNumber(int ayatNumber) {
        this.ayatNumber = ayatNumber;
    }
}
