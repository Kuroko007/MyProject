package com.cmsk.wxplatform.modules.wx.controller;
import com.cmsk.wxplatform.common.auth.AuthContext;
import com.cmsk.wxplatform.common.auth.Authorized;
import com.cmsk.wxplatform.common.utils.DateUtils;
import com.cmsk.wxplatform.common.utils.SHA1Util;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * @author zhanglgstart
 * @create 2021-03-04 17:19
 */
@RestController
@RequestMapping("/cmsk")
@RequiredArgsConstructor
@Slf4j
public class WeChatController {


    private final AuthContext authContext;

    @GetMapping("/getToken")
    public String getToken(){
        String token = authContext.createToken("wxf1d925aec2445ca1", Long.valueOf(DateUtils.date2TimeStamp(DateUtils.getDateOfHalfHourAfter(), DateUtils.DATE_TIME_PATTERN)));
        log.info("token:{}",token);
        return token;
    }

    /**
     * 微信URL接入验证
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     */
    @RequestMapping(value="/weChat")
    @ResponseBody
    public String validate(String signature,String timestamp,String nonce,String echostr){
        //1. 将token、timestamp、nonce三个参数进行字典序排序
        String[] arr = {timestamp,nonce, SHA1Util.TOKEN};
        Arrays.sort(arr);
        //2. 将三个参数字符串拼接成一个字符串进行sha1加密
        StringBuilder sb = new StringBuilder();
        for (String temp : arr) {
            sb.append(temp);
        }
        //3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
        if(SHA1Util.sha1(sb.toString()).equals(signature)){
            //接入成功
            log.info("echostr={}",echostr);
            return echostr;
        }
        //接入失败
        log.info("接入失败");
        return null;
    }

}
