package org.jeecg.modules.geek.geekProductSearch.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.geek.geekProductSearch.entity.GeekProductSearch;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

/**
 * @Description: geek_product_search
 * @Author: jeecg-boot
 * @Date:   2022-01-23
 * @Version: V1.0
 */
@Component
public interface GeekProductSearchMapper extends BaseMapper<GeekProductSearch> {
    public boolean deleteTable();

    public  void  deleteOtherInfo();
}
