package org.jeecg.modules.geek.geekIndustrySearch.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.Date;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.Table;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: geek_industry_search
 * @Author: jeecg-boot
 * @Date: 2022-01-23
 * @Version: V1.0
 */
@Data
@TableName("geek_industry_search")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "geek_industry_search对象", description = "geek_industry_search")
public class GeekIndustrySearch implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
    /**
     * classification
     */
    @Excel(name = "classification", width = 15)
    @ApiModelProperty(value = "classification")
    private String classification;
    /**
     * 总评论数
     */
    @Excel(name = "总评论数", width = 15)
    @ApiModelProperty(value = "总评论数")
    private Double commentSum;
    /**
     * hotSalesIndex
     */
    @Excel(name = "hotSalesIndex", width = 15)
    @ApiModelProperty(value = "hotSalesIndex")
    private String hotSalesIndex;
    /**
     * 商品名称
     */
    @Excel(name = "商品名称", width = 15)
    @ApiModelProperty(value = "商品名称")
    private String industryName;
    /**
     * 韩文名称
     */
    @Excel(name = "韩文名称", width = 15)
    @ApiModelProperty(value = "韩文名称")
    private String industryNameKo;
    /**
     * 行业动销率
     */
    @Excel(name = "行业动销率", width = 15)
    @ApiModelProperty(value = "行业动销率")
    private Double industrySaleRate;
    /**
     * level
     */
    @Excel(name = "level", width = 15)
    @ApiModelProperty(value = "level")
    private String level;
    /**
     * 韩文分类
     */
    @Excel(name = "韩文分类", width = 15)
    @ApiModelProperty(value = "韩文分类")
    private String path;
    /**
     * 中文分类
     */
    @Excel(name = "中文分类", width = 15)
    @ApiModelProperty(value = "中文分类")
    private String pathZh;
    /**
     * 产品均价
     */
    @Excel(name = "产品均价", width = 15)
    @ApiModelProperty(value = "产品均价")
    private Double priceAvg;
    /**
     * 有销量产品数
     */
    @Excel(name = "有销量产品数", width = 15)
    @ApiModelProperty(value = "有销量产品数")
    private String productSales;
    /**
     * 产品数量
     */
    @Excel(name = "产品数量", width = 15)
    @ApiModelProperty(value = "产品数量")
    private Double productTotal;
    /**
     * 店铺数量
     */
    @Excel(name = "店铺数量", width = 15)
    @ApiModelProperty(value = "店铺数量")
    private String shopTotal;
    /**
     * state
     */
    @Excel(name = "state", width = 15)
    @ApiModelProperty(value = "state")
    private String state;
    /**
     * Top1000产品评论数
     */
    @Excel(name = "Top1000产品评论数", width = 15)
    @ApiModelProperty(value = "Top1000产品评论数")
    private Double topCommentTotal;
    /**
     * url
     */
    @Excel(name = "url", width = 15)
    @ApiModelProperty(value = "url")
    private String url;
    /**
     * 近7日新增产品评论数
     */
    @Excel(name = "近7日新增产品评论数", width = 15)
    @ApiModelProperty(value = "近7日新增产品评论数")
    private String weeklyAddComment;
    /**
     * 近7日新增产品数
     */
    @Excel(name = "近7日新增产品数", width = 15)
    @ApiModelProperty(value = "近7日新增产品数")
    private String weeklyAddProduct;
    /**
     * 近7日供需指数
     */
    @Excel(name = "近7日供需指数", width = 15)
    @ApiModelProperty(value = "近7日供需指数")
    private String weeklySupplyDemand;
    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createBy;
    /**
     * 创建日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "创建日期")
    private Date createTime;
    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人")
    private String updateBy;
    /**
     * 更新日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "更新日期")
    private Date updateTime;
    /**
     * 分类1
     */
    @Excel(name = "分类1", width = 15)
    @ApiModelProperty(value = "分类1")
    private String path1;
    /**
     * 分类2
     */
    @Excel(name = "分类2", width = 15)
    @ApiModelProperty(value = "分类2")
    private String path2;
    /**
     * 分类3
     */
    @Excel(name = "分类3", width = 15)
    @ApiModelProperty(value = "分类3")
    private String path3;
    /**
     * 分类4
     */
    @Excel(name = "分类4", width = 15)
    @ApiModelProperty(value = "分类4")
    private String path4;
    /**
     * 分类5
     */
    @Excel(name = "分类5", width = 15)
    @ApiModelProperty(value = "分类5")
    private String path5;

//    @TableField(exist = false)
    @ApiModelProperty(value = "出单指数")
    private Double orderIndex;

//    public String getOrderIndex() {
//        try {
//            DecimalFormat df   =new   DecimalFormat("#.00");
//            Double commentSumd = Double.parseDouble(commentSum);
//            Double productTotald = Double.parseDouble(productTotal);
//            return df.format(commentSumd / productTotald);
//        } catch (Exception e) {
//            return "";
//        }
//    }
}
