package com.sbakht.QuranRoots.Ayat;


import com.sbakht.QuranRoots.Word.Word;

import java.util.List;

public class Ayat {
    private Integer ayatNumber;
    private List<Word> words;
    private int level;
    private String translation;
    private String arabic;

    public Ayat() {

    }

    public String getArabic() {
        return arabic;
    }

    public void setArabic(String arabic) {
        this.arabic = arabic;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

    public Integer getAyatNumber() {
        return ayatNumber;
    }

    public void setAyatNumber(Integer ayatNumber) {
        this.ayatNumber = ayatNumber;
    }

}
