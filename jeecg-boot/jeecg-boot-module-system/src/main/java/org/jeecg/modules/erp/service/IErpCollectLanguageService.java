package org.jeecg.modules.erp.service;

import org.jeecg.modules.erp.entity.ErpCollectLanguage;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: erp_collect_language
 * @Author: jeecg-boot
 * @Date:   2021-12-02
 * @Version: V1.0
 */
public interface IErpCollectLanguageService extends IService<ErpCollectLanguage> {

	public List<ErpCollectLanguage> selectByMainId(String mainId);
}
