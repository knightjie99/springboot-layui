package com.wujie.springbootlayui.bus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wujie.springbootlayui.bus.entity.Backup;
import org.apache.ibatis.annotations.Param;

public interface BackupMapper extends BaseMapper<Backup>{
    void deleteBackupByUid(@Param("belongId") Integer id);
}
