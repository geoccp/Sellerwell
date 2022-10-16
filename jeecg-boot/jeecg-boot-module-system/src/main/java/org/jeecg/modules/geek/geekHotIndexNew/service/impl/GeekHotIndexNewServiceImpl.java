package org.jeecg.modules.geek.geekHotIndexNew.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.jeecg.common.util.DateUtils;
import org.jeecg.modules.geek.geekHotIndexNew.entity.GeekHotIndexNew;
import org.jeecg.modules.geek.geekHotIndexNew.mapper.GeekHotIndexNewMapper;
import org.jeecg.modules.geek.geekHotIndexNew.service.IGeekHotIndexNewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.text.ParseException;
import java.util.Date;

/**
 * @Description: geek_hot_index_new
 * @Author: jeecg-boot
 * @Date: 2022-05-09
 * @Version: V1.0
 */
@Service
public class GeekHotIndexNewServiceImpl extends ServiceImpl<GeekHotIndexNewMapper, GeekHotIndexNew> implements IGeekHotIndexNewService {
    @Autowired
    private GeekHotIndexNewMapper geekHotIndexNewMapper;

    @Override
    public Boolean isExistDate(String strDate) throws ParseException {
        LambdaQueryWrapper<GeekHotIndexNew> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(GeekHotIndexNew::getDate, DateUtils.parseDate(strDate, "yyyy-MM-dd"));
        if (this.list(queryWrapper).size() > 0)
            return true;
        else
            return false;
    }

    @Override
    public void clearData(String strYYMMDD) {
        geekHotIndexNewMapper.delete3Date(strYYMMDD);
        geekHotIndexNewMapper.keepSevenDaysData();
    }

    @Override
    public Boolean isExistByProdectID(String productId, String strYYMMDD) throws ParseException {
        LambdaQueryWrapper<GeekHotIndexNew> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(GeekHotIndexNew::getDate, DateUtils.parseDate(strYYMMDD, "yyyy-MM-dd"))
                .eq(GeekHotIndexNew::getProductId, productId);
        if (this.list(queryWrapper).size() > 0)
            return true;
        else
            return false;
    }

    @Override
    public void createStatViewTable() {
        try {
            geekHotIndexNewMapper.dropGeekView();
        } catch (Exception e) {

        }
        geekHotIndexNewMapper.createTableViewGeek();
    }
}
