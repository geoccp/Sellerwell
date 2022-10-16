package org.jeecg.modules.geek.geekCategory.entity;

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
 * @Description: geek_category
 * @Author: jeecg-boot
 * @Date:   2022-05-11
 * @Version: V1.0
 */
@Data
@TableName("geek_category")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="geek_category对象", description="geek_category")
public class GeekCategory implements Serializable {
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
	/**categoryId*/
	@Excel(name = "categoryId", width = 15)
    @ApiModelProperty(value = "categoryId")
    private String categoryId;

    @Excel(name = "parentCategoryId", width = 15)
    @ApiModelProperty(value = "parentCategoryId")
    private String parentCategoryId;
	/**dataCode*/
	@Excel(name = "dataCode", width = 15)
    @ApiModelProperty(value = "dataCode")
    private String dataCode;
	/**level*/
	@Excel(name = "level", width = 15)
    @ApiModelProperty(value = "level")
    private String level;
	/**name*/
	@Excel(name = "name", width = 15)
    @ApiModelProperty(value = "name")
    private String name;
	/**nameTko*/
	@Excel(name = "nameTko", width = 15)
    @ApiModelProperty(value = "nameTko")
    private String nameTko;
}
