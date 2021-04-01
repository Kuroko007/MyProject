package com.cmsk.wxplatform.modules.wx.dao;

import com.cmsk.wxplatform.modules.wx.entity.WxMsg;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;

/**
 * 微信消息
 * 
 * @author niefy
 * @date 2020-05-14 17:28:34
 */
@Mapper
@CacheNamespace(flushInterval = 10*1000L)//缓存过期时间（毫秒）
public interface WxMsgMapper extends BaseMapper<WxMsg> {

    void insertMsgInfo(WxMsg msg);
}
