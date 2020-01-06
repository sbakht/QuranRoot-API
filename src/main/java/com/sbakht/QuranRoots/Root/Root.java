package com.sbakht.QuranRoots.Root;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Root {
    @JsonProperty("categories")
    private List<RootGroup> rootGroups;
    private String root;
    private int id;
    private int wordCount;

    public Root() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<RootGroup> getRootGroups() {
        return rootGroups;
    }

    public void setRootGroups(List<RootGroup> rootGroups) {
        this.rootGroups = rootGroups;
    }

    public Integer getWordCount() {
        return this.wordCount;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }

    public void setWordCount() {
        this.wordCount = this.rootGroups
                .stream()
                .map(rootGroup -> rootGroup.getWordCount())
                .mapToInt(Integer::intValue)
                .sum();
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public Integer getLevel() {
        int level = 1;
        int[] levelMin = {500, 300, 200, 100, 60, 40, 30, 20, 10, 5, 4, 3, 2, 1};
        for (int count : levelMin) {
            if (this.wordCount >= count) {
                return level;
            }
            level++;
        }
        return -1;
    }
}
