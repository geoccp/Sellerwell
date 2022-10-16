package org.jeecg.modules.geek.geekParInfo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.jeecg.modules.geek.geekParInfo.entity.GeekParInfo;
import org.jeecg.modules.geek.geekParInfo.mapper.GeekParInfoMapper;
import org.jeecg.modules.geek.geekParInfo.service.IGeekParInfoService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: geek_par_info
 * @Author: jeecg-boot
 * @Date: 2022-05-19
 * @Version: V1.0
 */
@Service
public class GeekParInfoServiceImpl extends ServiceImpl<GeekParInfoMapper, GeekParInfo> implements IGeekParInfoService {
    @Override
    public String getAuthorization(String id) {
        LambdaQueryWrapper<GeekParInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(GeekParInfo::getId, id);
        return this.list(queryWrapper).get(0).getStrAuthorization();
//        return "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NTM1NzQxMDAsInN1YiI6Ilx1NzUyOFx1NjIzN1x1NzY3Ylx1NWY1NVx1NjM4OFx1Njc0MyIsIm5iZiI6MTY1Mjk2OTMwMCwiYXVkIjoxMDIwNzQyMiwiaWF0IjoxNjUyOTY5MzAwLCJqdGkiOiJmMTEwODBmM2U3NTA4YmE2YjdkN2Y1YjM0Y2IxNzIwZiIsImlzcyI6IlNlbGxlcldlbGwiLCJkYXRhIjp7InBhcmVudF9pZCI6MTAyMDc0MjIsInVzZXJfbmFtZSI6IjE3NzIwNDkxNjA4Iiwib2ZmbGluZV90eXBlIjoxLCJhZ2VudF9pZCI6MywiYWNjb3VudF90eXBlIjoxfX0.AjxOlBbb91qeRKkCrH1xJNHifKu2YqLm7A2CCNIRgQ0";
    }
}
