package ru.job4j.filesearch;


import java.nio.file.DirectoryStream;
import java.nio.file.Path;

public class SearchType {

    private String searchPattern;

    public SearchType(String searchPattern) {
        this.searchPattern = searchPattern;
    }

    private final DirectoryStream.Filter<Path> fullName = (path) ->
            path.getFileName().toString().equals(searchPattern);

    private final  DirectoryStream.Filter<Path> mask = (path) ->
         path.getFileName().toString().toLowerCase().contains(searchPattern.toLowerCase());


    private final DirectoryStream.Filter<Path> regular = (path) ->
            path.getFileName().toString().matches(searchPattern);



    public DirectoryStream.Filter<Path> getFullName() {
        return fullName;
    }

    public DirectoryStream.Filter<Path> getMask() {
        return mask;
    }

    public DirectoryStream.Filter<Path> getRegular() {
        return regular;
    }
}
