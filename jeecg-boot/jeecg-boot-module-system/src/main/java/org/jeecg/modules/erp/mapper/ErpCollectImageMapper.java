package org.jeecg.modules.erp.mapper;

import java.util.List;
import org.jeecg.modules.erp.entity.ErpCollectImage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: erp_collect_image
 * @Author: jeecg-boot
 * @Date:   2021-12-02
 * @Version: V1.0
 */
public interface ErpCollectImageMapper extends BaseMapper<ErpCollectImage> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<ErpCollectImage> selectByMainId(@Param("mainId") String mainId);
}
