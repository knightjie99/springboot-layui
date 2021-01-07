package com.wujie.springbootlayui.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wujie.springbootlayui.bus.mapper.*;
import com.wujie.springbootlayui.sys.config.MybatisPlusConfig;
import com.wujie.springbootlayui.sys.entity.DeskParam;
import com.wujie.springbootlayui.sys.entity.User;
import com.wujie.springbootlayui.sys.mapper.DeptMapper;
import com.wujie.springbootlayui.sys.mapper.RoleMapper;
import com.wujie.springbootlayui.sys.mapper.UserMapper;
import com.wujie.springbootlayui.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private AttendMapper attendMapper;

	@Autowired
	private PassRecordMapper passRecordMapper;

	@Autowired
	private VipVisitorMapper vipVisitorMapper;

	@Autowired
	private TempVisitorMapper tempVisitorMapper;

	@Autowired
	private StaffMapper staffMapper;

	@Autowired
	private GateMapper gateMapper;

	@Autowired
	private BackupMapper backupMapper;

	@Autowired
	private BlackVisitorMapper blackVisitorMapper;

	@Autowired
	private GateAuthorMapper gateAuthorMapper;

	@Autowired
	private PersonErrorMapper personErrorMapper;

	@Autowired
	private StaffErrorMapper staffErrorMapper;

	@Autowired
	private DeptMapper deptMapper;

	@Autowired
	private StaffCardMapper staffCardMapper;

	@Override
	public boolean save(User entity) {
		return super.save(entity);
	}

	@Override
	public boolean updateById(User entity) {
		return super.updateById(entity);
	}

	@Override
	public User getById(Serializable id) {
		return super.getById(id);
	}

	@Override
	public boolean removeById(Serializable id) {
		// 根据用户id删除用户角色中间表的数据
		roleMapper.deleteRoleUserByUid(id);
		// 删除用户头像[如果是默认头像不删除，否则删除]

		return super.removeById(id);
	}

	/**
	 * 保存用户和角色的关系
	 *
	 * @param uid
	 *            用户的ID
	 * @param ids
	 *            用户拥有的角色的ID的数组
	 */
	@Override
	public void saveUserRole(Integer uid, Integer[] rid) {
		// 1.根据用户ID删除sys_user_role里面的数据
		roleMapper.deleteRoleUserByUid(uid);
		System.out.println("rid:" + rid);
		if (null != rid) {
			for (int i = 0; i < rid.length; i++) {
				roleMapper.insertUserRole(uid, rid[i]);
			}

		}
		// if (null!=ids&&ids.length>0){
		// for (Integer rid : ids) {
		// roleMapper.insertUserRole(uid,rid);
		// }
		// }
	}

	@Override
	public Integer queryUserByLoginName(String loginName) {
		return this.getBaseMapper().queryUserByLoginName(loginName);
	}

	@Override
	public DeskParam getDesk(Integer belongId, String time) {
		return this.getBaseMapper().getDesk(belongId, time);
	}

	@Override
	public Integer queryUserByPhone(String phone) {
		return this.getBaseMapper().queryUserByPhone(phone);
	}

	@Override
	public Integer insertUser(User user) {
		this.getBaseMapper().insert(user);
		Integer id = user.getId();
		return id;
	}

	@Override
	public void deleteUserBelongs(Integer belongId) {
		backupMapper.deleteBackupByUid(belongId);
		blackVisitorMapper.deleteBlackVisitorByUid(belongId);
		gateMapper.deleteGateByUid(belongId);
		gateAuthorMapper.deleteGateAuthorByUid(belongId);
		personErrorMapper.deletePersonErrorByUid(belongId);
		staffMapper.deleteStaffByUid(belongId);
		staffCardMapper.deleteStaffCardByUid(belongId);
		staffErrorMapper.deleteStaffErrorByUid(belongId);
		tempVisitorMapper.deleteTempVisitorByUid(belongId);
		vipVisitorMapper.deleteVipByUid(belongId);
		deptMapper.deleteDeptByUid(belongId);

		String tName = "bus_pass_record_" + belongId;
		passRecordMapper.deleteTable(tName);

		String tName2 = "bus_attend_" + belongId;
		MybatisPlusConfig.attendTName.set(tName2);
		attendMapper.deleteTable(tName2);
	}
}
