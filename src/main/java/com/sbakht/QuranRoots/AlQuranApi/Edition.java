package com.sbakht.QuranRoots.AlQuranApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Edition {
    private List<SurahText> surahs;

    public List<SurahText> getSurahs() {
        return surahs;
    }

    public void setSurahs(List<SurahText> surahs) {
        this.surahs = surahs;
    }

}
