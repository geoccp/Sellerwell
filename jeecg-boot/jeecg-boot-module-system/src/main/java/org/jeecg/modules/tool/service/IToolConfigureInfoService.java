package org.jeecg.modules.tool.service;

import org.jeecg.modules.tool.entity.ToolConfigureInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: tool_configure_info
 * @Author: jeecg-boot
 * @Date:   2021-10-31
 * @Version: V1.0
 */
public interface IToolConfigureInfoService extends IService<ToolConfigureInfo> {
 ToolConfigureInfo queryByToolSign(String toolSign);
}
