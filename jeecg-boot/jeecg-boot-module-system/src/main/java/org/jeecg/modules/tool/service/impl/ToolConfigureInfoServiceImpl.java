package org.jeecg.modules.tool.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.jeecg.modules.tool.entity.ToolConfigureInfo;
import org.jeecg.modules.tool.entity.ToolRegisterInfo;
import org.jeecg.modules.tool.mapper.ToolConfigureInfoMapper;
import org.jeecg.modules.tool.service.IToolConfigureInfoService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: tool_configure_info
 * @Author: jeecg-boot
 * @Date:   2021-10-31
 * @Version: V1.0
 */
@Service
public class ToolConfigureInfoServiceImpl extends ServiceImpl<ToolConfigureInfoMapper, ToolConfigureInfo> implements IToolConfigureInfoService {

    @Override
    public ToolConfigureInfo queryByToolSign(String toolSign) {
        LambdaQueryWrapper<ToolConfigureInfo> query =
                new QueryWrapper<ToolConfigureInfo>().lambda().eq(ToolConfigureInfo::getToolSign,toolSign);
        List<ToolConfigureInfo> list= this.list(query);
        if(list.size()>0)
            return list.get(0);
        else
            return null;
    }
}
