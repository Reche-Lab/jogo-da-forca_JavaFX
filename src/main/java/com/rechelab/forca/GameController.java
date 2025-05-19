package com.rechelab.forca;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class GameController {
    private final String wordToGuess;
    private final Set<Character> correctGuesses = new HashSet<>();
    private final Set<Character> wrongGuesses = new HashSet<>();
    private final int maxErrors = 6;

    public GameController() {
        this.wordToGuess = loadRandomWord().toUpperCase();
    }

    private String loadRandomWord() {
        try {
            List<String> words = Files.readAllLines(Paths.get("src/main/resources/words.txt"));
            Collections.shuffle(words);
            return words.get(0).trim();
        } catch (IOException e) {
            return "JAVA"; // fallback se não encontrar o arquivo
        }
    }

    public boolean guessLetter(char letter) {
        letter = Character.toUpperCase(letter);

        // Palavra e letra normalizadas
        String normalizedWord = removeAccents(wordToGuess).toUpperCase();
        char normalizedLetter = removeAccents(String.valueOf(letter)).charAt(0);

        boolean acerto = false;
        for (int i = 0; i < normalizedWord.length(); i++) {
            if (normalizedWord.charAt(i) == normalizedLetter) {
                // Adiciona o caractere original (com acento ou cedilha)
                correctGuesses.add(wordToGuess.charAt(i));
                acerto = true;
            }
        }

        if (!acerto) {
            wrongGuesses.add(letter);
        }

        return acerto;
    }

    private String removeAccents(String input) {
        if (input == null) return null;

        // Remove acentos com Normalizer
        String normalized = java.text.Normalizer.normalize(input, java.text.Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");

        // Substitui caracteres especiais remanescentes
        normalized = normalized.replaceAll("Ç", "C").replaceAll("ç", "c");

        return normalized;
    }

    public String getMaskedWord() {
        StringBuilder masked = new StringBuilder();
        for (char c : wordToGuess.toCharArray()) {
            if (c == '-') {
                masked.append("- "); // mostra hífen diretamente
            } else if (correctGuesses.contains(c)) {
                masked.append(c).append(" ");
            } else {
                masked.append("_ ");
            }
        }
        return masked.toString().trim();
    }

    public String getWrongGuessesAsString() {
        List<Character> sorted = new ArrayList<>(wrongGuesses);
        Collections.sort(sorted);
        StringBuilder sb = new StringBuilder();
        for (char c : sorted) {
            sb.append(c).append(" ");
        }
        return sb.toString().trim();
    }

    public boolean isWon() {
        for (char c : wordToGuess.toCharArray()) {
            if (!correctGuesses.contains(c)) {
                return false;
            }
        }
        return true;
    }

    public boolean isLost() {
        return wrongGuesses.size() >= maxErrors;
    }

    public int getRemainingTries() {
        return maxErrors - wrongGuesses.size();
    }

    public String getWordToGuess() {
        return wordToGuess;
    }

    public Set<Character> getWrongGuesses() {
        return wrongGuesses;
    }

    public int getErrors() {
        return wrongGuesses.size();
    }
}
