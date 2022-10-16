package org.jeecg.modules.geek.geekCategory.controller;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import net.bytebuddy.implementation.bytecode.Throw;
import org.apache.commons.lang3.StringUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.DateUtils;
import org.jeecg.common.util.RestUtil;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.geek.geekCategory.entity.GeekCategory;
import org.jeecg.modules.geek.geekCategory.service.IGeekCategoryService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.modules.geek.geekHotIndexNew.entity.GeekHotIndexNew;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

/**
 * @Description: geek_category
 * @Author: jeecg-boot
 * @Date: 2022-05-11
 * @Version: V1.0
 */
@Api(tags = "geek_category")
@RestController
@RequestMapping("/geekCategory/geekCategory")
@Slf4j
public class GeekCategoryController extends JeecgController<GeekCategory, IGeekCategoryService> {
    @Autowired
    private IGeekCategoryService geekCategoryService;


    /**
     * 分页列表查询
     *
     * @param geekCategory
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "geek_category-分页列表查询")
    @ApiOperation(value = "geek_category-分页列表查询", notes = "geek_category-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(GeekCategory geekCategory,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<GeekCategory> queryWrapper = QueryGenerator.initQueryWrapper(geekCategory, req.getParameterMap());
        Page<GeekCategory> page = new Page<GeekCategory>(pageNo, pageSize);
        IPage<GeekCategory> pageList = geekCategoryService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 添加
     *
     * @param geekCategory
     * @return
     */
    @AutoLog(value = "geek_category-添加")
    @ApiOperation(value = "geek_category-添加", notes = "geek_category-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody GeekCategory geekCategory) {
        geekCategoryService.save(geekCategory);
        return Result.OK("添加成功！");
    }

    /**
     * 编辑
     *
     * @param geekCategory
     * @return
     */
    @AutoLog(value = "geek_category-编辑")
    @ApiOperation(value = "geek_category-编辑", notes = "geek_category-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody GeekCategory geekCategory) {
        geekCategoryService.updateById(geekCategory);
        return Result.OK("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "geek_category-通过id删除")
    @ApiOperation(value = "geek_category-通过id删除", notes = "geek_category-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        geekCategoryService.removeById(id);
        return Result.OK("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "geek_category-批量删除")
    @ApiOperation(value = "geek_category-批量删除", notes = "geek_category-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.geekCategoryService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "geek_category-通过id查询")
    @ApiOperation(value = "geek_category-通过id查询", notes = "geek_category-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        GeekCategory geekCategory = geekCategoryService.getById(id);
        if (geekCategory == null) {
            return Result.error("未找到对应数据");
        }
        return Result.OK(geekCategory);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param geekCategory
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, GeekCategory geekCategory) {
        return super.exportXls(request, geekCategory, GeekCategory.class, "geek_category");
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
        return super.importExcel(request, response, GeekCategory.class);
    }

    /**
     * 产品分析 产品搜索
     *
     * @throws InterruptedException
     */
    @GetMapping(value = "/saveGeekCategory")
    public void saveGeekCategory() {
        Date startDate = new Date();
        String searchUrl = "https://geek-api-python.sellerwell.com/api/category/index";
        String authorization = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9" +
                ".eyJleHAiOjE2NTI3MDYxMjEsInN1YiI6Ilx1NzUyOFx1NjIzN1x" +
                "1NzY3Ylx1NWY1NVx1NjM4OFx1Njc0MyIsIm5iZiI6MTY1MjEwMTMyM" +
                "SwiYXVkIjoxMDIwNzQyMiwiaWF0IjoxNjUyMTAxMzIxLCJqdGkiOiJmMT" +
                "EwODBmM2U3NTA4YmE2YjdkN2Y1YjM0Y2IxNzIwZiIsImlzcyI6IlNlbGxl" +
                "cldlbGwiLCJkYXRhIjp7InBhcmVudF9pZCI6MTAyMDc0MjIsInVzZXJfbmF" +
                "tZSI6IjE3NzIwNDkxNjA4Iiwib2ZmbGluZV90eXBlIjoxLCJhZ2VudF9pZ" +
                "CI6MywiYWNjb3VudF90eXBlIjoxfX0.nP2ateKjEZyxDDnwOsyIC3TgnSr9m-BfrwaaSl3Ycbo";
        HttpHeaders headers = RestUtil.getHeaderApplicationJson();
        headers.set("authorization", authorization);
        try {
            JSONObject object =
                    RestUtil.request(searchUrl, HttpMethod.GET, headers, null, null, JSONObject.class).getBody();
            List<Map> list = (ArrayList) ((Map) object.get("data")).get("category");
            if (list.size() > 0) {
                List<GeekCategory> list2 = new ArrayList<>();
                for (Map m : list) {
                    GeekCategory geekCategory = new GeekCategory();
                    geekCategory.setCategoryId(m.get("category_id").toString());
                    geekCategory.setDataCode(m.get("data_code").toString());
                    if (m.get("level") != null)
                        geekCategory.setLevel(m.get("level").toString());
                    if (m.get("name") != null)
                        geekCategory.setName(m.get("name").toString());
                    if (m.get("name_tko") != null)
                        geekCategory.setNameTko(m.get("name_tko").toString());
                    saveSubGeekCategory(geekCategory.getCategoryId(), headers);
                    list2.add(geekCategory);
                }
                geekCategoryService.saveBatch(list2);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        log.info(String.format("产品搜索,开始时间：%s-%s", df.format(startDate), df.format(new Date())));
    }

    /**
     * 保存子目录
     *
     * @param categoryId
     * @param headers
     */
    private void saveSubGeekCategory(String categoryId, HttpHeaders headers) {
        List<Map> list = getSubGeekCategory(categoryId, headers);
        if (list.size() > 0) {
            List<GeekCategory> list2 = new ArrayList<>();
            for (Map m : list) {
                GeekCategory geekCategory = new GeekCategory();
                geekCategory.setCategoryId(m.get("category_id").toString());
                geekCategory.setParentCategoryId(categoryId);
//                geekCategory.setDataCode(m.get("data_code").toString());
                if (m.get("level") != null)
                    geekCategory.setLevel(m.get("level").toString());
                if (m.get("name") != null)
                    geekCategory.setName(m.get("name").toString());
                if (m.get("name_tko") != null)
                    geekCategory.setNameTko(m.get("name_tko").toString());

                saveSubGeekCategory(geekCategory.getCategoryId(), headers);
                list2.add(geekCategory);
            }
            geekCategoryService.saveBatch(list2);
        }
    }

    private List<Map> getSubGeekCategory(String categoryId, HttpHeaders headers) {
        String searchUrl = String.format("https://geek-api-python.sellerwell.com/api/index/categoryId/%s", categoryId);
        JSONObject object =
                RestUtil.request(searchUrl, HttpMethod.GET, headers, null, null,
                        JSONObject.class).getBody();
        return (ArrayList) ((Map) object.get("data")).get("categoryIds");
    }

}
