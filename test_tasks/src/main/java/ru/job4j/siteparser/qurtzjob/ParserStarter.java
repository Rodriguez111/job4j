package ru.job4j.siteparser.qurtzjob;

import org.quartz.*;

import org.quartz.impl.StdSchedulerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.siteparser.utils.PropertiesManager;
import ru.job4j.siteparser.args.ArgManager;

import java.text.ParseException;

public class ParserStarter {
    private static final Logger LOG = LoggerFactory.getLogger(ParserStarter.class.getName());

    public static void main(String[] args) throws SchedulerException, ParseException {
        ArgManager argManager = new ArgManager(args);
        PropertiesManager.setPropertiesFile(argManager.getPropertiesFile());


        JobDetail jobDetail = JobBuilder.newJob(QuartzJob.class).build();

        CronExpression daily = new CronExpression("0 0 12 1/1 * ? *");
        CronExpression minutely = new CronExpression("0 0/1 * 1/1 * ? *");
        CronExpression cronTime = new CronExpression(PropertiesManager.getTime());


        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cronTime);
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("DailyTrigger").withSchedule(cronScheduleBuilder).build();

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();
        scheduler.scheduleJob(jobDetail, trigger);
    }
}
