package org.jeecg.modules.tool.service;

import org.jeecg.modules.tool.entity.ToolGeeProductSearch;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: tool_gee_product_search
 * @Author: jeecg-boot
 * @Date:   2021-11-21
 * @Version: V1.0
 */
public interface IToolGeeProductSearchService extends IService<ToolGeeProductSearch> {
    public boolean deleteTable();
}
