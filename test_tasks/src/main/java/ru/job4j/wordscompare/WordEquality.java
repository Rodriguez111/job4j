package ru.job4j.wordscompare;

import java.util.*;

public class WordEquality {

    public boolean wordsAreEqual(String word1, String word2) {
        Set<String> set = new HashSet<>();
        set.add(word1);
        set.add(word2);
        return set.size() == 1;
    }

    public boolean checkForSymbolsEqualityByMap(String word1, String word2) {
        boolean result = true;
        if (word1.length() != word2.length()) {
            result = false;
        } else {

            result = charactersCheck(word1, word2);
        }
        return result;
    }

  private boolean charactersCheck(String word1, String word2) {
        boolean result = true;
      Map<Character, Integer> mapOfCharacters = new HashMap<>();
      for (int i = 0; i < word1.length(); i++) {
          char currentChar = word1.charAt(i);
          mapOfCharacters.computeIfPresent(currentChar, (k, v) -> v + 1);
          mapOfCharacters.putIfAbsent(currentChar, 1);
      }

      for (int i = 0; i < word2.length(); i++) {
          char currentChar = word2.charAt(i);
          if (!mapOfCharacters.containsKey(currentChar)) {
              break;
          } else {
              if (mapOfCharacters.get(currentChar) > 1) {
                  mapOfCharacters.computeIfPresent(currentChar, (k, v) -> v - 1);
              } else {
                  mapOfCharacters.remove(currentChar);
              }
          }
      }
      if (mapOfCharacters.size() > 0) {
          result = false;
      }
    return result;
  }

    public boolean checkForSymbolsEqualityBySort(String word1, String word2) {
        char[] arrayFromWord1 = word1.toCharArray();
        char[] arrayFromWord2 = word2.toCharArray();
        Arrays.sort(arrayFromWord1);
        Arrays.sort(arrayFromWord2);
        return Arrays.equals(arrayFromWord1, arrayFromWord2);
    }
}
