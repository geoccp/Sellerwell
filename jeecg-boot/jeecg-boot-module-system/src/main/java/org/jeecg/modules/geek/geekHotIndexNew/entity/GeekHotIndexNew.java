package org.jeecg.modules.geek.geekHotIndexNew.entity;

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
 * @Description: geek_hot_index_new
 * @Author: jeecg-boot
 * @Date:   2022-05-09
 * @Version: V1.0
 */
@Data
@TableName("geek_hot_index_new")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="geek_hot_index_new对象", description="geek_hot_index_new")
public class GeekHotIndexNew implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
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
    /**统计日期*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计日期")
    private Date date;
	/**classification*/
	@Excel(name = "classification", width = 15)
    @ApiModelProperty(value = "classification")
    private String classification;
	/**comment*/
	@Excel(name = "comment", width = 15)
    @ApiModelProperty(value = "comment")
    private String comment;
	/**images*/
	@Excel(name = "images", width = 15)
    @ApiModelProperty(value = "images")
    private String images;
	/**price*/
	@Excel(name = "price", width = 15)
    @ApiModelProperty(value = "price")
    private Double price;
	/**productId*/
	@Excel(name = "productId", width = 15)
    @ApiModelProperty(value = "productId")
    private String productId;
	/**rank*/
	@Excel(name = "rank", width = 15)
    @ApiModelProperty(value = "rank")
    private String rank;
	/**rankType*/
	@Excel(name = "rankType", width = 15)
    @ApiModelProperty(value = "rankType")
    private String rankType;
	/**star*/
	@Excel(name = "star", width = 15)
    @ApiModelProperty(value = "star")
    private String star;
	/**state*/
	@Excel(name = "state", width = 15)
    @ApiModelProperty(value = "state")
    private String state;
	/**title*/
	@Excel(name = "title", width = 15)
    @ApiModelProperty(value = "title")
    private String title;
	/**url*/
	@Excel(name = "url", width = 15)
    @ApiModelProperty(value = "url")
    private String url;
    /**name*/
    @Excel(name = "name", width = 15)
    @ApiModelProperty(value = "name")
    private String name;
    /**nameTko*/
    @Excel(name = "nameTko", width = 15)
    @ApiModelProperty(value = "nameTko")
    private String nameTko;


    @Excel(name = "parentCategoryId", width = 15)
    @ApiModelProperty(value = "parentCategoryId")
    private String parentCategoryId;
    /**level*/
    @Excel(name = "level", width = 15)
    @ApiModelProperty(value = "level")
    private String level;
}
