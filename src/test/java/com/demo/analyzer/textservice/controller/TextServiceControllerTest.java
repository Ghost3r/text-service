package com.demo.analyzer.textservice.controller;

import com.demo.analyzer.textservice.dto.SearchType;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TextServiceControllerTest {

    TextServiceController controller = new TextServiceController();

    @Test
    void getAnalyzedTextResponse_shouldCheckThatEveryVowelAppearsOnce() {
        Map<String, Integer> analyzedTextResponse = controller.getAnalyzedTextResponse(SearchType.VOWELS, "a,ie,O,u");

        TextServiceController.VOWELS_UPPERCASE.forEach(vowel -> {
            assertEquals(1, analyzedTextResponse.get(vowel));
        });
    }

    @Test
    void getAnalyzedTextResponse_shouldCheckThatNoVowelAppears() {
        Map<String, Integer> analyzedTextResponse = controller.getAnalyzedTextResponse(SearchType.VOWELS, "56-48,97894651 233qwtr,ztlkmnjb");
        Map<String, Integer> emptyStringResponse = controller.getAnalyzedTextResponse(SearchType.VOWELS, "");

        TextServiceController.VOWELS_UPPERCASE.forEach(vowel -> {
            assertEquals(0, analyzedTextResponse.get(vowel));
            assertEquals(0, emptyStringResponse.get(vowel));

        });
    }

    @Test
    void getAnalyzedTextResponse_shouldCheckThatEveryVowelAppearsAsExpected() {
        Map<String, Integer> analyzedTextResponse = controller.getAnalyzedTextResponse(SearchType.VOWELS, "aaaaabbbeeeeIIIcccccc Oo, blbU");

        assertEquals(5, analyzedTextResponse.get("A"));
        assertEquals(4, analyzedTextResponse.get("E"));
        assertEquals(3, analyzedTextResponse.get("I"));
        assertEquals(2, analyzedTextResponse.get("O"));
        assertEquals(1, analyzedTextResponse.get("U"));
    }

    @Test
    void getAnalyzedTextResponse_shouldCheckConsonantAppearsAsExpected() {
        Map<String, Integer> analyzedTextResponse = controller.getAnalyzedTextResponse(SearchType.CONSONANTS, "aAEeiIbbb-BBBoOUu");

        assertEquals(6, analyzedTextResponse.get("B"));
    }

    @Test
    void getAnalyzedTextResponse_shouldCheckThatNoConsonantAppears() {
        Map<String, Integer> analyzedTextResponse = controller.getAnalyzedTextResponse(SearchType.CONSONANTS, "aaaeeeiiiooouuu");
        Map<String, Integer> emptyStringResponse = controller.getAnalyzedTextResponse(SearchType.CONSONANTS, "");

        assertEquals(Collections.emptyMap(), analyzedTextResponse);
        assertEquals(Collections.emptyMap(), emptyStringResponse);
    }
}