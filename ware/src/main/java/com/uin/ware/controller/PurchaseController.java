package com.uin.ware.controller;


import com.uin.utils.PageUtils;
import com.uin.utils.R;
import com.uin.ware.entity.PurchaseEntity;
import com.uin.ware.service.PurchaseService;
import com.uin.ware.vo.MergeVo;
import com.uin.ware.vo.PurchaseDoneVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 采购信息
 *
 * @author wanglufei
 * @email 1634060836@qq.com
 * @date 2022-04-18 19:41:51
 */
@RestController
@RequestMapping("ware/purchase")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    // /ware/purchase/done
    /**
     * 完成采购
     * {
     *    id: 123,//采购单id
     *    items: [{itemId:1,status:4,reason:""}]//完成/失败的需求详情
     * }
     */
    @PostMapping("/purchase/done")
    public R donePurchase(@RequestBody PurchaseDoneVo vo) {
       purchaseService.done(vo);
        return R.ok();
    }

    // /ware/purchase/unreceive/list

    /**
     * 查询未领取的采购单
     */
    @RequestMapping("/unreceive/list")
    public R unReceiveList(@RequestParam Map<String, Object> params) {
        PageUtils page = purchaseService.unReceiveList(params);
        return R.ok().put("page", page);
    }
    ///ware/purchase/merge

    /**
     * 合并采购需求
     */
    @PostMapping("/merge")
    /**
     * @RequiresPermissions("ware:purchase:list")
     */
    public R merge(@RequestBody MergeVo mergeVo) {
        //purchaseId: 1, //整单id
        //  items:[1,2,3,4] //合并项集合
        purchaseService.mergePurchase(mergeVo);
        return R.ok();
    }

    ///ware/purchase/received

    /**
     * 领取采购单
     */
    @PostMapping("/received")
    public R received(@RequestBody List<Long> ids) {
        // [1,2,3,4] 采购单的数组
        purchaseService.received(ids);
        return R.ok();
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    /**
     * @RequiresPermissions("ware:purchase:list")
     */
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = purchaseService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    /**
     *@RequiresPermissions("ware:purchase:info")
     */
    public R info(@PathVariable("id") Long id) {
        PurchaseEntity purchase = purchaseService.getById(id);

        return R.ok().put("purchase", purchase);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    /**
     *@RequiresPermissions("ware:purchase:save")
     */

    public R save(@RequestBody PurchaseEntity purchase) {
        purchase.setCreateTime(new Date());
        purchase.setUpdateTime(new Date());
        purchaseService.save(purchase);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    /**
     *@RequiresPermissions("ware:purchase:update")
     */
    public R update(@RequestBody PurchaseEntity purchase) {
        purchaseService.updateById(purchase);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    /**
     *@RequiresPermissions("ware:purchase:delete")
     */
    public R delete(@RequestBody Long[] ids) {
        purchaseService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
