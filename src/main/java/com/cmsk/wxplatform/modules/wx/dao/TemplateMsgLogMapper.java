package com.cmsk.wxplatform.modules.wx.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cmsk.wxplatform.modules.wx.entity.TemplateMsgLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TemplateMsgLogMapper extends BaseMapper<TemplateMsgLog> {
    void insertTemplateMsgLog(TemplateMsgLog log);
}
