package org.jeecg.modules.geek.geekIndustrySearch.controller;

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
import org.jeecg.modules.geek.geekIndustrySearch.entity.GeekIndustrySearch;
import org.jeecg.modules.geek.geekIndustrySearch.service.IGeekIndustrySearchService;

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
 * @Description: geek_industry_search
 * @Author: jeecg-boot
 * @Date:   2022-01-23
 * @Version: V1.0
 */
@Api(tags="geek_industry_search")
@RestController
@RequestMapping("/geekIndustrySearch/geekIndustrySearch")
@Slf4j
public class GeekIndustrySearchController extends JeecgController<GeekIndustrySearch, IGeekIndustrySearchService> {
	@Autowired
	private IGeekIndustrySearchService geekIndustrySearchService;
	
	/**
	 * 分页列表查询
	 *
	 * @param geekIndustrySearch
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "geek_industry_search-分页列表查询")
	@ApiOperation(value="geek_industry_search-分页列表查询", notes="geek_industry_search-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(GeekIndustrySearch geekIndustrySearch,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<GeekIndustrySearch> queryWrapper = QueryGenerator.initQueryWrapper(geekIndustrySearch, req.getParameterMap());
		Page<GeekIndustrySearch> page = new Page<GeekIndustrySearch>(pageNo, pageSize);
		IPage<GeekIndustrySearch> pageList = geekIndustrySearchService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param geekIndustrySearch
	 * @return
	 */
	@AutoLog(value = "geek_industry_search-添加")
	@ApiOperation(value="geek_industry_search-添加", notes="geek_industry_search-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody GeekIndustrySearch geekIndustrySearch) {
		geekIndustrySearchService.save(geekIndustrySearch);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param geekIndustrySearch
	 * @return
	 */
	@AutoLog(value = "geek_industry_search-编辑")
	@ApiOperation(value="geek_industry_search-编辑", notes="geek_industry_search-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody GeekIndustrySearch geekIndustrySearch) {
		geekIndustrySearchService.updateById(geekIndustrySearch);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "geek_industry_search-通过id删除")
	@ApiOperation(value="geek_industry_search-通过id删除", notes="geek_industry_search-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		geekIndustrySearchService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "geek_industry_search-批量删除")
	@ApiOperation(value="geek_industry_search-批量删除", notes="geek_industry_search-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.geekIndustrySearchService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "geek_industry_search-通过id查询")
	@ApiOperation(value="geek_industry_search-通过id查询", notes="geek_industry_search-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		GeekIndustrySearch geekIndustrySearch = geekIndustrySearchService.getById(id);
		if(geekIndustrySearch==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(geekIndustrySearch);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param geekIndustrySearch
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, GeekIndustrySearch geekIndustrySearch) {
        return super.exportXls(request, geekIndustrySearch, GeekIndustrySearch.class, "geek_industry_search");
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
        return super.importExcel(request, response, GeekIndustrySearch.class);
    }

	 @RequestMapping(value = "/industrySearch", method = RequestMethod.GET)
	 public void industrySearch(HttpServletRequest request, HttpServletResponse response) throws InterruptedException {
		  geekIndustrySearchService.industrySearch();
	 }



}
