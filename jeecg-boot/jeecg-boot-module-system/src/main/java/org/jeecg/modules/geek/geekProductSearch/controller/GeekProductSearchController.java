package org.jeecg.modules.geek.geekProductSearch.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.geek.geekProductSearch.entity.GeekProductSearch;
import org.jeecg.modules.geek.geekProductSearch.service.IGeekProductSearchService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: geek_product_search
 * @Author: jeecg-boot
 * @Date:   2022-01-23
 * @Version: V1.0
 */
@Api(tags="geek_product_search")
@RestController
@RequestMapping("/geekProductSearch/geekProductSearch")
@Slf4j
public class GeekProductSearchController extends JeecgController<GeekProductSearch, IGeekProductSearchService> {
	@Autowired
	private IGeekProductSearchService geekProductSearchService;
	
	/**
	 * ??????????????????
	 *
	 * @param geekProductSearch
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "geek_product_search-??????????????????")
	@ApiOperation(value="geek_product_search-??????????????????", notes="geek_product_search-??????????????????")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(GeekProductSearch geekProductSearch,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<GeekProductSearch> queryWrapper = QueryGenerator.initQueryWrapper(geekProductSearch, req.getParameterMap());
		Page<GeekProductSearch> page = new Page<GeekProductSearch>(pageNo, pageSize);
		IPage<GeekProductSearch> pageList = geekProductSearchService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   ??????
	 *
	 * @param geekProductSearch
	 * @return
	 */
	@AutoLog(value = "geek_product_search-??????")
	@ApiOperation(value="geek_product_search-??????", notes="geek_product_search-??????")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody GeekProductSearch geekProductSearch) {
		geekProductSearchService.save(geekProductSearch);
		return Result.OK("???????????????");
	}
	
	/**
	 *  ??????
	 *
	 * @param geekProductSearch
	 * @return
	 */
	@AutoLog(value = "geek_product_search-??????")
	@ApiOperation(value="geek_product_search-??????", notes="geek_product_search-??????")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody GeekProductSearch geekProductSearch) {
		geekProductSearchService.updateById(geekProductSearch);
		return Result.OK("????????????!");
	}
	
	/**
	 *   ??????id??????
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "geek_product_search-??????id??????")
	@ApiOperation(value="geek_product_search-??????id??????", notes="geek_product_search-??????id??????")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		geekProductSearchService.removeById(id);
		return Result.OK("????????????!");
	}
	
	/**
	 *  ????????????
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "geek_product_search-????????????")
	@ApiOperation(value="geek_product_search-????????????", notes="geek_product_search-????????????")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.geekProductSearchService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("??????????????????!");
	}
	
	/**
	 * ??????id??????
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "geek_product_search-??????id??????")
	@ApiOperation(value="geek_product_search-??????id??????", notes="geek_product_search-??????id??????")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		GeekProductSearch geekProductSearch = geekProductSearchService.getById(id);
		if(geekProductSearch==null) {
			return Result.error("?????????????????????");
		}
		return Result.OK(geekProductSearch);
	}

    /**
    * ??????excel
    *
    * @param request
    * @param geekProductSearch
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, GeekProductSearch geekProductSearch) {
        return super.exportXls(request, geekProductSearch, GeekProductSearch.class, "geek_product_search");
    }

    /**
      * ??????excel????????????
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, GeekProductSearch.class);
    }

}
