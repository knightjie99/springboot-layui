package com.wujie.springbootlayui.bus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wujie.springbootlayui.bus.entity.StaffError;
import com.wujie.springbootlayui.bus.mapper.StaffErrorMapper;
import com.wujie.springbootlayui.bus.service.IStaffErrorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;


@Service
@Transactional
public class StaffErrorServiceImpl extends ServiceImpl<StaffErrorMapper, StaffError> implements IStaffErrorService {

    @Override
    public boolean save(StaffError entity) {
        return super.save(entity);
    }

    @Override
    public boolean updateById(StaffError entity) {
        return super.updateById(entity);
    }

    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    public StaffError getById(Serializable id) {
        return super.getById(id);
    }
}
