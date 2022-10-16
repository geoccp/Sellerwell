package org.jeecg.modules.geek.viewGeekHotIndexNew.controller;

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
import org.jeecg.modules.geek.viewGeekHotIndexNew.entity.ViewGeekHotIndexNew;
import org.jeecg.modules.geek.viewGeekHotIndexNew.service.IViewGeekHotIndexNewService;

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
 * @Description: view_geek_hot_index_new
 * @Author: jeecg-boot
 * @Date:   2022-05-15
 * @Version: V1.0
 */
@Api(tags="view_geek_hot_index_new")
@RestController
@RequestMapping("/viewGeekHotIndexNew/viewGeekHotIndexNew")
@Slf4j
public class ViewGeekHotIndexNewController extends JeecgController<ViewGeekHotIndexNew, IViewGeekHotIndexNewService> {
	@Autowired
	private IViewGeekHotIndexNewService viewGeekHotIndexNewService;
	
	/**
	 * 分页列表查询
	 *
	 * @param viewGeekHotIndexNew
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "view_geek_hot_index_new-分页列表查询")
	@ApiOperation(value="view_geek_hot_index_new-分页列表查询", notes="view_geek_hot_index_new-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ViewGeekHotIndexNew viewGeekHotIndexNew,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ViewGeekHotIndexNew> queryWrapper = QueryGenerator.initQueryWrapper(viewGeekHotIndexNew, req.getParameterMap());
		Page<ViewGeekHotIndexNew> page = new Page<ViewGeekHotIndexNew>(pageNo, pageSize);
		IPage<ViewGeekHotIndexNew> pageList = viewGeekHotIndexNewService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param viewGeekHotIndexNew
	 * @return
	 */
	@AutoLog(value = "view_geek_hot_index_new-添加")
	@ApiOperation(value="view_geek_hot_index_new-添加", notes="view_geek_hot_index_new-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ViewGeekHotIndexNew viewGeekHotIndexNew) {
		viewGeekHotIndexNewService.save(viewGeekHotIndexNew);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param viewGeekHotIndexNew
	 * @return
	 */
	@AutoLog(value = "view_geek_hot_index_new-编辑")
	@ApiOperation(value="view_geek_hot_index_new-编辑", notes="view_geek_hot_index_new-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ViewGeekHotIndexNew viewGeekHotIndexNew) {
		viewGeekHotIndexNewService.updateById(viewGeekHotIndexNew);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "view_geek_hot_index_new-通过id删除")
	@ApiOperation(value="view_geek_hot_index_new-通过id删除", notes="view_geek_hot_index_new-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		viewGeekHotIndexNewService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "view_geek_hot_index_new-批量删除")
	@ApiOperation(value="view_geek_hot_index_new-批量删除", notes="view_geek_hot_index_new-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.viewGeekHotIndexNewService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "view_geek_hot_index_new-通过id查询")
	@ApiOperation(value="view_geek_hot_index_new-通过id查询", notes="view_geek_hot_index_new-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ViewGeekHotIndexNew viewGeekHotIndexNew = viewGeekHotIndexNewService.getById(id);
		if(viewGeekHotIndexNew==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(viewGeekHotIndexNew);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param viewGeekHotIndexNew
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ViewGeekHotIndexNew viewGeekHotIndexNew) {
        return super.exportXls(request, viewGeekHotIndexNew, ViewGeekHotIndexNew.class, "view_geek_hot_index_new");
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
        return super.importExcel(request, response, ViewGeekHotIndexNew.class);
    }

}
