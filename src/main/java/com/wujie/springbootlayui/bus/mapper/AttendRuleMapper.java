package com.wujie.springbootlayui.bus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wujie.springbootlayui.bus.entity.AttendRule;
import org.apache.ibatis.annotations.Param;

public interface AttendRuleMapper extends BaseMapper<AttendRule>{

    void deleteAttendRuleByUid(@Param("belong_id") Integer id);
}
