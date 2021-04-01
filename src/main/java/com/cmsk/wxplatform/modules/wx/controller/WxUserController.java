package com.cmsk.wxplatform.modules.wx.controller;

import com.cmsk.wxplatform.common.utils.R;
import com.cmsk.wxplatform.modules.wx.entity.WxUser;
import com.cmsk.wxplatform.modules.wx.service.WxUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.mp.api.WxMpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 微信用户（粉丝）
 */
@RestController
@RequestMapping("/wxUser")
@RequiredArgsConstructor
@Api(tags = {"微信粉丝"})
public class WxUserController {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    WxUserService wxUserService;
    private final WxMpService wxMpService;

    @GetMapping("/getUserInfo")
    @ApiOperation(value = "获取粉丝信息")
    public R getUserInfo(@CookieValue String appid,@CookieValue String openid){
        this.wxMpService.switchoverTo(appid);
        WxUser wxUser = wxUserService.getById(openid);
        return R.ok().put(wxUser);
    }

    @GetMapping("/get")
    @ApiOperation(value = "获取微信用户信息")
    public WxUser getWxUserInfo(@RequestParam("openId") String openId,@RequestParam("appId") String appId){
        return wxUserService.refreshUserInfo(openId, appId);
    }
}
