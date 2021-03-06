package com.uin.member.controller;


import com.uin.member.entity.MemberEntity;
import com.uin.member.feign.MemberFeignCoupon;
import com.uin.member.service.MemberService;
import com.uin.utils.PageUtils;
import com.uin.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

/**
 * 会员
 *
 * @author wanglufei
 * @email 1634060836@qq.com
 * @date 2022-04-18 19:54:06
 */
@RestController
@RequestMapping("member/member")
public class MemberController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberFeignCoupon memberFeignCoupon;

    /**
     * 测试远程服务调用
     *
     * @return com.uin.utils.R
     * @author wanglufei
     * @date 2022/4/19 2:35 PM
     */
    @RequestMapping("/coupons")
    private R coupons() {
        MemberEntity member = new MemberEntity();
        member.setNickname("张三");
        R r = memberFeignCoupon.memberWithCoupons();
        return R.ok().put("member", member).put("coupon", r.get("coupons"));
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    /**
     *@RequiresPermissions("member:member:list")
     */
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = memberService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    /**
     *@RequiresPermissions("member:member:info")
     */
    public R info(@PathVariable("id") Long id) {
        MemberEntity member = memberService.getById(id);

        return R.ok().put("member", member);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    /**
     *@RequiresPermissions("member:member:save")
     */
    public R save(@RequestBody MemberEntity member) {
        memberService.save(member);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    /**
     *@RequiresPermissions("member:member:update")
     */
    public R update(@RequestBody MemberEntity member) {
        memberService.updateById(member);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    /**
     *@RequiresPermissions("member:member:delete")
     */
    public R delete(@RequestBody Long[] ids) {
        memberService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
