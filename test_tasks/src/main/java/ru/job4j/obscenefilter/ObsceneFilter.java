package ru.job4j.obscenefilter;

/**
 * Дается список-1 с фразами - слова, разделенные пробелами
 * Дается список-2 - слова, подлежащие цензуре
 * С помощью Stream API нужно вернуть список фраз, из которых вырезаны все нецензурные слова.
 */

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ObsceneFilter {

    public List<String> process(List<String> stringsToCensor, List<String> obsceneWords) {
        List<String>  result = stringsToCensor.stream()
                .map(eachLine -> Stream.of(eachLine.split(" "))
                        .filter(a -> !obsceneWords.contains(a))
                        .collect(Collectors.joining(" ")))
                .collect(Collectors.toList());
        return result;
    }

}
