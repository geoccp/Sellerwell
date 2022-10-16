package org.jeecg.modules.erp.controller;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.vo.LoginUser;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.erp.entity.ErpCollectLanguage;
import org.jeecg.modules.erp.entity.ErpCollectImage;
import org.jeecg.modules.erp.entity.ErpCollectData;
import org.jeecg.modules.erp.vo.ErpCollectDataPage;
import org.jeecg.modules.erp.service.IErpCollectDataService;
import org.jeecg.modules.erp.service.IErpCollectLanguageService;
import org.jeecg.modules.erp.service.IErpCollectImageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: erp_collect_data
 * @Author: jeecg-boot
 * @Date:   2021-12-02
 * @Version: V1.0
 */
@Api(tags="erp_collect_data")
@RestController
@RequestMapping("/erp/erpCollectData")
@Slf4j
public class ErpCollectDataController {
	@Autowired
	private IErpCollectDataService erpCollectDataService;
	@Autowired
	private IErpCollectLanguageService erpCollectLanguageService;
	@Autowired
	private IErpCollectImageService erpCollectImageService;
	
	/**
	 * 分页列表查询
	 *
	 * @param erpCollectData
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "erp_collect_data-分页列表查询")
	@ApiOperation(value="erp_collect_data-分页列表查询", notes="erp_collect_data-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ErpCollectData erpCollectData,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ErpCollectData> queryWrapper = QueryGenerator.initQueryWrapper(erpCollectData, req.getParameterMap());
		Page<ErpCollectData> page = new Page<ErpCollectData>(pageNo, pageSize);
		IPage<ErpCollectData> pageList = erpCollectDataService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param erpCollectDataPage
	 * @return
	 */
	@AutoLog(value = "erp_collect_data-添加")
	@ApiOperation(value="erp_collect_data-添加", notes="erp_collect_data-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ErpCollectDataPage erpCollectDataPage) {
		ErpCollectData erpCollectData = new ErpCollectData();
		BeanUtils.copyProperties(erpCollectDataPage, erpCollectData);
		erpCollectDataService.saveMain(erpCollectData, erpCollectDataPage.getErpCollectLanguageList(),erpCollectDataPage.getErpCollectImageList());
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param erpCollectDataPage
	 * @return
	 */
	@AutoLog(value = "erp_collect_data-编辑")
	@ApiOperation(value="erp_collect_data-编辑", notes="erp_collect_data-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ErpCollectDataPage erpCollectDataPage) {
		ErpCollectData erpCollectData = new ErpCollectData();
		BeanUtils.copyProperties(erpCollectDataPage, erpCollectData);
		ErpCollectData erpCollectDataEntity = erpCollectDataService.getById(erpCollectData.getId());
		if(erpCollectDataEntity==null) {
			return Result.error("未找到对应数据");
		}
		erpCollectDataService.updateMain(erpCollectData, erpCollectDataPage.getErpCollectLanguageList(),erpCollectDataPage.getErpCollectImageList());
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "erp_collect_data-通过id删除")
	@ApiOperation(value="erp_collect_data-通过id删除", notes="erp_collect_data-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		erpCollectDataService.delMain(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "erp_collect_data-批量删除")
	@ApiOperation(value="erp_collect_data-批量删除", notes="erp_collect_data-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.erpCollectDataService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "erp_collect_data-通过id查询")
	@ApiOperation(value="erp_collect_data-通过id查询", notes="erp_collect_data-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ErpCollectData erpCollectData = erpCollectDataService.getById(id);
		if(erpCollectData==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(erpCollectData);

	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "erp_collect_language通过主表ID查询")
	@ApiOperation(value="erp_collect_language主表ID查询", notes="erp_collect_language-通主表ID查询")
	@GetMapping(value = "/queryErpCollectLanguageByMainId")
	public Result<?> queryErpCollectLanguageListByMainId(@RequestParam(name="id",required=true) String id) {
		List<ErpCollectLanguage> erpCollectLanguageList = erpCollectLanguageService.selectByMainId(id);
		return Result.OK(erpCollectLanguageList);
	}
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "erp_collect_image通过主表ID查询")
	@ApiOperation(value="erp_collect_image主表ID查询", notes="erp_collect_image-通主表ID查询")
	@GetMapping(value = "/queryErpCollectImageByMainId")
	public Result<?> queryErpCollectImageListByMainId(@RequestParam(name="id",required=true) String id) {
		List<ErpCollectImage> erpCollectImageList = erpCollectImageService.selectByMainId(id);
		return Result.OK(erpCollectImageList);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param erpCollectData
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ErpCollectData erpCollectData) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<ErpCollectData> queryWrapper = QueryGenerator.initQueryWrapper(erpCollectData, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //Step.2 获取导出数据
      List<ErpCollectData> queryList = erpCollectDataService.list(queryWrapper);
      // 过滤选中数据
      String selections = request.getParameter("selections");
      List<ErpCollectData> erpCollectDataList = new ArrayList<ErpCollectData>();
      if(oConvertUtils.isEmpty(selections)) {
          erpCollectDataList = queryList;
      }else {
          List<String> selectionList = Arrays.asList(selections.split(","));
          erpCollectDataList = queryList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
      }

      // Step.3 组装pageList
      List<ErpCollectDataPage> pageList = new ArrayList<ErpCollectDataPage>();
      for (ErpCollectData main : erpCollectDataList) {
          ErpCollectDataPage vo = new ErpCollectDataPage();
          BeanUtils.copyProperties(main, vo);
          List<ErpCollectLanguage> erpCollectLanguageList = erpCollectLanguageService.selectByMainId(main.getId());
          vo.setErpCollectLanguageList(erpCollectLanguageList);
          List<ErpCollectImage> erpCollectImageList = erpCollectImageService.selectByMainId(main.getId());
          vo.setErpCollectImageList(erpCollectImageList);
          pageList.add(vo);
      }

      // Step.4 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "erp_collect_data列表");
      mv.addObject(NormalExcelConstants.CLASS, ErpCollectDataPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("erp_collect_data数据", "导出人:"+sysUser.getRealname(), "erp_collect_data"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      return mv;
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
      MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
      Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
      for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
          MultipartFile file = entity.getValue();// 获取上传文件对象
          ImportParams params = new ImportParams();
          params.setTitleRows(2);
          params.setHeadRows(1);
          params.setNeedSave(true);
          try {
              List<ErpCollectDataPage> list = ExcelImportUtil.importExcel(file.getInputStream(), ErpCollectDataPage.class, params);
              for (ErpCollectDataPage page : list) {
                  ErpCollectData po = new ErpCollectData();
                  BeanUtils.copyProperties(page, po);
                  erpCollectDataService.saveMain(po, page.getErpCollectLanguageList(),page.getErpCollectImageList());
              }
              return Result.OK("文件导入成功！数据行数:" + list.size());
          } catch (Exception e) {
              log.error(e.getMessage(),e);
              return Result.error("文件导入失败:"+e.getMessage());
          } finally {
              try {
                  file.getInputStream().close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
      return Result.OK("文件导入失败！");
    }

}
