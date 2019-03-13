package ru.job4j.filesearch;


import java.nio.file.*;
import java.util.*;

public class Args {
    private static String roodDir;
    private static String searchPattern;
    private static String searchPatternType;
    private static String outputFilePath;

    private final Map<String, DirectoryStream.Filter<Path>> searchTypes = new HashMap<>();

    private final FileSearch fileSearch;

    public Args() {
        initSearchType();
        this.fileSearch = new FileSearch(this);
    }

    private void initSearchType() {
        SearchType searchType = new SearchType(searchPattern);
        searchTypes.put("f", searchType.getFullName());
        searchTypes.put("m", searchType.getMask());
        searchTypes.put("r", searchType.getRegular());    }


    public String getRoodDir() {
        return roodDir;
    }

    public String getSearchPatternType() {
        return searchPatternType;
    }

    public String getSearchPattern() {
        return searchPattern;
    }

    public String getOutputFilePath() {
        return outputFilePath;
    }

    public Map<String, DirectoryStream.Filter<Path>> getSearchTypes() {
        return searchTypes;
    }

    public static void main(String[] args) {
        ArgsManager argsManager = new ArgsManager(args);
        Args.roodDir = argsManager.getRootDir();
        Args.searchPattern = argsManager.getSearchPattern();
        Args.searchPatternType = argsManager.getSearchPatternType();
        Args.outputFilePath = argsManager.getOutputFilePath();
    }
}
