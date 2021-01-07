package com.wujie.springbootlayui.bus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wujie.springbootlayui.bus.entity.StaffCard;
import org.apache.ibatis.annotations.Param;

public interface StaffCardMapper extends BaseMapper<StaffCard>{
    String getBinded(@Param("belongId") Integer belongId);
    void deleteStaffCardByUid(@Param("belongId") Integer id);
}
