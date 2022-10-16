package org.jeecg.modules.geek.geekProductSearch.service;

import org.jeecg.modules.geek.geekProductSearch.entity.GeekProductSearch;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: geek_product_search
 * @Author: jeecg-boot
 * @Date:   2022-01-23
 * @Version: V1.0
 */
public interface IGeekProductSearchService extends IService<GeekProductSearch> {
    public boolean deleteTable();

    public  void  deleteOtherInfo();

    /**
     * 商品数据爬取
     */
    public void productSearch() throws InterruptedException;
}
