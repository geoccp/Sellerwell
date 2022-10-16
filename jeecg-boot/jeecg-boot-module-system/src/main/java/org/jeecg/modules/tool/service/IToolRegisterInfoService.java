package org.jeecg.modules.tool.service;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.tool.entity.ToolRegisterInfo;

/**
 * @Description: tool_register_info
 * @Author: jeecg-boot
 * @Date:   2021-10-31
 * @Version: V1.0
 */
public interface IToolRegisterInfoService extends IService<ToolRegisterInfo> {
    /**
     * 初始化注册用户信息
     * 1、如果该用户已经注册，返回可用天数
     * 2、如果该用户没有注册，则添加注册信息，默认可用使用1天
     * @param userId
     * @param toolSign
     * @return
     */
    Integer  queryByUseId(String userId,String toolSign);
}
