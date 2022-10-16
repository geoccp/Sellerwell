package org.jeecg.modules.erp.service.impl;

import org.jeecg.modules.erp.entity.ErpCollectLanguage;
import org.jeecg.modules.erp.mapper.ErpCollectLanguageMapper;
import org.jeecg.modules.erp.service.IErpCollectLanguageService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: erp_collect_language
 * @Author: jeecg-boot
 * @Date:   2021-12-02
 * @Version: V1.0
 */
@Service
public class ErpCollectLanguageServiceImpl extends ServiceImpl<ErpCollectLanguageMapper, ErpCollectLanguage> implements IErpCollectLanguageService {
	
	@Autowired
	private ErpCollectLanguageMapper erpCollectLanguageMapper;
	
	@Override
	public List<ErpCollectLanguage> selectByMainId(String mainId) {
		return erpCollectLanguageMapper.selectByMainId(mainId);
	}
}
