package com.wujie.springbootlayui.bus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wujie.springbootlayui.bus.entity.StaffError;
import org.apache.ibatis.annotations.Param;

public interface StaffErrorMapper extends BaseMapper<StaffError> {
    void deleteStaffErrorByUid(@Param("belongId") Integer id);
}
