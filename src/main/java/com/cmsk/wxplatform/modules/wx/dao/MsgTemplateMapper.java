package com.cmsk.wxplatform.modules.wx.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cmsk.wxplatform.modules.wx.entity.MsgTemplate;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@CacheNamespace(flushInterval = 300000L)//缓存五分钟过期
public interface MsgTemplateMapper extends BaseMapper<MsgTemplate> {
}
