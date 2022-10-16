package org.jeecg.modules.erp.service.impl;

import org.jeecg.modules.erp.entity.ErpCollectData;
import org.jeecg.modules.erp.entity.ErpCollectLanguage;
import org.jeecg.modules.erp.entity.ErpCollectImage;
import org.jeecg.modules.erp.mapper.ErpCollectLanguageMapper;
import org.jeecg.modules.erp.mapper.ErpCollectImageMapper;
import org.jeecg.modules.erp.mapper.ErpCollectDataMapper;
import org.jeecg.modules.erp.service.IErpCollectDataService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: erp_collect_data
 * @Author: jeecg-boot
 * @Date:   2021-12-02
 * @Version: V1.0
 */
@Service
public class ErpCollectDataServiceImpl extends ServiceImpl<ErpCollectDataMapper, ErpCollectData> implements IErpCollectDataService {

	@Autowired
	private ErpCollectDataMapper erpCollectDataMapper;
	@Autowired
	private ErpCollectLanguageMapper erpCollectLanguageMapper;
	@Autowired
	private ErpCollectImageMapper erpCollectImageMapper;
	
	@Override
	@Transactional
	public void saveMain(ErpCollectData erpCollectData, List<ErpCollectLanguage> erpCollectLanguageList,List<ErpCollectImage> erpCollectImageList) {
		erpCollectDataMapper.insert(erpCollectData);
		if(erpCollectLanguageList!=null && erpCollectLanguageList.size()>0) {
			for(ErpCollectLanguage entity:erpCollectLanguageList) {
				//外键设置
				entity.setCollectId(erpCollectData.getId());
				erpCollectLanguageMapper.insert(entity);
			}
		}
		if(erpCollectImageList!=null && erpCollectImageList.size()>0) {
			for(ErpCollectImage entity:erpCollectImageList) {
				//外键设置
				entity.setCollectId(erpCollectData.getId());
				erpCollectImageMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void updateMain(ErpCollectData erpCollectData,List<ErpCollectLanguage> erpCollectLanguageList,List<ErpCollectImage> erpCollectImageList) {
		erpCollectDataMapper.updateById(erpCollectData);
		
		//1.先删除子表数据
		erpCollectLanguageMapper.deleteByMainId(erpCollectData.getId());
		erpCollectImageMapper.deleteByMainId(erpCollectData.getId());
		
		//2.子表数据重新插入
		if(erpCollectLanguageList!=null && erpCollectLanguageList.size()>0) {
			for(ErpCollectLanguage entity:erpCollectLanguageList) {
				//外键设置
				entity.setCollectId(erpCollectData.getId());
				erpCollectLanguageMapper.insert(entity);
			}
		}
		if(erpCollectImageList!=null && erpCollectImageList.size()>0) {
			for(ErpCollectImage entity:erpCollectImageList) {
				//外键设置
				entity.setCollectId(erpCollectData.getId());
				erpCollectImageMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		erpCollectLanguageMapper.deleteByMainId(id);
		erpCollectImageMapper.deleteByMainId(id);
		erpCollectDataMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			erpCollectLanguageMapper.deleteByMainId(id.toString());
			erpCollectImageMapper.deleteByMainId(id.toString());
			erpCollectDataMapper.deleteById(id);
		}
	}
	
}
