package com.cmsk.wxplatform.modules.wx.service;

import com.google.gson.Gson;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import me.chanjar.weixin.mp.util.WxMpConfigStorageHolder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 程序发送模板消息demo
 */
@SpringBootTest
class TemplateMsgServiceTest {
    @Autowired
    TemplateMsgService templateMsgService;

    /**
     * 发送模板消息给用户
     * 添加消息模板指引：https://kf.qq.com/faq/170209E3InyI170209nIF7RJ.html
     * 示例消息模板为：{{first.DATA}} ↵商品名称：{{keyword1.DATA}} ↵购买时间：{{keyword2.DATA}} ↵{{remark.DATA}}
     */
    @Test
    void sendTemplateMsg() {
        String appid = WxMpConfigStorageHolder.get();
        //String appid = "wxd4d32375e8fd62a4";
        List<WxMpTemplateData> data  = new ArrayList<>();
        data.add(new WxMpTemplateData("first","模板消息测试"));
        data.add(new WxMpTemplateData("keyword1","16888888888"));
        data.add(new WxMpTemplateData("keyword2","888888元"));
        data.add(new WxMpTemplateData("keyword3","容器化部署实战"));
        data.add(new WxMpTemplateData("keyword4","18888888888"));
        data.add(new WxMpTemplateData("keyword5",new Gson().toJson(new Date())));
        data.add(new WxMpTemplateData("remark","点击查看消息详情"));
        WxMpTemplateMessage wxMpTemplateMessage = WxMpTemplateMessage.builder()
                .templateId("dHm8UK79zss2IB47LEj87U8iAKVKjd_9lm0JHuNyrVk")
                .url("https://developers.weixin.qq.com/doc/offiaccount/Basic_Information/Requesting_an_API_Test_Account.html")
                .toUser("omXel5hgP6o4fC_SsP8JnBnF6jwY")
                .data(data)
                .build();
        templateMsgService.sendTemplateMsg(wxMpTemplateMessage,appid);
    }
}
