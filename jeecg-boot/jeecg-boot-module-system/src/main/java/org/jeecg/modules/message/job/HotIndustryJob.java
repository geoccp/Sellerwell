package org.jeecg.modules.message.job;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.geek.geekAllProductSearch.service.IGeekAllProductSearchService;
import org.jeecg.modules.geek.geekIndustrySearch.service.IGeekIndustrySearchService;
import org.jeecg.modules.geek.geekProductSearch.service.IGeekProductSearchService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class HotIndustryJob implements Job {

    @Autowired
    private IGeekProductSearchService productSearchService;
    @Autowired
    private IGeekIndustrySearchService industrySearchService;
    @Autowired
    private IGeekAllProductSearchService geekAllProductSearchService;


    @SneakyThrows
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        industrySearchService.industrySearch();
        productSearchService.productSearch();
        geekAllProductSearchService.productSearch();
    }

}
