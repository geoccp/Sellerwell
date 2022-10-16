package org.jeecg.modules.tool.controller;

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
import org.jeecg.modules.tool.entity.ToolConfigureInfo;
import org.jeecg.modules.tool.service.IToolConfigureInfoService;

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
 * @Description: tool_configure_info
 * @Author: jeecg-boot
 * @Date: 2021-10-31
 * @Version: V1.0
 */
@Api(tags = "tool_configure_info")
@RestController
@RequestMapping("/tool/toolConfigureInfo")
@Slf4j
public class ToolConfigureInfoController extends JeecgController<ToolConfigureInfo, IToolConfigureInfoService> {
    @Autowired
    private IToolConfigureInfoService toolConfigureInfoService;

    /**
     * 分页列表查询
     *
     * @param toolConfigureInfo
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "tool_configure_info-分页列表查询")
    @ApiOperation(value = "tool_configure_info-分页列表查询", notes = "tool_configure_info-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(ToolConfigureInfo toolConfigureInfo,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<ToolConfigureInfo> queryWrapper = QueryGenerator.initQueryWrapper(toolConfigureInfo, req.getParameterMap());
        Page<ToolConfigureInfo> page = new Page<ToolConfigureInfo>(pageNo, pageSize);
        IPage<ToolConfigureInfo> pageList = toolConfigureInfoService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 添加
     *
     * @param toolConfigureInfo
     * @return
     */
    @AutoLog(value = "tool_configure_info-添加")
    @ApiOperation(value = "tool_configure_info-添加", notes = "tool_configure_info-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody ToolConfigureInfo toolConfigureInfo) {
        toolConfigureInfoService.save(toolConfigureInfo);
        return Result.OK("添加成功！");
    }

    /**
     * 编辑
     *
     * @param toolConfigureInfo
     * @return
     */
    @AutoLog(value = "tool_configure_info-编辑")
    @ApiOperation(value = "tool_configure_info-编辑", notes = "tool_configure_info-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody ToolConfigureInfo toolConfigureInfo) {
        toolConfigureInfoService.updateById(toolConfigureInfo);
        return Result.OK("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "tool_configure_info-通过id删除")
    @ApiOperation(value = "tool_configure_info-通过id删除", notes = "tool_configure_info-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        toolConfigureInfoService.removeById(id);
        return Result.OK("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "tool_configure_info-批量删除")
    @ApiOperation(value = "tool_configure_info-批量删除", notes = "tool_configure_info-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.toolConfigureInfoService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "tool_configure_info-通过id查询")
    @ApiOperation(value = "tool_configure_info-通过id查询", notes = "tool_configure_info-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        ToolConfigureInfo toolConfigureInfo = toolConfigureInfoService.getById(id);
        if (toolConfigureInfo == null) {
            return Result.error("未找到对应数据");
        }
        return Result.OK(toolConfigureInfo);
    }

    /**
     * 通过id查询
     *
     * @param toolSign
     * @return
     */
//	 @AutoLog(value = "tool_configure_info-通过id查询")
    @ApiOperation(value = "tool_configure_info-通过id查询", notes = "tool_configure_info-通过id查询")
    @RequestMapping(value = "/queryByToolSign", method = RequestMethod.GET)
    public Result<?> queryByToolSign(@RequestParam(name = "toolSign", required = true) String toolSign) {
        ToolConfigureInfo toolConfigureInfo = toolConfigureInfoService.queryByToolSign(toolSign);
        if (toolConfigureInfo == null) {
            return Result.error("未找到对应数据");
        }
        return Result.OK(toolConfigureInfo);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param toolConfigureInfo
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ToolConfigureInfo toolConfigureInfo) {
        return super.exportXls(request, toolConfigureInfo, ToolConfigureInfo.class, "tool_configure_info");
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
        return super.importExcel(request, response, ToolConfigureInfo.class);
    }

}
