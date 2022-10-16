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
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.modules.tool.entity.ToolRegisterInfo;
import org.jeecg.modules.tool.service.IToolRegisterInfoService;
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
 * @Description: tool_register_info
 * @Author: jeecg-boot
 * @Date: 2021-10-31
 * @Version: V1.0
 */
@Api(tags = "tool_register_info")
@RestController
@RequestMapping("/tool/toolRegisterInfo")
@Slf4j
public class ToolRegisterInfoController extends JeecgController<ToolRegisterInfo, IToolRegisterInfoService> {
    @Autowired
    private IToolRegisterInfoService toolRegisterInfoService;

    /**
     * 分页列表查询
     *
     * @param toolRegisterInfo
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "tool_register_info-分页列表查询")
    @ApiOperation(value = "tool_register_info-分页列表查询", notes = "tool_register_info-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(ToolRegisterInfo toolRegisterInfo,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<ToolRegisterInfo> queryWrapper = QueryGenerator.initQueryWrapper(toolRegisterInfo, req.getParameterMap());
        Page<ToolRegisterInfo> page = new Page<ToolRegisterInfo>(pageNo, pageSize);
        IPage<ToolRegisterInfo> pageList = toolRegisterInfoService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 添加
     *
     * @param toolRegisterInfo
     * @return
     */
    @AutoLog(value = "tool_register_info-添加")
    @ApiOperation(value = "tool_register_info-添加", notes = "tool_register_info-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody ToolRegisterInfo toolRegisterInfo) {
        toolRegisterInfoService.save(toolRegisterInfo);
        return Result.OK("添加成功！");
    }

    /**
     * 编辑
     *
     * @param toolRegisterInfo
     * @return
     */
    @AutoLog(value = "tool_register_info-编辑")
    @ApiOperation(value = "tool_register_info-编辑", notes = "tool_register_info-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody ToolRegisterInfo toolRegisterInfo) {
        toolRegisterInfoService.updateById(toolRegisterInfo);
        return Result.OK("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "tool_register_info-通过id删除")
    @ApiOperation(value = "tool_register_info-通过id删除", notes = "tool_register_info-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        toolRegisterInfoService.removeById(id);
        return Result.OK("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "tool_register_info-批量删除")
    @ApiOperation(value = "tool_register_info-批量删除", notes = "tool_register_info-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.toolRegisterInfoService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "tool_register_info-通过id查询")
    @ApiOperation(value = "tool_register_info-通过id查询", notes = "tool_register_info-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        ToolRegisterInfo toolRegisterInfo = toolRegisterInfoService.getById(id);
        if (toolRegisterInfo == null) {
            return Result.error("未找到对应数据");
        }
        return Result.OK(toolRegisterInfo);
    }


    /**
     * 通过用户注册id查询
     *
     * @param userId   用户注册编码
     * @param toolSign 工具标识
     * @return
     */
    @AutoLog(value = "tool_register_info-用户工具注册和查询")
    @ApiOperation(value = "tool_register_info-用户工具注册和查询", notes = "tool_register_info-用户工具注册和查询")
//    @RequestMapping(value = "/queryByUseId", method = RequestMethod.GET)
    @GetMapping(value = "/queryByUseId")
    public Result<?> queryByUseId(@RequestParam(name = "userId", required = true) String userId,
                                  @RequestParam(name = "toolSign", required = true) String toolSign) {
        Integer days = toolRegisterInfoService.queryByUseId(userId, toolSign);
        if (days == null) {
            return Result.error("未找到对应数据");
        }
        return Result.OK(days);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param toolRegisterInfo
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ToolRegisterInfo toolRegisterInfo) {
        return super.exportXls(request, toolRegisterInfo, ToolRegisterInfo.class, "tool_register_info");
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
        return super.importExcel(request, response, ToolRegisterInfo.class);
    }

}
