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
	 * ??????????????????
	 *
	 * @param erpCollectData
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "erp_collect_data-??????????????????")
	@ApiOperation(value="erp_collect_data-??????????????????", notes="erp_collect_data-??????????????????")
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
	 *   ??????
	 *
	 * @param erpCollectDataPage
	 * @return
	 */
	@AutoLog(value = "erp_collect_data-??????")
	@ApiOperation(value="erp_collect_data-??????", notes="erp_collect_data-??????")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ErpCollectDataPage erpCollectDataPage) {
		ErpCollectData erpCollectData = new ErpCollectData();
		BeanUtils.copyProperties(erpCollectDataPage, erpCollectData);
		erpCollectDataService.saveMain(erpCollectData, erpCollectDataPage.getErpCollectLanguageList(),erpCollectDataPage.getErpCollectImageList());
		return Result.OK("???????????????");
	}
	
	/**
	 *  ??????
	 *
	 * @param erpCollectDataPage
	 * @return
	 */
	@AutoLog(value = "erp_collect_data-??????")
	@ApiOperation(value="erp_collect_data-??????", notes="erp_collect_data-??????")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ErpCollectDataPage erpCollectDataPage) {
		ErpCollectData erpCollectData = new ErpCollectData();
		BeanUtils.copyProperties(erpCollectDataPage, erpCollectData);
		ErpCollectData erpCollectDataEntity = erpCollectDataService.getById(erpCollectData.getId());
		if(erpCollectDataEntity==null) {
			return Result.error("?????????????????????");
		}
		erpCollectDataService.updateMain(erpCollectData, erpCollectDataPage.getErpCollectLanguageList(),erpCollectDataPage.getErpCollectImageList());
		return Result.OK("????????????!");
	}
	
	/**
	 *   ??????id??????
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "erp_collect_data-??????id??????")
	@ApiOperation(value="erp_collect_data-??????id??????", notes="erp_collect_data-??????id??????")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		erpCollectDataService.delMain(id);
		return Result.OK("????????????!");
	}
	
	/**
	 *  ????????????
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "erp_collect_data-????????????")
	@ApiOperation(value="erp_collect_data-????????????", notes="erp_collect_data-????????????")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.erpCollectDataService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.OK("?????????????????????");
	}
	
	/**
	 * ??????id??????
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "erp_collect_data-??????id??????")
	@ApiOperation(value="erp_collect_data-??????id??????", notes="erp_collect_data-??????id??????")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ErpCollectData erpCollectData = erpCollectDataService.getById(id);
		if(erpCollectData==null) {
			return Result.error("?????????????????????");
		}
		return Result.OK(erpCollectData);

	}
	
	/**
	 * ??????id??????
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "erp_collect_language????????????ID??????")
	@ApiOperation(value="erp_collect_language??????ID??????", notes="erp_collect_language-?????????ID??????")
	@GetMapping(value = "/queryErpCollectLanguageByMainId")
	public Result<?> queryErpCollectLanguageListByMainId(@RequestParam(name="id",required=true) String id) {
		List<ErpCollectLanguage> erpCollectLanguageList = erpCollectLanguageService.selectByMainId(id);
		return Result.OK(erpCollectLanguageList);
	}
	/**
	 * ??????id??????
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "erp_collect_image????????????ID??????")
	@ApiOperation(value="erp_collect_image??????ID??????", notes="erp_collect_image-?????????ID??????")
	@GetMapping(value = "/queryErpCollectImageByMainId")
	public Result<?> queryErpCollectImageListByMainId(@RequestParam(name="id",required=true) String id) {
		List<ErpCollectImage> erpCollectImageList = erpCollectImageService.selectByMainId(id);
		return Result.OK(erpCollectImageList);
	}

    /**
    * ??????excel
    *
    * @param request
    * @param erpCollectData
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ErpCollectData erpCollectData) {
      // Step.1 ??????????????????????????????
      QueryWrapper<ErpCollectData> queryWrapper = QueryGenerator.initQueryWrapper(erpCollectData, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //Step.2 ??????????????????
      List<ErpCollectData> queryList = erpCollectDataService.list(queryWrapper);
      // ??????????????????
      String selections = request.getParameter("selections");
      List<ErpCollectData> erpCollectDataList = new ArrayList<ErpCollectData>();
      if(oConvertUtils.isEmpty(selections)) {
          erpCollectDataList = queryList;
      }else {
          List<String> selectionList = Arrays.asList(selections.split(","));
          erpCollectDataList = queryList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
      }

      // Step.3 ??????pageList
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

      // Step.4 AutoPoi ??????Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "erp_collect_data??????");
      mv.addObject(NormalExcelConstants.CLASS, ErpCollectDataPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("erp_collect_data??????", "?????????:"+sysUser.getRealname(), "erp_collect_data"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      return mv;
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
      MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
      Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
      for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
          MultipartFile file = entity.getValue();// ????????????????????????
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
              return Result.OK("?????????????????????????????????:" + list.size());
          } catch (Exception e) {
              log.error(e.getMessage(),e);
              return Result.error("??????????????????:"+e.getMessage());
          } finally {
              try {
                  file.getInputStream().close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
      return Result.OK("?????????????????????");
    }

}
