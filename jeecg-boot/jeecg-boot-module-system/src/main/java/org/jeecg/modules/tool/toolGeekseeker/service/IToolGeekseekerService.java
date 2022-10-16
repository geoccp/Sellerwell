package org.jeecg.modules.tool.toolGeekseeker.service;

import org.jeecg.modules.tool.toolGeekseeker.entity.ToolGeekseeker;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: tool_geekseeker
 * @Author: jeecg-boot
 * @Date:   2021-12-11
 * @Version: V1.0
 */
public interface IToolGeekseekerService extends IService<ToolGeekseeker> {
    public boolean deleteTable();
}
