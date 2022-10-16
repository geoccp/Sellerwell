package org.jeecg.modules.tool.controller;

import java.util.Arrays;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.tool.entity.ToolGeeProductSearch;
import org.jeecg.modules.tool.entity.ToolGeeProductSearchColl;
import org.jeecg.modules.tool.service.IToolGeeProductSearchService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: tool_gee_product_search
 * @Author: jeecg-boot
 * @Date:   2021-11-21
 * @Version: V1.0
 */
@Api(tags="tool_gee_product_search")
@RestController
@RequestMapping("/tool/toolGeeProductSearch")
@Slf4j
public class ToolGeeProductSearchController extends JeecgController<ToolGeeProductSearch, IToolGeeProductSearchService> {
	@Autowired
	private IToolGeeProductSearchService toolGeeProductSearchService;
	
	/**
	 * 分页列表查询
	 *
	 * @param toolGeeProductSearch
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "tool_gee_product_search-分页列表查询")
	@ApiOperation(value="tool_gee_product_search-分页列表查询", notes="tool_gee_product_search-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ToolGeeProductSearch toolGeeProductSearch,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ToolGeeProductSearch> queryWrapper = QueryGenerator.initQueryWrapper(toolGeeProductSearch, req.getParameterMap());
		Page<ToolGeeProductSearch> page = new Page<ToolGeeProductSearch>(pageNo, pageSize);
		IPage<ToolGeeProductSearch> pageList = toolGeeProductSearchService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param toolGeeProductSearch
	 * @return
	 */
	@AutoLog(value = "tool_gee_product_search-添加")
	@ApiOperation(value="tool_gee_product_search-添加", notes="tool_gee_product_search-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ToolGeeProductSearch toolGeeProductSearch) {
		toolGeeProductSearchService.save(toolGeeProductSearch);
		return Result.OK("添加成功！");
	}

	 @ApiOperation(value="tool_geekseeker-添加", notes="tool_geekseeker-添加")
	 @RequestMapping(value = "/batchAdd" ,method = RequestMethod.POST)
	 public Result<?> batchAdd(@RequestBody ToolGeeProductSearchColl toolGeekColl ) {
		 Collection<ToolGeeProductSearch> toolGeekseekerCollection=toolGeekColl.getData();
		 toolGeeProductSearchService.saveBatch(toolGeekseekerCollection);
		 return Result.OK("添加成功！");
	 }
	
	/**
	 *  编辑
	 *
	 * @param toolGeeProductSearch
	 * @return
	 */
	@AutoLog(value = "tool_gee_product_search-编辑")
	@ApiOperation(value="tool_gee_product_search-编辑", notes="tool_gee_product_search-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ToolGeeProductSearch toolGeeProductSearch) {
		toolGeeProductSearchService.updateById(toolGeeProductSearch);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "tool_gee_product_search-通过id删除")
	@ApiOperation(value="tool_gee_product_search-通过id删除", notes="tool_gee_product_search-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		toolGeeProductSearchService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "tool_gee_product_search-批量删除")
	@ApiOperation(value="tool_gee_product_search-批量删除", notes="tool_gee_product_search-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.toolGeeProductSearchService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "tool_gee_product_search-通过id查询")
	@ApiOperation(value="tool_gee_product_search-通过id查询", notes="tool_gee_product_search-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ToolGeeProductSearch toolGeeProductSearch = toolGeeProductSearchService.getById(id);
		if(toolGeeProductSearch==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(toolGeeProductSearch);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param toolGeeProductSearch
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ToolGeeProductSearch toolGeeProductSearch) {
        return super.exportXls(request, toolGeeProductSearch, ToolGeeProductSearch.class, "tool_gee_product_search");
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
        return super.importExcel(request, response, ToolGeeProductSearch.class);
    }

}
