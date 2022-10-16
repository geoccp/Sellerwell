package org.jeecg.modules.message.job;

import lombok.SneakyThrows;
import org.jeecg.modules.geek.geekHotIndexNew.controller.GeekHotIndexNewController;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;

public class GeekCategoryJob implements Job {
@Autowired
private GeekHotIndexNewController geekHotIndexNewController;



    @SneakyThrows
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        geekHotIndexNewController.saveGeekHotIndex(null);
        geekHotIndexNewController.createStatViewTable();
    }

}
