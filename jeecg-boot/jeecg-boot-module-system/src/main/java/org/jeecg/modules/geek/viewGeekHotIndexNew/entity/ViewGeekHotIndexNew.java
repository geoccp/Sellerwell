package org.jeecg.modules.geek.viewGeekHotIndexNew.entity;

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
 * @Description: view_geek_hot_index_new
 * @Author: jeecg-boot
 * @Date:   2022-05-15
 * @Version: V1.0
 */
@Data
@TableName("view_geek_hot_index_new")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="view_geek_hot_index_new对象", description="view_geek_hot_index_new")
public class ViewGeekHotIndexNew implements Serializable {
    private static final long serialVersionUID = 1L;

	/**分类编码*/
	@Excel(name = "分类编码", width = 15)
    @ApiModelProperty(value = "分类编码")
    private String classification;
	/**产品编码*/
	@Excel(name = "产品编码", width = 15)
    @ApiModelProperty(value = "产品编码")
    private String productId;
	/**分类名称*/
	@Excel(name = "分类名称", width = 15)
    @ApiModelProperty(value = "分类名称")
    private String name;
	/**分类韩文名称*/
	@Excel(name = "分类韩文名称", width = 15)
    @ApiModelProperty(value = "分类韩文名称")
    private String nameTko;
	/**7天平均排名*/
	@Excel(name = "7天平均排名", width = 15)
    @ApiModelProperty(value = "7天平均排名")
    private Integer avgRank;
	/**当前排名*/
	@Excel(name = "当前排名", width = 15)
    @ApiModelProperty(value = "当前排名")
    private Integer rank;
    /**排名涨跌*/
    @Excel(name = "排名涨跌", width = 15)
    @ApiModelProperty(value = "排名涨跌")
    private Integer changeRank;

	/**标题*/
	@Excel(name = "标题", width = 15)
    @ApiModelProperty(value = "标题")
    private String title;

    @Excel(name = "parentCategoryId", width = 15)
    @ApiModelProperty(value = "parentCategoryId")
    private String parentCategoryId;
    /**level*/
    @Excel(name = "level", width = 15)
    @ApiModelProperty(value = "level")
    private String level;
}
