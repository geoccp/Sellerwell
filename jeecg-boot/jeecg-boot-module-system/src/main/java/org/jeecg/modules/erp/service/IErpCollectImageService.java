package org.jeecg.modules.erp.service;

import org.jeecg.modules.erp.entity.ErpCollectImage;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: erp_collect_image
 * @Author: jeecg-boot
 * @Date:   2021-12-02
 * @Version: V1.0
 */
public interface IErpCollectImageService extends IService<ErpCollectImage> {

	public List<ErpCollectImage> selectByMainId(String mainId);
}
