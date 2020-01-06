package com.sbakht.QuranRoots.Root;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RootService {
    private List<Root> roots;

    public RootService() {

        try {
            InputStream inputStream = TypeReference.class.getResourceAsStream("/json/RootToLocation.json");
            ObjectMapper mapper = new ObjectMapper();

            this.roots = mapper.readValue(inputStream, new TypeReference<List<Root>>() {
            });
            for (Root root : this.roots) {
                root.setWordCount();
            }
        } catch (IOException e) {
            System.out.println("Unable to fetch roots: " + e.getMessage());
            this.roots = new ArrayList<>();
        }
    }

    public List<Root> getAllRoots() {
        return this.roots;
    }

    public Root getRoot(String root) {
        return this.roots
                .stream()
                .filter(r -> r.getRoot().equals(root))
                .findFirst()
                .get();
    }

    public Root getRootById(int id) {
        return this.roots
                .stream()
                .filter(r -> r.getId() == id)
                .findFirst()
                .get();
    }

    public List<Root> getRootsByLevel(int level) {
        return this.roots
                .stream()
                .filter(root -> root.getLevel() == level)
                .collect(Collectors.toList());
    }
}
