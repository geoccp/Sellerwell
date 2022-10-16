package org.jeecg.modules.tool.entity;

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
 * @Description: tool_gee_product_search
 * @Author: jeecg-boot
 * @Date:   2021-11-21
 * @Version: V1.0
 */
@Data
@TableName("tool_gee_product_search")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="tool_gee_product_search对象", description="tool_gee_product_search")
public class ToolGeeProductSearch implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**问答数*/
	@Excel(name = "问答数", width = 15)
    @ApiModelProperty(value = "问答数")
    private java.lang.String askAndQ;
	/**advertisementProduct*/
	@Excel(name = "advertisementProduct", width = 15)
    @ApiModelProperty(value = "advertisementProduct")
    private java.lang.String advertisementProduct;
	/**坏评率*/
	@Excel(name = "坏评率", width = 15)
    @ApiModelProperty(value = "坏评率")
    private java.lang.String badCommentRate;
	/**classification*/
	@Excel(name = "classification", width = 15)
    @ApiModelProperty(value = "classification")
    private java.lang.String classification;
	/**当前产品评价数*/
	@Excel(name = "当前产品评价数", width = 15)
    @ApiModelProperty(value = "当前产品评价数")
    private java.lang.String comment;
	/**促销折扣*/
	@Excel(name = "促销折扣", width = 15)
    @ApiModelProperty(value = "促销折扣")
    private java.lang.String discount;
	/**配送*/
	@Excel(name = "配送", width = 15)
    @ApiModelProperty(value = "配送")
    private java.lang.String distribution;
	/**好评率*/
	@Excel(name = "好评率", width = 15)
    @ApiModelProperty(value = "好评率")
    private java.lang.String goodCommentRate;
	/**商品浏览图*/
	@Excel(name = "商品浏览图", width = 15)
    @ApiModelProperty(value = "商品浏览图")
    private java.lang.String imgIndex;
	/**itemid*/
	@Excel(name = "itemid", width = 15)
    @ApiModelProperty(value = "itemid")
    private java.lang.String itemid;
	/**类目路径韩文*/
	@Excel(name = "类目路径韩文", width = 15)
    @ApiModelProperty(value = "类目路径韩文")
    private java.lang.String path;
	/**类目路径 中文*/
	@Excel(name = "类目路径 中文", width = 15)
    @ApiModelProperty(value = "类目路径 中文")
    private java.lang.String pathTzh;
	/**产品价格*/
	@Excel(name = "产品价格", width = 15)
    @ApiModelProperty(value = "产品价格")
    private java.lang.String price;
	/**产品编码*/
	@Excel(name = "产品编码", width = 15)
    @ApiModelProperty(value = "产品编码")
    private java.lang.String productId;
	/**productStatus*/
	@Excel(name = "productStatus", width = 15)
    @ApiModelProperty(value = "productStatus")
    private java.lang.String productStatus;
	/**当前BSR排名*/
	@Excel(name = "当前BSR排名", width = 15)
    @ApiModelProperty(value = "当前BSR排名")
    private java.lang.String strRank;
	/**榜单类型*/
	@Excel(name = "榜单类型", width = 15)
    @ApiModelProperty(value = "榜单类型")
    private java.lang.String rankType;
	/**上架时间*/
	@Excel(name = "上架时间", width = 15)
    @ApiModelProperty(value = "上架时间")
    private java.lang.String shelfTime;
	/**卖家数*/
	@Excel(name = "卖家数", width = 15)
    @ApiModelProperty(value = "卖家数")
    private java.lang.String sellerCount;
	/**产品评分*/
	@Excel(name = "产品评分", width = 15)
    @ApiModelProperty(value = "产品评分")
    private java.lang.String star;
	/**state*/
	@Excel(name = "state", width = 15)
    @ApiModelProperty(value = "state")
    private java.lang.String state;
	/**产品标题*/
	@Excel(name = "产品标题", width = 15)
    @ApiModelProperty(value = "产品标题")
    private java.lang.String title;
	/**url*/
	@Excel(name = "url", width = 15)
    @ApiModelProperty(value = "url")
    private java.lang.String url;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private java.lang.String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新日期")
    private java.util.Date updateTime;
	/**分类1*/
	@Excel(name = "分类1", width = 15)
    @ApiModelProperty(value = "分类1")
    private java.lang.String path1;
	/**分类2*/
	@Excel(name = "分类2", width = 15)
    @ApiModelProperty(value = "分类2")
    private java.lang.String path2;
	/**分类3*/
	@Excel(name = "分类3", width = 15)
    @ApiModelProperty(value = "分类3")
    private java.lang.String path3;
	/**分类4*/
	@Excel(name = "分类4", width = 15)
    @ApiModelProperty(value = "分类4")
    private java.lang.String path4;
	/**分类5*/
	@Excel(name = "分类5", width = 15)
    @ApiModelProperty(value = "分类5")
    private java.lang.String path5;
}
