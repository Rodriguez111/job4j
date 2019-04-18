package ru.job4j.siteparser.args;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.filesearch.argparser.RCommandManager;
import ru.job4j.filesearch.argparser.exceptions.ArgumentException;
import ru.job4j.siteparser.qurtzjob.ParserStarter;

import java.io.File;
import java.io.FileNotFoundException;

public class ArgManager {
    private final String[] args;
    private static final Logger LOG = LoggerFactory.getLogger(ArgManager.class.getName());

    public ArgManager(String[] args) {
        this.args = args;
    }


    public File getPropertiesFile() {
        checkArgument();
        File file = new File(args[0]);
        try {
            checkFile(file);
        } catch (FileNotFoundException e) {
            LOG.error("File with properties not found");
        }
        return file;
    }

    private void checkArgument() {
        if (args.length == 0) {
            throw new ArgumentException("Must have argument with properties file path");
        }
    }

    private void checkFile(File file) throws FileNotFoundException {
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
    }


}
