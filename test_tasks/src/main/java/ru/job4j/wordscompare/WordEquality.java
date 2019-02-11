package ru.job4j.wordscompare;

import java.util.*;
import java.util.stream.Stream;

public class WordEquality {

    public boolean wordsAreEqual(String word1, String word2) {
        Set<String> set = new HashSet<>();
        set.add(word1);
        set.add(word2);
        return set.size() == 1;
    }

    public boolean checkForSymbolsEquality(String word1, String word2) {
        boolean result = true;
        if (word1.length() != word2.length()) {
            result = false;
        } else {

            result = charactersCheck(word1, word2);
        }


        return result;
    }

  private boolean charactersCheck (String word1, String word2)   {
        boolean result = true;
      Map<Character, Integer> mapOfCharacters = new HashMap<>();
      for (int i = 0; i < word1.length(); i++) {
          char currentChar = word1.charAt(i);
          if (mapOfCharacters.containsKey(currentChar)) {
              mapOfCharacters.put(currentChar, mapOfCharacters.get(currentChar) + 1);
          } else {
              mapOfCharacters.put(currentChar, 1);
          }
      }

      for (int i = 0; i < word2.length(); i++) {
          char currentChar = word2.charAt(i);
          if (!mapOfCharacters.containsKey(currentChar)) {
              break;
          } else {
              if(mapOfCharacters.get(currentChar) > 1) {
                  mapOfCharacters.put(currentChar, mapOfCharacters.get(currentChar) - 1);
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



}
