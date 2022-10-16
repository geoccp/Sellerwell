package org.jeecg.modules.tool.toolGeekseeker.service.impl;

import org.jeecg.modules.tool.toolGeekseeker.entity.ToolGeekseeker;
import org.jeecg.modules.tool.toolGeekseeker.mapper.ToolGeekseekerMapper;
import org.jeecg.modules.tool.toolGeekseeker.service.IToolGeekseekerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: tool_geekseeker
 * @Author: jeecg-boot
 * @Date:   2021-12-11
 * @Version: V1.0
 */
@Service
public class ToolGeekseekerServiceImpl extends ServiceImpl<ToolGeekseekerMapper, ToolGeekseeker> implements IToolGeekseekerService {
    @Autowired
    private  ToolGeekseekerMapper toolGeekseekerMapper;
    @Override
    public boolean deleteTable() {
       return toolGeekseekerMapper.deleteTable();
    }
}
