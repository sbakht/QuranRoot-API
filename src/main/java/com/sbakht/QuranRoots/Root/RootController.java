package com.sbakht.QuranRoots.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RootController {
    @Autowired
    private RootService rootService;

    public RootController() {

    }

    @RequestMapping("/roots")
    public List<Root> getAllRoots() {
        return rootService.getAllRoots();
    }

    @RequestMapping("/roots/{id}")
    public Root getRootById(@PathVariable("id") int id) {
        return rootService.getRootById(id);
    }

    @RequestMapping("/roots/level/{level}") // 1 to 14
    public List<Root> getRootsByLevel(@PathVariable("level") int level) {
        return rootService.getRootsByLevel(level);
    }

    @RequestMapping("/roots/minimal")
    public List<Root> getMinimalRoots() {
        List<Root> clonedRoots = new ArrayList<>();
        for (Root root : rootService.getAllRoots()) {
            Root clonedRoot = new Root();
            clonedRoot.setRoot(root.getRoot());
            clonedRoot.setId(root.getId());
            clonedRoot.setWordCount(root.getWordCount());
            clonedRoots.add(clonedRoot);
        }
        return clonedRoots;
    }
}
