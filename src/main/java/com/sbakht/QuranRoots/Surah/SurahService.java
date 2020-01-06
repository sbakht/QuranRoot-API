package com.sbakht.QuranRoots.Surah;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbakht.QuranRoots.Ayat.Ayat;
import com.sbakht.QuranRoots.Root.Root;
import com.sbakht.QuranRoots.Root.RootService;
import com.sbakht.QuranRoots.Word.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SurahService {
    @Autowired
    private RootService rootService;
    private List<Surah> surahs;

    public SurahService() {
        try {
            InputStream inputStream = TypeReference.class.getResourceAsStream("/json/LocationToRoot.json");
            ObjectMapper mapper = new ObjectMapper();

            this.surahs = mapper.readValue(inputStream, new TypeReference<List<Surah>>() {
            });
        } catch (IOException e) {
            System.out.println("Unable to fetch surahs: " + e.getMessage());
            this.surahs = new ArrayList<>();
        }
    }

    private int avg(List<Integer> levels) {
        return Math.round(levels.stream().mapToInt(Integer::intValue).sum() / levels.size());
    }

    public List<Surah> getAllSurahs() {
        setUpAyatLevels();
        return this.surahs;
    }

    public List<Surah> getSurahsByAyatLevel(int level) {
        setUpAyatLevels();
//        return this.surahs
//                .stream()
//                .filter(surah -> surah.getAyats().stream().allMatch(ayat -> ayat.getLevel() == 2))
//                .collect(Collectors.toList());
        List<Surah> clonedSurahs = new ArrayList<>();
        for (Surah surah : this.surahs) {
            List<Ayat> ayatsByLevel = surah.getAyats().stream().filter(ayat -> ayat.getLevel() == level).collect(Collectors.toList());
            Surah clonedSurah = new Surah();
            clonedSurah.setSurahNumber(surah.getSurahNumber());
            clonedSurah.setAyats(ayatsByLevel);
            clonedSurahs.add(clonedSurah);
        }
        return clonedSurahs;
    }

    public Surah getSurah(int surahNumber) {
        setUpAyatLevels();
        for (Surah surah : this.surahs) {
            if (surah.getSurahNumber() == surahNumber) {
                return surah;
            }
        }
        return new Surah(); // TODO: throw error instead
    }

    private void setUpAyatLevels() { // TODO: this is bad, needs to happen on initialize
        for (Surah surah : this.surahs) {
            for (Ayat ayah : surah.getAyats()) {
                List<Integer> levels = new ArrayList<>();
                for (Word word : ayah.getWords()) {
                    Root root = rootService.getRoot(word.getRoot());
                    levels.add(root.getLevel());
                }
                ayah.setLevel(avg(levels));
            }
        }
    }
}
