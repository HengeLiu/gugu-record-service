package com.nutrition.nutritionservice.biz.health;

import com.nutrition.nutritionservice.annotation.Biz;
import com.nutrition.nutritionservice.controller.health.ao.StorePreviewAo;
import com.nutrition.nutritionservice.enums.database.StoreStatusEnum;
import com.nutrition.nutritionservice.service.StoreInfoService;
import com.nutrition.nutritionservice.vo.StoreInfoVo;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 门店
 *
 * @author heng.liu
 * @since 2021/1/12
 */
@Biz
public class StoreBiz {

    @Resource
    private StoreInfoService storeInfoService;

    public List<StorePreviewAo> queryAllSupportedStore() {
        List<StoreInfoVo> storeInfoVoList = storeInfoService.queryByStatus(StoreStatusEnum.ONLINE.getCode());
        return storeInfoVoList.stream()
                .map(storeInfoVo -> StorePreviewAo.builder().code(storeInfoVo.getStoreCode())
                        .name(storeInfoVo.getStoreName()).imageUrl(storeInfoVo.getImageUrl())
                        .iconUrl(storeInfoVo.getIconUrl()).addressTitle(storeInfoVo.getLocationTitle()).build())
                .collect(Collectors.toList());
    }

}
