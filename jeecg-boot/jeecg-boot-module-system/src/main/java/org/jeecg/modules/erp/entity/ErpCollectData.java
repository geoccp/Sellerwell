package org.jeecg.modules.erp.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
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

/**
 * @Description: erp_collect_data
 * @Author: jeecg-boot
 * @Date:   2021-12-02
 * @Version: V1.0
 */
@ApiModel(value="erp_collect_data对象", description="erp_collect_data")
@Data
@TableName("erp_collect_data")
public class ErpCollectData implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private String id;
	/**目录ID*/
	@Excel(name = "目录ID", width = 15)
    @ApiModelProperty(value = "目录ID")
    private Integer categoryId;
	/**目录名称*/
	@Excel(name = "目录名称", width = 15)
    @ApiModelProperty(value = "目录名称")
    private String categoryName;
	/**重量*/
	@Excel(name = "重量", width = 15)
    @ApiModelProperty(value = "重量")
    private Double weight;
	/**当前价格*/
	@Excel(name = "当前价格", width = 15)
    @ApiModelProperty(value = "当前价格")
    private java.math.BigDecimal price;
	/**货币单位*/
	@Excel(name = "货币单位", width = 15)
    @ApiModelProperty(value = "货币单位")
    private String currency;
	/**主商品图片*/
	@Excel(name = "主商品图片", width = 15)
    @ApiModelProperty(value = "主商品图片")
    private String mainImageUrl;
	/**是否当前*/
	@Excel(name = "是否当前", width = 15)
    @ApiModelProperty(value = "是否当前")
    private String isCurrenct;
	/**链接平台*/
	@Excel(name = "链接平台", width = 15)
    @ApiModelProperty(value = "链接平台")
    private String srcPlatform;
	/**商品平台*/
	@Excel(name = "商品平台", width = 15)
    @ApiModelProperty(value = "商品平台")
    private String purchasePlatform;
	/**商品链接*/
	@Excel(name = "商品链接", width = 15)
    @ApiModelProperty(value = "商品链接")
    private String srcUrl;
	/**仓库*/
	@Excel(name = "仓库", width = 15)
    @ApiModelProperty(value = "仓库")
    private String warehouseSpu;
	/**商品长度*/
	@Excel(name = "商品长度", width = 15)
    @ApiModelProperty(value = "商品长度")
    private String productLength;
	/**商品宽*/
	@Excel(name = "商品宽", width = 15)
    @ApiModelProperty(value = "商品宽")
    private String productWidth;
	/**商品高*/
	@Excel(name = "商品高", width = 15)
    @ApiModelProperty(value = "商品高")
    private String productHigh;
	/**商品id*/
	@Excel(name = "商品id", width = 15)
    @ApiModelProperty(value = "商品id")
    private String itemId;
	/**库存*/
	@Excel(name = "库存", width = 15)
    @ApiModelProperty(value = "库存")
    private String stock;
	/**价格范围*/
	@Excel(name = "价格范围", width = 15)
    @ApiModelProperty(value = "价格范围")
    private String sourcePrice;
	/**创建人*/
    @Dict(dictTable = "sys_user", dicText = "realname", dicCode = "username")
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
