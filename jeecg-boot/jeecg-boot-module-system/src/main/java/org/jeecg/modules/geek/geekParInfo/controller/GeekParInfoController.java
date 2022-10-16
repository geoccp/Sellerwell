package org.jeecg.modules.geek.geekParInfo.controller;

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
import org.jeecg.modules.geek.geekParInfo.entity.GeekParInfo;
import org.jeecg.modules.geek.geekParInfo.service.IGeekParInfoService;

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
 * @Description: geek_par_info
 * @Author: jeecg-boot
 * @Date:   2022-05-19
 * @Version: V1.0
 */
@Api(tags="geek_par_info")
@RestController
@RequestMapping("/geekParInfo/geekParInfo")
@Slf4j
public class GeekParInfoController extends JeecgController<GeekParInfo, IGeekParInfoService> {
	@Autowired
	private IGeekParInfoService geekParInfoService;
	
	/**
	 * 分页列表查询
	 *
	 * @param geekParInfo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "geek_par_info-分页列表查询")
	@ApiOperation(value="geek_par_info-分页列表查询", notes="geek_par_info-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(GeekParInfo geekParInfo,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<GeekParInfo> queryWrapper = QueryGenerator.initQueryWrapper(geekParInfo, req.getParameterMap());
		Page<GeekParInfo> page = new Page<GeekParInfo>(pageNo, pageSize);
		IPage<GeekParInfo> pageList = geekParInfoService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param geekParInfo
	 * @return
	 */
	@AutoLog(value = "geek_par_info-添加")
	@ApiOperation(value="geek_par_info-添加", notes="geek_par_info-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody GeekParInfo geekParInfo) {
		geekParInfoService.save(geekParInfo);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param geekParInfo
	 * @return
	 */
	@AutoLog(value = "geek_par_info-编辑")
	@ApiOperation(value="geek_par_info-编辑", notes="geek_par_info-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody GeekParInfo geekParInfo) {
		geekParInfoService.updateById(geekParInfo);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "geek_par_info-通过id删除")
	@ApiOperation(value="geek_par_info-通过id删除", notes="geek_par_info-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		geekParInfoService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "geek_par_info-批量删除")
	@ApiOperation(value="geek_par_info-批量删除", notes="geek_par_info-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.geekParInfoService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "geek_par_info-通过id查询")
	@ApiOperation(value="geek_par_info-通过id查询", notes="geek_par_info-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		GeekParInfo geekParInfo = geekParInfoService.getById(id);
		if(geekParInfo==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(geekParInfo);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param geekParInfo
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, GeekParInfo geekParInfo) {
        return super.exportXls(request, geekParInfo, GeekParInfo.class, "geek_par_info");
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
        return super.importExcel(request, response, GeekParInfo.class);
    }

}
