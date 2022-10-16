package org.jeecg.modules.geek.geekParInfo.service;

import org.jeecg.modules.geek.geekParInfo.entity.GeekParInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: geek_par_info
 * @Author: jeecg-boot
 * @Date:   2022-05-19
 * @Version: V1.0
 */
public interface IGeekParInfoService extends IService<GeekParInfo> {

    public String getAuthorization(String id);
}
