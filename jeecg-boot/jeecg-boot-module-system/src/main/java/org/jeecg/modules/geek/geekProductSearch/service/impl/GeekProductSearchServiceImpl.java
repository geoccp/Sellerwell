package org.jeecg.modules.geek.geekProductSearch.service.impl;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.util.DateUtils;
import org.jeecg.common.util.GeekUtils;
import org.jeecg.common.util.RestUtil;
import org.jeecg.modules.geek.geekParInfo.service.IGeekParInfoService;
import org.jeecg.modules.geek.geekProductSearch.entity.GeekProductSearch;
import org.jeecg.modules.geek.geekProductSearch.mapper.GeekProductSearchMapper;
import org.jeecg.modules.geek.geekProductSearch.service.IGeekProductSearchService;
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
 * @Description: geek_product_search
 * @Author: jeecg-boot
 * @Date: 2022-01-23
 * @Version: V1.0
 */
@Slf4j
@Service
public class GeekProductSearchServiceImpl extends ServiceImpl<GeekProductSearchMapper, GeekProductSearch> implements IGeekProductSearchService {
    @Autowired
    private GeekProductSearchMapper productSearchMapper;
    @Autowired
    private IGeekParInfoService geekParInfoService;

    public boolean deleteTable() {
        return productSearchMapper.deleteTable();
    }


    @Override
    public void deleteOtherInfo() {
        productSearchMapper.deleteOtherInfo();
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
            param.put("product_status_lef", "正常");
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
                List<GeekProductSearch> list1 = new ArrayList<>();
                for (Map m : list) {
                    GeekProductSearch geekProductSearch = new GeekProductSearch();
                    geekProductSearch.setAAndQ(GeekUtils.toString(m.get("a_and_q")));
                    geekProductSearch.setAdFlag(GeekUtils.toString(m.get("ad_flag")));
                    geekProductSearch.setBadCommentRate(GeekUtils.toString(m.get("bad_comment_rate")));
                    geekProductSearch.setBrandName(GeekUtils.toString(m.get("brand_name")));
                    geekProductSearch.setClassification(GeekUtils.toString(m.get("classification")));
                    geekProductSearch.setComment(GeekUtils.toString(m.get("comment")));
                    geekProductSearch.setDiscount(GeekUtils.toString(m.get("discount")));
                    geekProductSearch.setDistribution(GeekUtils.toString(m.get("distribution")));
                    geekProductSearch.setGoodCommentRate(GeekUtils.toString(m.get("good_comment_rate")));
                    geekProductSearch.setImgIndex(GeekUtils.toString(m.get("img_index")));
                    geekProductSearch.setItemid(GeekUtils.toString(m.get("itemId")));
                    geekProductSearch.setPath(GeekUtils.toString(m.get("path")));
                    geekProductSearch.setPathTzh(GeekUtils.toString(m.get("path_tzh")));
                    geekProductSearch.setPrice(GeekUtils.toString(m.get("price")));
                    geekProductSearch.setProductId(GeekUtils.toString(m.get("product_id")));
                    geekProductSearch.setProductStatus(GeekUtils.toString(m.get("product_status")));
//
                    geekProductSearch.setStrRank(GeekUtils.toString(m.get("rank")));
                    geekProductSearch.setRankType(GeekUtils.toString(m.get("rank_type")));
                    geekProductSearch.setRocket(GeekUtils.toString(m.get("rocket")));

                    geekProductSearch.setSellerCount(GeekUtils.toString(m.get("seller_count")));
                    try {
                        if (m.get("shelf_time") != null && m.get("shelf_time") != "") {
                            Long datel = ((Double) m.get("shelf_time")).longValue() * 1000;
                            geekProductSearch.setShelfTime(DateUtils.getDate(datel));
                        }
                    } catch (Exception e) {

                    }
                    String[] paths = geekProductSearch.getPathTzh().split("->");
                    if (paths.length > 0)
                        geekProductSearch.setPath1(paths[0]);
                    if (paths.length > 1)
                        geekProductSearch.setPath2(paths[1]);
                    if (paths.length > 2)
                        geekProductSearch.setPath3(paths[2]);
                    if (paths.length > 3)
                        geekProductSearch.setPath4(paths[3]);
                    if (paths.length > 4)
                        geekProductSearch.setPath5(paths[4]);

                    geekProductSearch.setStar(GeekUtils.toString(m.get("star")));
                    geekProductSearch.setState(GeekUtils.toString(m.get("state")));
                    geekProductSearch.setTitle(GeekUtils.toString(m.get("title")));
                    geekProductSearch.setUrl(GeekUtils.toString(m.get("url")));
                    geekProductSearch.setVariableCount(GeekUtils.toString(m.get("variable_count")));
                    list1.add(geekProductSearch);
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
