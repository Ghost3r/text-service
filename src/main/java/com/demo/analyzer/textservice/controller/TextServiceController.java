package com.demo.analyzer.textservice.controller;

import com.demo.analyzer.textservice.dto.SearchType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("analyze")
public class TextServiceController {

    protected static final Set<String> VOWELS_UPPERCASE = new HashSet<>(Arrays.asList("A", "E", "I", "O", "U"));

    @GetMapping("/text")
    public Map<String, Integer> getAnalyzedTextResponse(@RequestParam SearchType type, @RequestParam String input) {
        Map<String, Integer> vowelMap = VOWELS_UPPERCASE.stream().collect(Collectors.toMap(v -> v, v -> 0));
        Map<String, Integer> consonantsMap = new HashMap<>();

        for (int i = 0; i < input.length(); i++) {
            final String searchChar = String.valueOf(input.toUpperCase().charAt(i));
            final Optional<String> foundVowel = VOWELS_UPPERCASE.stream().filter(v -> v.equals(searchChar)).findFirst();
            if (foundVowel.isPresent()) {
                vowelMap.put(foundVowel.get(), vowelMap.get(foundVowel.get()) + 1);
            } else {
                consonantsMap.put(searchChar, Optional.ofNullable(consonantsMap.get(searchChar)).map(count -> count + 1).orElse(1));
            }
        }
        switch (type) {
            case VOWELS:
                return vowelMap;
            case CONSONANTS:
                return consonantsMap;
            default:
                return Collections.emptyMap();
        }
    }
}