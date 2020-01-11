package com.sbakht.QuranRoots.Surah;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbakht.QuranRoots.AlQuranApi.SurahText;
import com.sbakht.QuranRoots.AlQuranApi.Wrapper;
import com.sbakht.QuranRoots.Ayat.Ayat;
import com.sbakht.QuranRoots.Root.Root;
import com.sbakht.QuranRoots.Root.RootService;
import com.sbakht.QuranRoots.Word.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
    private RestTemplate restTemplate = new RestTemplate();

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
        List<Surah> clonedSurahs = new ArrayList<>();
        for (Surah surah : this.surahs) {
            List<Ayat> ayatsByLevel = surah.getAyats().stream().filter(ayat -> ayat.getLevel() == level).collect(Collectors.toList());
            Surah clonedSurah = new Surah();
            clonedSurah.setSurahNumber(surah.getSurahNumber());
            clonedSurah.setAyats(ayatsByLevel);

            // TODO: refactor this
            List<SurahText> allSurahsTranslations = this.getApiSurahs("FullTranslation");
            SurahText surahText = allSurahsTranslations.stream()
                    .filter(s -> s.getSurahNumber() == surah.getSurahNumber())
                    .findFirst().get();

            for (Ayat ayat : clonedSurah.getAyats()) {
                com.sbakht.QuranRoots.AlQuranApi.Ayat apiAyah = surahText.getAyahs().stream()
                        .filter(ayah -> ayah.getAyatNumber() == ayat.getAyatNumber())
                        .findFirst().get();
                ayat.setTranslation(apiAyah.getText());
            }

            List<SurahText> allSurahsArabic = this.getApiSurahs("UthmaniQuran");
            SurahText surahTextA = allSurahsArabic.stream()
                    .filter(s -> s.getSurahNumber() == surah.getSurahNumber())
                    .findFirst().get();

            for (Ayat ayat : clonedSurah.getAyats()) {
                com.sbakht.QuranRoots.AlQuranApi.Ayat apiAyah = surahTextA.getAyahs().stream()
                        .filter(ayah -> ayah.getAyatNumber() == ayat.getAyatNumber())
                        .findFirst().get();
                ayat.setArabic(apiAyah.getText());
            }

            clonedSurahs.add(clonedSurah);
        }
        return clonedSurahs;
    }

    public List<SurahText> getApiSurahs(String str) {

        try {
            InputStream inputStream = TypeReference.class.getResourceAsStream("/json/" + str + ".json");
            ObjectMapper mapper = new ObjectMapper();

            return mapper.readValue(inputStream, new TypeReference<Wrapper>() {
            }).getData().getSurahs();
        } catch (IOException e) {
            System.out.println("Unable to fetch surahs: " + e.getMessage());
            return new ArrayList<>();
        }
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
