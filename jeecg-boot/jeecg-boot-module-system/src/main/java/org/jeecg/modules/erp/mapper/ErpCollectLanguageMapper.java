package org.jeecg.modules.erp.mapper;

import java.util.List;
import org.jeecg.modules.erp.entity.ErpCollectLanguage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: erp_collect_language
 * @Author: jeecg-boot
 * @Date:   2021-12-02
 * @Version: V1.0
 */
public interface ErpCollectLanguageMapper extends BaseMapper<ErpCollectLanguage> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<ErpCollectLanguage> selectByMainId(@Param("mainId") String mainId);
}
