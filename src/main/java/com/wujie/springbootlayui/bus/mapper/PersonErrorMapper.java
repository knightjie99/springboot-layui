package com.wujie.springbootlayui.bus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wujie.springbootlayui.bus.entity.PersonError;
import org.apache.ibatis.annotations.Param;

public interface PersonErrorMapper extends BaseMapper<PersonError>{

    void deletePersonErrorByUid(@Param("belongId") Integer id);
}
