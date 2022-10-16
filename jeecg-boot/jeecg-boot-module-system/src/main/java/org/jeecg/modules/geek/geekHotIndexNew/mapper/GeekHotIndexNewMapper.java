package org.jeecg.modules.geek.geekHotIndexNew.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.geek.geekHotIndexNew.entity.GeekHotIndexNew;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

/**
 * @Description: geek_hot_index_new
 * @Author: jeecg-boot
 * @Date:   2022-05-09
 * @Version: V1.0
 */
@Component
public interface GeekHotIndexNewMapper extends BaseMapper<GeekHotIndexNew> {
    public  void delete3Date(@Param("strYYMMDD")  String strYYMMDD);

    public void keepSevenDaysData();

    /**
     * 创建统计表
     */
    public void dropGeekView();
    public void createTableViewGeek();
}
