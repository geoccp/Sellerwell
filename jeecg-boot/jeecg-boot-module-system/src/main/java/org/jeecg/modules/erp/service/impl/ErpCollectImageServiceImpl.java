package org.jeecg.modules.erp.service.impl;

import org.jeecg.modules.erp.entity.ErpCollectImage;
import org.jeecg.modules.erp.mapper.ErpCollectImageMapper;
import org.jeecg.modules.erp.service.IErpCollectImageService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: erp_collect_image
 * @Author: jeecg-boot
 * @Date:   2021-12-02
 * @Version: V1.0
 */
@Service
public class ErpCollectImageServiceImpl extends ServiceImpl<ErpCollectImageMapper, ErpCollectImage> implements IErpCollectImageService {
	
	@Autowired
	private ErpCollectImageMapper erpCollectImageMapper;
	
	@Override
	public List<ErpCollectImage> selectByMainId(String mainId) {
		return erpCollectImageMapper.selectByMainId(mainId);
	}
}
