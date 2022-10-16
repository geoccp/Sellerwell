package org.jeecg.modules.geek.geekHotIndexNew.service;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.geek.geekHotIndexNew.entity.GeekHotIndexNew;
import com.baomidou.mybatisplus.extension.service.IService;

import java.text.ParseException;
import java.util.Date;

/**
 * @Description: geek_hot_index_new
 * @Author: jeecg-boot
 * @Date:   2022-05-09
 * @Version: V1.0
 */
public interface IGeekHotIndexNewService extends IService<GeekHotIndexNew> {

    public Boolean isExistDate(String strDate) throws ParseException;


    /**
     * 数据清洗
     * 1、删除当前最近三天的数据，导入新数据覆盖
     * 2、保持数据为最近7天的，七天的数据删除
     * @param strYYMMDD
     */
    public  void clearData(String strYYMMDD);

    /**
     * 判断当前日期该产品是否存在
     * @param productId
     * @return
     */
    public Boolean isExistByProdectID(String productId,String strYYMMDD) throws ParseException;

    public void createStatViewTable();
}
