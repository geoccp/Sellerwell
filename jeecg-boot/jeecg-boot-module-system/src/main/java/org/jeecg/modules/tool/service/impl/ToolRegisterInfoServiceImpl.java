package org.jeecg.modules.tool.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.poi.ss.formula.functions.T;
import org.jeecg.common.util.DateUtils;
import org.jeecg.modules.system.entity.SysRolePermission;
import org.jeecg.modules.tool.entity.ToolRegisterInfo;
import org.jeecg.modules.tool.mapper.ToolRegisterInfoMapper;
import org.jeecg.modules.tool.service.IToolRegisterInfoService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;
import java.util.List;

/**
 * @Description: tool_register_info
 * @Author: jeecg-boot
 * @Date:   2021-10-31
 * @Version: V1.0
 */
@Service
public class ToolRegisterInfoServiceImpl extends ServiceImpl<ToolRegisterInfoMapper, ToolRegisterInfo> implements IToolRegisterInfoService {


    @Override
    public Integer queryByUseId(String userId, String toolSign) {
        LambdaQueryWrapper<ToolRegisterInfo> query =
                new QueryWrapper<ToolRegisterInfo>().lambda().eq(ToolRegisterInfo::getUserId,
                        userId).eq(ToolRegisterInfo::getToolSign,toolSign);
      List<ToolRegisterInfo> list= this.list(query);
      if(list.size()>0){
          return  DateUtils.dateDiff('d',list.get(0).getEndTime(),new Date())+1;
      }
      else{
          ToolRegisterInfo toolRegisterInfo=new ToolRegisterInfo();
          toolRegisterInfo.setUserId(userId);
          toolRegisterInfo.setToolSign(toolSign);
          toolRegisterInfo.setStarTime(DateUtils.getDate());
          toolRegisterInfo.setEndTime(DateUtils.getDate());
          this.save(toolRegisterInfo);
          return  1;
      }
    }
}
