package org.jeecg.modules.geek.geekAllProductSearch.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.geek.geekAllProductSearch.entity.GeekAllProductSearch;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

/**
 * @Description: geek_all_product_search
 * @Author: jeecg-boot
 * @Date:   2022-05-23
 * @Version: V1.0
 */
@Component
public interface GeekAllProductSearchMapper extends BaseMapper<GeekAllProductSearch> {
    public boolean deleteTable();

    public  void  deleteOtherInfo();
}
