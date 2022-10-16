package org.jeecg.modules.geek.geekAllProductSearch.service;

import org.jeecg.modules.geek.geekAllProductSearch.entity.GeekAllProductSearch;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: geek_all_product_search
 * @Author: jeecg-boot
 * @Date:   2022-05-23
 * @Version: V1.0
 */
public interface IGeekAllProductSearchService extends IService<GeekAllProductSearch> {
    public boolean deleteTable();

    public  void  deleteOtherInfo();

    /**
     * 商品数据爬取
     */
    public void productSearch() throws InterruptedException;
}
