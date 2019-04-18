package ru.job4j.siteparser.qurtzjob;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import ru.job4j.siteparser.SiteParser;
import ru.job4j.siteparser.Vacancy;
import ru.job4j.siteparser.sql.SQLConnection;
import ru.job4j.siteparser.sql.SQLManager;

import java.util.List;

public class QuartzJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        SiteParser siteParser = new SiteParser();
        SQLManager sqlManager = new SQLManager(SQLConnection.createConnection());
        sqlManager.addEntryList(siteParser.scanPagesAndGetVacancies());
    }
}
