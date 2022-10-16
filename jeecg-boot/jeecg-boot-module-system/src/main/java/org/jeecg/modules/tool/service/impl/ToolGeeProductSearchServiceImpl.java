package org.jeecg.modules.tool.service.impl;

import org.jeecg.modules.tool.entity.ToolGeeProductSearch;
import org.jeecg.modules.tool.mapper.ToolGeeProductSearchMapper;
import org.jeecg.modules.tool.service.IToolGeeProductSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: tool_gee_product_search
 * @Author: jeecg-boot
 * @Date:   2021-11-21
 * @Version: V1.0
 */
@Service
public class ToolGeeProductSearchServiceImpl extends ServiceImpl<ToolGeeProductSearchMapper, ToolGeeProductSearch> implements IToolGeeProductSearchService {
    @Autowired
    ToolGeeProductSearchMapper toolGeeProductSearchMapper;

    @Override
    public boolean deleteTable() {
        return toolGeeProductSearchMapper.deleteTable();
    }
}
