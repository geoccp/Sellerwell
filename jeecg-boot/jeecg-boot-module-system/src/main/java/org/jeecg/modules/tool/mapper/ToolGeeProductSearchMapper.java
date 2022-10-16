package org.jeecg.modules.tool.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.tool.entity.ToolGeeProductSearch;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

/**
 * @Description: tool_gee_product_search
 * @Author: jeecg-boot
 * @Date: 2021-11-21
 * @Version: V1.0
 */
@Component
public interface ToolGeeProductSearchMapper extends BaseMapper<ToolGeeProductSearch> {
    boolean deleteTable();
}
