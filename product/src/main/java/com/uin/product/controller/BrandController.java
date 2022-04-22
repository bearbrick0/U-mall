package com.uin.product.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.uin.utils.PageUtils;
import com.uin.utils.R;
import com.uin.valid.AddGroup;
import com.uin.valid.UpdateGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uin.product.entity.BrandEntity;
import com.uin.product.service.BrandService;

import javax.validation.Valid;


/**
 * 品牌
 *
 * @author wanglufei
 * @email 1634060836@qq.com
 * @date 2022-04-18 18:05:16
 */
@RestController
@RequestMapping("product/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    /**
     *@RequiresPermissions("product:brand:list")
     */
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = brandService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{brandId}")
    /**
     *@RequiresPermissions("product:brand:info")
     */
    public R info(@PathVariable("brandId") Long brandId) {
        BrandEntity brand = brandService.getById(brandId);

        return R.ok().put("brand", brand);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    /**
     *@RequiresPermissions("product:brand:save")
     */
    public R save(@Validated({AddGroup.class}) @RequestBody BrandEntity brand) {
        //BindingResult result
//        if (result.hasErrors()) {
//            Map<String, String> map = new HashMap<>();
//            result.getFieldErrors().forEach((item) -> {
//                //消息提示
//                String message = item.getDefaultMessage();
//                //那个字段错误
//                String itemField = item.getField();
//                map.put(itemField, message);
//            });
//            return R.error(400, "品牌名不能为空").put("data",map);
//        } else {
//        }
        brandService.save(brand);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    /**
     *@RequiresPermissions("product:brand:update")
     */
    public R update(@Validated(UpdateGroup.class)  @RequestBody BrandEntity brand) {
        brandService.updateById(brand);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    /**
     *@RequiresPermissions("product:brand:delete")
     */
    public R delete(@RequestBody Long[] brandIds) {
        brandService.removeByIds(Arrays.asList(brandIds));

        return R.ok();
    }

}
