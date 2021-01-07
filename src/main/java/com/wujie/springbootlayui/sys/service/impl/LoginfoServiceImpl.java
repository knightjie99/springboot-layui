package com.wujie.springbootlayui.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wujie.springbootlayui.sys.entity.Loginfo;
import com.wujie.springbootlayui.sys.mapper.LoginfoMapper;
import com.wujie.springbootlayui.sys.service.ILoginfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoginfoServiceImpl extends ServiceImpl<LoginfoMapper, Loginfo> implements ILoginfoService {

}
