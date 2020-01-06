package com.sbakht.QuranRoots.Surah;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SurahController {
    @Autowired
    private SurahService surahService;

    @RequestMapping("/surahs")
    public List<Surah> getAllSurahs() {
        return surahService.getAllSurahs();
    }

    @RequestMapping("/surahs/{id}")
    public Surah getSurah(@PathVariable("id") int surahNumber) {
        return surahService.getSurah(surahNumber);
    }

    @RequestMapping("/surahs/level/{level}")
    public List<Surah> getSurahsByAyatLevel(@PathVariable("level") int level) {
        return surahService.getSurahsByAyatLevel(level);
    }

}
