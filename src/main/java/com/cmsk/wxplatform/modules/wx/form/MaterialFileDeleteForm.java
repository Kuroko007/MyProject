package com.cmsk.wxplatform.modules.wx.form;

import com.cmsk.wxplatform.common.utils.Json;
import lombok.Data;

@Data
public class MaterialFileDeleteForm {
    String mediaId;

    @Override
    public String toString() {
        return Json.toJsonString(this);
    }
}
