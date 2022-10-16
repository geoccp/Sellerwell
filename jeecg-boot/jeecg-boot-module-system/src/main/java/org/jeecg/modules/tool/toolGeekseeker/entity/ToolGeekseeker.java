package org.jeecg.modules.tool.toolGeekseeker.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: tool_geekseeker
 * @Author: jeecg-boot
 * @Date:   2021-12-11
 * @Version: V1.0
 */
@Data
@TableName("tool_geekseeker")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="tool_geekseeker对象", description="tool_geekseeker")
public class ToolGeekseeker implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private String id;
	/**编码*/
	@Excel(name = "编码", width = 15)
    @ApiModelProperty(value = "编码")
    private String classification;
	/**页码*/
	@Excel(name = "页码", width = 15)
    @ApiModelProperty(value = "页码")
    private Integer pageSum;
	/**细分市场*/
	@Excel(name = "细分市场", width = 15)
    @ApiModelProperty(value = "细分市场")
    private String industryName;
	/**分类1*/
	@Excel(name = "分类1", width = 15)
    @ApiModelProperty(value = "分类1")
    private String path1;
	/**分类2*/
	@Excel(name = "分类2", width = 15)
    @ApiModelProperty(value = "分类2")
    private String path2;
	/**分类3*/
	@Excel(name = "分类3", width = 15)
    @ApiModelProperty(value = "分类3")
    private String path3;
	/**分类4*/
	@Excel(name = "分类4", width = 15)
    @ApiModelProperty(value = "分类4")
    private String path4;
	/**分类5*/
	@Excel(name = "分类5", width = 15)
    @ApiModelProperty(value = "分类5")
    private String path5;
	/**获单指数*/
	@Excel(name = "获单指数", width = 15)
    @ApiModelProperty(value = "获单指数")
    private Double orderIndex;
	/**热销指数*/
	@Excel(name = "热销指数", width = 15)
    @ApiModelProperty(value = "热销指数")
    private Double hotSalesIndex;
	/**产品数量*/
	@Excel(name = "产品数量", width = 15)
    @ApiModelProperty(value = "产品数量")
    private Double productTotal;
	/**有销售产品数*/
	@Excel(name = "有销售产品数", width = 15)
    @ApiModelProperty(value = "有销售产品数")
    private Double productSales;
	/**行业动销率*/
	@Excel(name = "行业动销率", width = 15)
    @ApiModelProperty(value = "行业动销率")
    private Double industrySaleRate;
	/**产品均价*/
	@Excel(name = "产品均价", width = 15)
    @ApiModelProperty(value = "产品均价")
    private Double priceAvg;
	/**top100评论指数*/
	@Excel(name = "top100评论指数", width = 15)
    @ApiModelProperty(value = "top100评论指数")
    private Double topCommentTotal;
	/**韩文分类*/
	@Excel(name = "韩文分类", width = 15)
    @ApiModelProperty(value = "韩文分类")
    private String path;
	/**中文分类*/
	@Excel(name = "中文分类", width = 15)
    @ApiModelProperty(value = "中文分类")
    private String pathZh;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建日期")
    private Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新日期")
    private Date updateTime;
}
