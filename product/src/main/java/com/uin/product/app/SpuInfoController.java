package com.uin.product.app;

import java.util.Arrays;
import java.util.Map;

import com.uin.product.vo.SpuSaveVo;
import com.uin.utils.PageUtils;
import com.uin.utils.R;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.uin.product.entity.SpuInfoEntity;
import com.uin.product.service.SpuInfoService;


/**
 * spu信息
 *
 * @author wanglufei
 * @email 1634060836@qq.com
 * @date 2022-04-18 18:05:16
 */
@RestController
@RequestMapping("product/spuinfo")
public class SpuInfoController {
    @Autowired
    private SpuInfoService spuInfoService;

    /**
     * 商品上架
     * product/spuinfo/{spuId}/up
     */
    @PostMapping("/{spuId}/up")
    public R upSpu(@PathVariable Long spuId) {
        //商品上架主要是给ES中存储数据
        spuInfoService.upSpuToES(spuId);
        return R.ok();
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    /**
     *@RequiresPermissions("product:spuinfo:list")
     */
    public R list(@RequestParam Map<String, Object> params) {
        //PageUtils page = spuInfoService.queryPage(params);
        PageUtils page = spuInfoService.queryPageByCodition(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    /**
     *@RequiresPermissions("product:spuinfo:info")
     */
    public R info(@PathVariable("id") Long id) {
        SpuInfoEntity spuInfo = spuInfoService.getById(id);

        return R.ok().put("spuInfo", spuInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    /**
     *@RequiresPermissions("product:spuinfo:save")
     */
    public R save(@RequestBody SpuSaveVo vo) {
        //spuInfoService.save(spuInfo);
        spuInfoService.saveSpuSaveVo(vo);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    /**
     *@RequiresPermissions("product:spuinfo:update")
     */
    public R update(@RequestBody SpuInfoEntity spuInfo) {
        spuInfoService.updateById(spuInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    /**
     *@RequiresPermissions("product:spuinfo:delete")
     */
    public R delete(@RequestBody Long[] ids) {
        spuInfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
