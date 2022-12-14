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
	 * ??????????????????
	 *
	 * @param viewGeekHotIndexNew
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "view_geek_hot_index_new-??????????????????")
	@ApiOperation(value="view_geek_hot_index_new-??????????????????", notes="view_geek_hot_index_new-??????????????????")
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
	 *   ??????
	 *
	 * @param viewGeekHotIndexNew
	 * @return
	 */
	@AutoLog(value = "view_geek_hot_index_new-??????")
	@ApiOperation(value="view_geek_hot_index_new-??????", notes="view_geek_hot_index_new-??????")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ViewGeekHotIndexNew viewGeekHotIndexNew) {
		viewGeekHotIndexNewService.save(viewGeekHotIndexNew);
		return Result.OK("???????????????");
	}
	
	/**
	 *  ??????
	 *
	 * @param viewGeekHotIndexNew
	 * @return
	 */
	@AutoLog(value = "view_geek_hot_index_new-??????")
	@ApiOperation(value="view_geek_hot_index_new-??????", notes="view_geek_hot_index_new-??????")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ViewGeekHotIndexNew viewGeekHotIndexNew) {
		viewGeekHotIndexNewService.updateById(viewGeekHotIndexNew);
		return Result.OK("????????????!");
	}
	
	/**
	 *   ??????id??????
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "view_geek_hot_index_new-??????id??????")
	@ApiOperation(value="view_geek_hot_index_new-??????id??????", notes="view_geek_hot_index_new-??????id??????")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		viewGeekHotIndexNewService.removeById(id);
		return Result.OK("????????????!");
	}
	
	/**
	 *  ????????????
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "view_geek_hot_index_new-????????????")
	@ApiOperation(value="view_geek_hot_index_new-????????????", notes="view_geek_hot_index_new-????????????")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.viewGeekHotIndexNewService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("??????????????????!");
	}
	
	/**
	 * ??????id??????
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "view_geek_hot_index_new-??????id??????")
	@ApiOperation(value="view_geek_hot_index_new-??????id??????", notes="view_geek_hot_index_new-??????id??????")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ViewGeekHotIndexNew viewGeekHotIndexNew = viewGeekHotIndexNewService.getById(id);
		if(viewGeekHotIndexNew==null) {
			return Result.error("?????????????????????");
		}
		return Result.OK(viewGeekHotIndexNew);
	}

    /**
    * ??????excel
    *
    * @param request
    * @param viewGeekHotIndexNew
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ViewGeekHotIndexNew viewGeekHotIndexNew) {
        return super.exportXls(request, viewGeekHotIndexNew, ViewGeekHotIndexNew.class, "view_geek_hot_index_new");
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
        return super.importExcel(request, response, ViewGeekHotIndexNew.class);
    }

}
