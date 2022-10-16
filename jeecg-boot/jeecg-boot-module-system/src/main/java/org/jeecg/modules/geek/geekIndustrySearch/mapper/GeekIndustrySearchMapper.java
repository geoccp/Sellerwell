package org.jeecg.modules.geek.geekIndustrySearch.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.geek.geekIndustrySearch.entity.GeekIndustrySearch;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

/**
 * @Description: geek_industry_search
 * @Author: jeecg-boot
 * @Date:   2022-01-23
 * @Version: V1.0
 */
@Component
public interface GeekIndustrySearchMapper extends BaseMapper<GeekIndustrySearch> {
    public boolean deleteTable();

    public  void  deleteOtherInfo();
}
