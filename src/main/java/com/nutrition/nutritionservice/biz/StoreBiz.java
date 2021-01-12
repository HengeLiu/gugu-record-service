package com.nutrition.nutritionservice.biz;

import com.nutrition.nutritionservice.annotation.Biz;
import com.nutrition.nutritionservice.controller.ao.StorePreviewAo;
import com.nutrition.nutritionservice.service.StoreInfoService;
import com.nutrition.nutritionservice.service.StoreService;
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
    private StoreService storeService;

    @Resource
    private StoreInfoService storeInfoService;

    public List<StorePreviewAo> queryAllStore() {
        List<StoreInfoVo> storeInfoVoList = storeInfoService.queryAll();
        return storeInfoVoList.stream()
                .map(storeInfoVo -> StorePreviewAo.builder().code(storeInfoVo.getStoreCode())
                        .name(storeInfoVo.getStoreName()).imageUrl(storeInfoVo.getImageUrl())
                        .iconUrl(storeInfoVo.getIconUrl()).build())
                .collect(Collectors.toList());
    }

}
