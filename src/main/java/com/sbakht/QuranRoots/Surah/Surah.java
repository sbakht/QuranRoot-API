package com.sbakht.QuranRoots.Surah;

import com.sbakht.QuranRoots.Ayat.Ayat;

import java.util.List;

public class Surah {
    private int surahNumber;
    private List<Ayat> ayats;

    public Surah() {

    }

    public Surah(Integer surahNumber) {
        this.surahNumber = surahNumber;
    }

    public Integer getSurahNumber() {
        return surahNumber;
    }

    public void setSurahNumber(Integer surahNumber) {
        this.surahNumber = surahNumber;
    }

    public List<Ayat> getAyats() {
        return ayats;
    }

    public void setAyats(List<Ayat> ayats) {
        this.ayats = ayats;
    }
}
