package com.wujie.springbootlayui.bus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wujie.springbootlayui.bus.entity.StaffCard;

public interface IStaffCardService  extends IService<StaffCard>{

    String getBinded(Integer belongId);
}
