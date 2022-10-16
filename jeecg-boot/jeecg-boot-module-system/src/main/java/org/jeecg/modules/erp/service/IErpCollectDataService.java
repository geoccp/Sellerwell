package org.jeecg.modules.erp.service;

import org.jeecg.modules.erp.entity.ErpCollectLanguage;
import org.jeecg.modules.erp.entity.ErpCollectImage;
import org.jeecg.modules.erp.entity.ErpCollectData;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: erp_collect_data
 * @Author: jeecg-boot
 * @Date:   2021-12-02
 * @Version: V1.0
 */
public interface IErpCollectDataService extends IService<ErpCollectData> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(ErpCollectData erpCollectData,List<ErpCollectLanguage> erpCollectLanguageList,List<ErpCollectImage> erpCollectImageList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(ErpCollectData erpCollectData,List<ErpCollectLanguage> erpCollectLanguageList,List<ErpCollectImage> erpCollectImageList);
	
	/**
	 * 删除一对多
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
