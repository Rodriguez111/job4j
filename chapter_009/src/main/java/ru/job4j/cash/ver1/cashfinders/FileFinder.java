package ru.job4j.cash.ver1.cashfinders;

import ru.job4j.cash.ver1.exceptions.FileNotExistsException;

import java.io.File;
import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;

/**
 * Search file in ru.job4j.cash by filename.
 */

public class FileFinder implements BiFunction<String, Map<String, SoftReference<File>>, Optional<File>> {
    /**
     * Searching file in the map. If file exists - return it. If not - throws exception.
     * @param searchKey - file name to search.
     * @param softReferenceMap - map with cashed files.
     * @return - file which matches searchKey.
     */
    @Override
    public Optional<File> apply(String searchKey, Map<String, SoftReference<File>> softReferenceMap) {
        if (!softReferenceMap.containsKey(searchKey)) {
            throw new FileNotExistsException("File with such name does not exist.");
        }
        return Optional.ofNullable(softReferenceMap.get(searchKey).get());
    }
}
