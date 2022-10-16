package org.jeecg.modules.tool.toolGeekseeker.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.tool.toolGeekseeker.entity.ToolGeekseeker;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

/**
 * @Description: tool_geekseeker
 * @Author: jeecg-boot
 * @Date:   2021-12-11
 * @Version: V1.0
 */
@Component
public interface ToolGeekseekerMapper extends BaseMapper<ToolGeekseeker> {

    public boolean deleteTable();
}
