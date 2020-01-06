package com.sbakht.QuranRoots.Root;

import com.sbakht.QuranRoots.Word.Word;

import java.util.List;

public class RootGroup {
    private String name;
    private int id;
    private List<Word> data;

    public RootGroup() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Word> getData() {
        return data;
    }

    public void setData(List<Word> data) {
        this.data = data;
    }

    public Integer getWordCount() {
        return this.data.size();
    }
}
