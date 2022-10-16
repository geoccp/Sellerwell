package org.jeecg.modules.geek.geekAllProductSearch.service.impl;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.util.DateUtils;
import org.jeecg.common.util.GeekUtils;
import org.jeecg.common.util.RestUtil;
import org.jeecg.modules.geek.geekAllProductSearch.entity.GeekAllProductSearch;
import org.jeecg.modules.geek.geekAllProductSearch.mapper.GeekAllProductSearchMapper;
import org.jeecg.modules.geek.geekAllProductSearch.service.IGeekAllProductSearchService;
import org.jeecg.modules.geek.geekParInfo.service.IGeekParInfoService;
import org.jeecg.modules.geek.geekProductSearch.entity.GeekProductSearch;
import org.jeecg.modules.geek.geekProductSearch.mapper.GeekProductSearchMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description: geek_all_product_search
 * @Author: jeecg-boot
 * @Date:   2022-05-23
 * @Version: V1.0
 */
@Slf4j
@Service
public class GeekAllProductSearchServiceImpl extends ServiceImpl<GeekAllProductSearchMapper, GeekAllProductSearch> implements IGeekAllProductSearchService {

    @Autowired
    private GeekAllProductSearchMapper allProductSearchMapper;
    @Autowired
    private IGeekParInfoService geekParInfoService;

    public boolean deleteTable() {
        return allProductSearchMapper.deleteTable();
    }


    @Override
    public void deleteOtherInfo() {
        allProductSearchMapper.deleteOtherInfo();
    }

    @Override
    public void productSearch() throws InterruptedException {
        JSONObject param = new JSONObject();
        Date startDate = new Date();
        String tocken = geekParInfoService.getAuthorization("1525700243692216321");
        String searchUrl = "https://geek-api-python.sellerwell.com/api/product/Search_V1.1";
        Integer current = 1;
        List<Map> list = new ArrayList<>();
        this.deleteTable();
        do {
            param = new JSONObject();
            param.put("current", current++);
            param.put("pageSize", 100);
            param.put("classification", "");
            param.put("brand_name_lef", "否");
            param.put("seller_type_lef", "跨境店");
            param.put("distribution_lef", "自配送");
            param.put("shelf_time_right", "3");
//            param.put("star_lef", "4.1");
//            param.put("star_right", "5.0");
            HttpHeaders headers = RestUtil.getHeaderApplicationJson();
//            headers.set("authorization", String.format("Bearer %s", tocken));
            headers.set("authorization", tocken);
            Integer i = 0;
            while (true) {
                try {
                    JSONObject object = RestUtil.request(searchUrl, HttpMethod.POST, headers, null, param, JSONObject.class).getBody();
                    list = (List) ((Map) object.get("data")).get("list");
                    break;
                } catch (Exception e) {
                    log.info(String.format("第%s次异常", i++));
                    Thread.sleep(5000l);
                    if (i > 5) {
//                        tocken = geekParInfoService.getAuthorization("1525700243692216321");
                        headers = RestUtil.getHeaderApplicationJson();
                        headers.set("authorization", tocken);
//                        headers.set("authorization", String.format("Bearer %s", tocken));
                        i = 0;
                    }
                    continue;
                }
            }

            if (list.size() > 0) {
                List<GeekAllProductSearch> list1 = new ArrayList<>();
                for (Map m : list) {
                    GeekAllProductSearch geekAllProductSearch = new GeekAllProductSearch();
                    geekAllProductSearch.setAAndQ(GeekUtils.toString(m.get("a_and_q")));
                    geekAllProductSearch.setAdFlag(GeekUtils.toString(m.get("ad_flag")));
                    geekAllProductSearch.setBadCommentRate(GeekUtils.toString(m.get("bad_comment_rate")));
                    geekAllProductSearch.setBrandName(GeekUtils.toString(m.get("brand_name")));
                    geekAllProductSearch.setClassification(GeekUtils.toString(m.get("classification")));
                    geekAllProductSearch.setComment(GeekUtils.toString(m.get("comment")));
                    geekAllProductSearch.setDiscount(GeekUtils.toString(m.get("discount")));
                    geekAllProductSearch.setDistribution(GeekUtils.toString(m.get("distribution")));
                    geekAllProductSearch.setGoodCommentRate(GeekUtils.toString(m.get("good_comment_rate")));
                    geekAllProductSearch.setImgIndex(GeekUtils.toString(m.get("img_index")));
                    geekAllProductSearch.setItemid(GeekUtils.toString(m.get("itemId")));
                    geekAllProductSearch.setPath(GeekUtils.toString(m.get("path")));
                    geekAllProductSearch.setPathTzh(GeekUtils.toString(m.get("path_tzh")));
                    geekAllProductSearch.setPrice(GeekUtils.toString(m.get("price")));
                    geekAllProductSearch.setProductId(GeekUtils.toString(m.get("product_id")));
                    geekAllProductSearch.setProductStatus(GeekUtils.toString(m.get("product_status")));
//
                    geekAllProductSearch.setStrRank(GeekUtils.toString(m.get("rank")));
                    geekAllProductSearch.setRankType(GeekUtils.toString(m.get("rank_type")));
                    geekAllProductSearch.setRocket(GeekUtils.toString(m.get("rocket")));

                    geekAllProductSearch.setSellerCount(GeekUtils.toString(m.get("seller_count")));
                    try {
                        if (m.get("shelf_time") != null && m.get("shelf_time") != "") {
                            Long datel = ((Double) m.get("shelf_time")).longValue() * 1000;
                            geekAllProductSearch.setShelfTime(DateUtils.getDate(datel));
                        }
                    } catch (Exception e) {

                    }
                    String[] paths = geekAllProductSearch.getPathTzh().split("->");
                    if (paths.length > 0)
                        geekAllProductSearch.setPath1(paths[0]);
                    if (paths.length > 1)
                        geekAllProductSearch.setPath2(paths[1]);
                    if (paths.length > 2)
                        geekAllProductSearch.setPath3(paths[2]);
                    if (paths.length > 3)
                        geekAllProductSearch.setPath4(paths[3]);
                    if (paths.length > 4)
                        geekAllProductSearch.setPath5(paths[4]);

                    geekAllProductSearch.setStar(GeekUtils.toString(m.get("star")));
                    geekAllProductSearch.setState(GeekUtils.toString(m.get("state")));
                    geekAllProductSearch.setTitle(GeekUtils.toString(m.get("title")));
                    geekAllProductSearch.setUrl(GeekUtils.toString(m.get("url")));
                    geekAllProductSearch.setVariableCount(GeekUtils.toString(m.get("variable_count")));
                    list1.add(geekAllProductSearch);
                }
                this.saveBatch(list1);
                log.info(String.format("顺利完成第%s页", current));
            }

        } while (list.size() > 0 && current < 500);
        //删除 食物、医疗、保健
        this.deleteOtherInfo();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        log.info(String.format("产品搜索,开始时间：%s-%s", df.format(startDate), df.format(new Date())));
    }

}
