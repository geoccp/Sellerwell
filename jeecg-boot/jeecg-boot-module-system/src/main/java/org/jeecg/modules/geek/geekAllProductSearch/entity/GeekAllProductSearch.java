package org.jeecg.modules.geek.geekAllProductSearch.entity;

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
 * @Description: geek_all_product_search
 * @Author: jeecg-boot
 * @Date:   2022-05-23
 * @Version: V1.0
 */
@Data
@TableName("geek_all_product_search")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="geek_all_product_search对象", description="geek_all_product_search")
public class GeekAllProductSearch implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
	/**问答数*/
	@Excel(name = "问答数", width = 15)
    @ApiModelProperty(value = "问答数")
    private String aAndQ;
	/**广告产品*/
	@Excel(name = "广告产品", width = 15)
    @ApiModelProperty(value = "广告产品")
    private String adFlag;
	/**差评率*/
	@Excel(name = "差评率", width = 15)
    @ApiModelProperty(value = "差评率")
    private String badCommentRate;
	/**品牌*/
	@Excel(name = "品牌", width = 15)
    @ApiModelProperty(value = "品牌")
    private String brandName;
	/**classification*/
	@Excel(name = "classification", width = 15)
    @ApiModelProperty(value = "classification")
    private String classification;
	/**评价数量*/
	@Excel(name = "评价数量", width = 15)
    @ApiModelProperty(value = "评价数量")
    private String comment;
	/**促销折扣*/
	@Excel(name = "促销折扣", width = 15)
    @ApiModelProperty(value = "促销折扣")
    private String discount;
	/**配送方式*/
	@Excel(name = "配送方式", width = 15)
    @ApiModelProperty(value = "配送方式")
    private String distribution;
	/**好评率*/
	@Excel(name = "好评率", width = 15)
    @ApiModelProperty(value = "好评率")
    private String goodCommentRate;
	/**商品浏览图*/
	@Excel(name = "商品浏览图", width = 15)
    @ApiModelProperty(value = "商品浏览图")
    private String imgIndex;
	/**itemid*/
	@Excel(name = "itemid", width = 15)
    @ApiModelProperty(value = "itemid")
    private String itemid;
	/**韩文目录*/
	@Excel(name = "韩文目录", width = 15)
    @ApiModelProperty(value = "韩文目录")
    private String path;
	/**中文目录*/
	@Excel(name = "中文目录", width = 15)
    @ApiModelProperty(value = "中文目录")
    private String pathTzh;
	/**价格*/
	@Excel(name = "价格", width = 15)
    @ApiModelProperty(value = "价格")
    private String price;
	/**编码*/
	@Excel(name = "编码", width = 15)
    @ApiModelProperty(value = "编码")
    private String productId;
	/**产品状态*/
	@Excel(name = "产品状态", width = 15)
    @ApiModelProperty(value = "产品状态")
    private String productStatus;
	/**当前BSR排名*/
	@Excel(name = "当前BSR排名", width = 15)
    @ApiModelProperty(value = "当前BSR排名")
    private String strRank;
	/**入围榜单*/
	@Excel(name = "入围榜单", width = 15)
    @ApiModelProperty(value = "入围榜单")
    private String rankType;
	/**配送方式*/
	@Excel(name = "配送方式", width = 15)
    @ApiModelProperty(value = "配送方式")
    private String rocket;
	/**跟卖数*/
	@Excel(name = "跟卖数", width = 15)
    @ApiModelProperty(value = "跟卖数")
    private String sellerCount;
	/**上架时间*/
	@Excel(name = "上架时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "上架时间")
    private Date shelfTime;
	/**评分*/
	@Excel(name = "评分", width = 15)
    @ApiModelProperty(value = "评分")
    private String star;
	/**state*/
	@Excel(name = "state", width = 15)
    @ApiModelProperty(value = "state")
    private String state;
	/**标题*/
	@Excel(name = "标题", width = 15)
    @ApiModelProperty(value = "标题")
    private String title;
	/**url*/
	@Excel(name = "url", width = 15)
    @ApiModelProperty(value = "url")
    private String url;
	/**变体数量*/
	@Excel(name = "变体数量", width = 15)
    @ApiModelProperty(value = "变体数量")
    private String variableCount;
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
}
