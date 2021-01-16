package tech.geekcity.application.doit;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class WordCount {
    public static void main(String[] args) throws IOException {
        File dataFile = File.createTempFile("ben.wangz.", ".data");
        dataFile.deleteOnExit();
        TaskManager taskManager = TaskManager.Builder.newInstance()
                .dataFile(dataFile)
                .build();
        taskManager.configure();
        taskManager.add(Task.Builder.newInstance()
                .title("this is a title")
                .description("no description")
                .timestampInMs(System.currentTimeMillis())
                .unit(TimeUnit.DAYS)
                .currentPeriod(1)
                .build());
        taskManager.flush();
        System.out.println(FileUtils.readFileToString(dataFile));
    }
}
