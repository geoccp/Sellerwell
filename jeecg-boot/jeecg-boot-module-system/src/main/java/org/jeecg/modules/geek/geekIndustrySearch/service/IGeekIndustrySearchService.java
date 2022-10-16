package org.jeecg.modules.geek.geekIndustrySearch.service;

import org.jeecg.modules.geek.geekIndustrySearch.entity.GeekIndustrySearch;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: geek_industry_search
 * @Author: jeecg-boot
 * @Date:   2022-01-23
 * @Version: V1.0
 */
public interface IGeekIndustrySearchService extends IService<GeekIndustrySearch> {
    public boolean deleteTable();

    public  void  deleteOtherInfo();

    /**
     * 商品数据爬取
     */
    public void industrySearch() throws InterruptedException;

}
