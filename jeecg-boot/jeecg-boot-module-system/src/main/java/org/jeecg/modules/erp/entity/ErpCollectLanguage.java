package org.jeecg.modules.erp.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.UnsupportedEncodingException;

/**
 * @Description: erp_collect_language
 * @Author: jeecg-boot
 * @Date:   2021-12-02
 * @Version: V1.0
 */
@ApiModel(value="erp_collect_language对象", description="erp_collect_language")
@Data
@TableName("erp_collect_language")
public class ErpCollectLanguage implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private String id;
	/**集合外键*/
    @ApiModelProperty(value = "集合外键")
    private String collectId;
	/**当前语言*/
	@Excel(name = "当前语言", width = 15)
    @ApiModelProperty(value = "当前语言")
    private String currenctLanguage;
	/**title*/
	@Excel(name = "title", width = 15)
    @ApiModelProperty(value = "title")
    private String title;
	/**description*/
	@Excel(name = "description", width = 15)
    @ApiModelProperty(value = "description")
    private String description;
	/**optionJson*/
	@Excel(name = "optionJson", width = 15)
    @ApiModelProperty(value = "optionJson")
    private String optionJson;
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
