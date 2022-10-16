package org.jeecg.modules.geek.geekHotIndexNew.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.DateUtils;
import org.jeecg.common.util.RestUtil;
import org.jeecg.modules.geek.geekCategory.entity.GeekCategory;
import org.jeecg.modules.geek.geekCategory.service.IGeekCategoryService;
import org.jeecg.modules.geek.geekHotIndexNew.entity.GeekHotIndexNew;
import org.jeecg.modules.geek.geekHotIndexNew.service.IGeekHotIndexNewService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.modules.geek.geekParInfo.service.IGeekParInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

/**
 * @Description: geek_hot_index_new
 * @Author: jeecg-boot
 * @Date: 2022-05-09
 * @Version: V1.0
 */
@Api(tags = "geek_hot_index_new")
@RestController
@RequestMapping("/geekHotIndexNew/geekHotIndexNew")
@Slf4j
public class GeekHotIndexNewController extends JeecgController<GeekHotIndexNew, IGeekHotIndexNewService> {
    @Autowired
    private IGeekHotIndexNewService geekHotIndexNewService;
    @Autowired
    private IGeekCategoryService geekCategoryService;
    @Autowired
    private IGeekParInfoService geekParInfoService;

    /**
     * 分页列表查询
     *
     * @param geekHotIndexNew
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "geek_hot_index_new-分页列表查询")
    @ApiOperation(value = "geek_hot_index_new-分页列表查询", notes = "geek_hot_index_new-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(GeekHotIndexNew geekHotIndexNew,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<GeekHotIndexNew> queryWrapper = QueryGenerator.initQueryWrapper(geekHotIndexNew, req.getParameterMap());
        Page<GeekHotIndexNew> page = new Page<GeekHotIndexNew>(pageNo, pageSize);
        IPage<GeekHotIndexNew> pageList = geekHotIndexNewService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 添加
     *
     * @param geekHotIndexNew
     * @return
     */
    @AutoLog(value = "geek_hot_index_new-添加")
    @ApiOperation(value = "geek_hot_index_new-添加", notes = "geek_hot_index_new-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody GeekHotIndexNew geekHotIndexNew) {
        geekHotIndexNewService.save(geekHotIndexNew);
        return Result.OK("添加成功！");
    }

    /**
     * 编辑
     *
     * @param geekHotIndexNew
     * @return
     */
    @AutoLog(value = "geek_hot_index_new-编辑")
    @ApiOperation(value = "geek_hot_index_new-编辑", notes = "geek_hot_index_new-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody GeekHotIndexNew geekHotIndexNew) {
        geekHotIndexNewService.updateById(geekHotIndexNew);
        return Result.OK("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "geek_hot_index_new-通过id删除")
    @ApiOperation(value = "geek_hot_index_new-通过id删除", notes = "geek_hot_index_new-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        geekHotIndexNewService.removeById(id);
        return Result.OK("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "geek_hot_index_new-批量删除")
    @ApiOperation(value = "geek_hot_index_new-批量删除", notes = "geek_hot_index_new-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.geekHotIndexNewService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "geek_hot_index_new-通过id查询")
    @ApiOperation(value = "geek_hot_index_new-通过id查询", notes = "geek_hot_index_new-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        GeekHotIndexNew geekHotIndexNew = geekHotIndexNewService.getById(id);
        if (geekHotIndexNew == null) {
            return Result.error("未找到对应数据");
        }
        return Result.OK(geekHotIndexNew);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param geekHotIndexNew
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, GeekHotIndexNew geekHotIndexNew) {
        return super.exportXls(request, geekHotIndexNew, GeekHotIndexNew.class, "geek_hot_index_new");
    }

    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, GeekHotIndexNew.class);
    }


    /**
     * 产品分析 产品搜索
     *
     * @throws InterruptedException
     */
    @GetMapping(value = "/saveGeekHotIndex")
    public void saveGeekHotIndex(@RequestParam(name = "times", required = false) String times) throws ParseException {
        Date startDate = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        LambdaQueryWrapper<GeekCategory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(GeekCategory::getLevel, "1", "2", "3")
                .orderByAsc(GeekCategory::getLevel);
        List<GeekCategory> list = geekCategoryService.list(queryWrapper);
        if (times == null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            calendar.add(Calendar.DATE, -1);
            times = df.format(calendar.getTime());
        }
        geekHotIndexNewService.clearData(times);
        try {
            for (GeekCategory g : list) {
                saveGeekHotIndex(g, times);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 保存历史热卖商品信息
     *
     * @param category
     */
    private void saveGeekHotIndex(GeekCategory category, String times) throws ParseException {
        String searchUrl = "https://geek-api-python.sellerwell.com/api/product/hotIndexNew";
        String authorization = geekParInfoService.getAuthorization("1525700243692216321");

        JSONObject param = new JSONObject();
        param.put("times", times);//"2022-05-08"
        param.put("classification", category.getCategoryId());
        param.put("rank_type", "coupang");
        HttpHeaders headers = RestUtil.getHeaderApplicationJson();
        headers.set("authorization", authorization);
        JSONObject object = RestUtil.request(searchUrl, HttpMethod.POST, headers, null, param, JSONObject.class).getBody();
        List<Map> list = (ArrayList) ((Map) object.get("data")).get("data");
        for (int i = 0; i < 3; i++) {
            Map map = (Map) list.get(i);
            String strDate = map.get("date").toString();
            //数据已经采集，则无需重复采集
            List<Map> list1 = (ArrayList) map.get(strDate);
            List<GeekHotIndexNew> list2 = new ArrayList<>();
            Map<String, String> productMap = new HashMap<>();
            if (list1.size() > 0) {
                for (Map m : list1) {
                    String productId = m.get("product_id").toString();
                    if (geekHotIndexNewService.isExistByProdectID(productId, strDate) == false) {
                        if (!productMap.containsKey(productId)) {
                            productMap.put(productId, productId);
                            GeekHotIndexNew geekHotIndexNew = new GeekHotIndexNew();
                            geekHotIndexNew.setClassification(m.get("classification").toString());
                            geekHotIndexNew.setComment(m.get("comment").toString());
                            geekHotIndexNew.setImages(m.get("images").toString());
                            geekHotIndexNew.setName(category.getName());
                            geekHotIndexNew.setNameTko(category.getNameTko());
                            geekHotIndexNew.setLevel(category.getLevel());
                            geekHotIndexNew.setParentCategoryId(category.getParentCategoryId());
                            try {
                                geekHotIndexNew.setPrice(Double.parseDouble(m.get("price").toString()));
                            } catch (Exception e) {

                            }
                            geekHotIndexNew.setProductId(productId);
                            geekHotIndexNew.setRank(m.get("rank").toString());
                            geekHotIndexNew.setRankType(m.get("rank_type").toString());
                            geekHotIndexNew.setStar(m.get("star").toString());
                            geekHotIndexNew.setState(m.get("state").toString());
                            geekHotIndexNew.setTitle(m.get("title").toString());
                            geekHotIndexNew.setUrl(m.get("url").toString());
                            geekHotIndexNew.setDate(DateUtils.parseDate(strDate, "yyyy-MM-dd"));
                            list2.add(geekHotIndexNew);
                        }
                    }
                }
            }
            geekHotIndexNewService.saveBatch(list2);
        }
    }

    @GetMapping(value = "/createStatViewTable")
    public Result<?> createStatViewTable() {
        this.geekHotIndexNewService.createStatViewTable();
        return Result.OK("批量统计表成功!");
    }
}
