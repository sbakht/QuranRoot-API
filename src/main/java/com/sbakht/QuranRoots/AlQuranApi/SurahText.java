package com.sbakht.QuranRoots.AlQuranApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

// http://api.alquran.cloud/v1/surah/114/editions/en.pickthall
@JsonIgnoreProperties(ignoreUnknown = true)
public class SurahText {
    @JsonProperty("number")
    private int surahNumber;
    @JsonProperty("englishName")
    private String surahName;
    private List<Ayat> ayahs;

    public int getSurahNumber() {
        return surahNumber;
    }

    public void setSurahNumber(int surahNumber) {
        this.surahNumber = surahNumber;
    }

    public List<Ayat> getAyahs() {
        return ayahs;
    }

    public void setAyahs(List<Ayat> ayahs) {
        this.ayahs = ayahs;
    }

    public String getSurahName() {

        return surahName;
    }

    public void setSurahName(String surahName) {
        this.surahName = surahName;
    }
}

