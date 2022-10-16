package org.jeecg.modules.geek.geekIndustrySearch.service.impl;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.util.DateUtils;
import org.jeecg.common.util.GeekUtils;
import org.jeecg.common.util.RestUtil;
import org.jeecg.modules.geek.geekIndustrySearch.entity.GeekIndustrySearch;
import org.jeecg.modules.geek.geekIndustrySearch.mapper.GeekIndustrySearchMapper;
import org.jeecg.modules.geek.geekIndustrySearch.service.IGeekIndustrySearchService;
import org.jeecg.modules.geek.geekParInfo.service.IGeekParInfoService;
import org.jeecg.modules.geek.geekProductSearch.entity.GeekProductSearch;
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
 * @Description: geek_industry_search
 * @Author: jeecg-boot
 * @Date: 2022-01-23
 * @Version: V1.0
 */
@Slf4j
@Service
public class GeekIndustrySearchServiceImpl extends ServiceImpl<GeekIndustrySearchMapper, GeekIndustrySearch> implements IGeekIndustrySearchService {
    @Autowired
    private GeekIndustrySearchMapper industrySearchMapper;
    @Autowired
    private IGeekParInfoService geekParInfoService;

    public boolean deleteTable() {
        return industrySearchMapper.deleteTable();
    }

    @Override
    public void deleteOtherInfo() {
        industrySearchMapper.deleteOtherInfo();
    }

    @Override
    public void industrySearch() throws InterruptedException {
        JSONObject param = new JSONObject();
        Date startDate = new Date();
        String tocken = geekParInfoService.getAuthorization("1525700243692216321");
        String searchUrl = "https://geek-api-python.sellerwell.com/api/industry/hotSearch";
        Integer current = 1;
        List<Map> list = new ArrayList<>();
        this.deleteTable();
//        do {
        param = new JSONObject();
        param.put("current", current++);
        param.put("pageSize", 1000);
        param.put("product_total_lef", 0);
        param.put("product_total_right", 500);

        HttpHeaders headers = RestUtil.getHeaderApplicationJson();
        headers.set("authorization", String.format("%s", tocken));
        Integer i = 0;
        Integer product_total_lef = 0;
        Integer product_total_right = 500;
        while (true) {
            try {
                param.remove("product_total_lef");
                param.remove("product_total_right");
                param.put("product_total_lef", product_total_lef);
                param.put("product_total_right", product_total_right);
                JSONObject object = RestUtil.request(searchUrl, HttpMethod.POST, headers, null, param, JSONObject.class).getBody();
                String message = object.get("msg").toString().split("，")[0];
                message = message.replace("共找到", "").replace("个品类", "");
                if (Integer.parseInt(message) == 0) {
                    if (product_total_right > 10000000)
                        break;
                    product_total_lef = product_total_right+1;
                    product_total_right = product_total_right + 50000;
                } else if (Integer.parseInt(message) <= 1000) {
                    list = (List) ((Map) ((Map) object.get("data")).get("categoryData")).get("list");

                    List<GeekIndustrySearch> list1 = new ArrayList<>();
                    for (Map m : list) {
                        GeekIndustrySearch industrySearch = new GeekIndustrySearch();
                        industrySearch.setClassification(GeekUtils.toString(m.get("classification")));
                        industrySearch.setCommentSum(GeekUtils.toDouble(m.get("comment_sum")));
                        industrySearch.setHotSalesIndex(GeekUtils.toString(m.get("hot_sales_index")));
                        industrySearch.setIndustryName(GeekUtils.toString(m.get("industry_name")));
                        industrySearch.setIndustryNameKo(GeekUtils.toString(m.get("industry_name_ko")));
                        industrySearch.setIndustrySaleRate(GeekUtils.toDouble(m.get("industry_sale_rate")));
                        industrySearch.setLevel(GeekUtils.toString(m.get("level")));
                        industrySearch.setPath(GeekUtils.toString(m.get("path")));
                        industrySearch.setPathZh(GeekUtils.toString(m.get("path_zh")));
                        industrySearch.setPriceAvg(GeekUtils.toDouble(m.get("price_avg")));
                        industrySearch.setProductSales(GeekUtils.toString(m.get("product_sales")));
                        industrySearch.setProductTotal(GeekUtils.toDouble(m.get("product_total")));
                        industrySearch.setShopTotal(GeekUtils.toString(m.get("shop_total")));
                        industrySearch.setState(GeekUtils.toString(m.get("state")));
                        industrySearch.setTopCommentTotal(GeekUtils.toDouble(m.get("top_comment_total")));
                        industrySearch.setUrl(GeekUtils.toString(m.get("url")));//
                        industrySearch.setWeeklyAddComment(GeekUtils.toString(m.get("weekly_add_comment")));
                        industrySearch.setWeeklyAddProduct(GeekUtils.toString(m.get("weekly_add_product")));
                        industrySearch.setWeeklySupplyDemand(GeekUtils.toString(m.get("weekly_supply_demand")));

                        if (industrySearch.getProductTotal() != 0d) {
                            industrySearch.setOrderIndex(industrySearch.getCommentSum() / industrySearch.getProductTotal());
                        }
                        String[] paths = industrySearch.getPathZh().split("->");
                        if (paths.length > 0)
                            industrySearch.setPath1(paths[0]);
                        if (paths.length > 1)
                            industrySearch.setPath2(paths[1]);
                        if (paths.length > 2)
                            industrySearch.setPath3(paths[2]);
                        if (paths.length > 3)
                            industrySearch.setPath4(paths[3]);
                        if (paths.length > 4)
                            industrySearch.setPath5(paths[4]);
                        list1.add(industrySearch);
                    }
                    this.saveBatch(list1);
                    log.info(String.format("顺利完成第%s页", current));
                    product_total_lef = product_total_right+1;
                    product_total_right = product_total_right + 10000;
                } else {
                    product_total_right = (product_total_lef + product_total_right) / 2;
                }
            } catch (Exception e) {
                log.info(String.format("第%s次异常", ++i));
                Thread.sleep(5000l);
                if (i > 5) {
//                        tocken = getTocken();
                    headers = RestUtil.getHeaderApplicationJson();
                    headers.set("authorization", String.format("%s", tocken));
                    i = 0;
                }
                continue;
            }
        }


//        } while (list.size() > 0 && current < 500);
        //删除 食物、医疗、保健
        this.deleteOtherInfo();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        log.info(String.format("行业搜索,开始时间：%s-%s", df.format(startDate), df.format(new Date())));

    }


}
