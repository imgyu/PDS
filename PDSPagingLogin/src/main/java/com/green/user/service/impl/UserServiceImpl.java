package com.green.user.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.user.dao.UserDao;
import com.green.user.service.UserService;
import com.green.user.vo.UserVo;

// UserServiceImpl userService = new UserServiceImpl();

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao  userDao;
	
	@Override
	public void insertUser(UserVo user) {
		
		userDao.insertUser( user );
		
	}

	@Override
	public List getUserList() {
		
		List    userList = userDao.getUserList();
		
		return  userList;
	}

	@Override
	public UserVo getUser(String userid) {
		
		UserVo  vo  =  userDao.getUser( userid );
		
		return  vo;
	}

	@Override
	public void deleteUser(String userid) {
		
		userDao.deleteUser( userid  );
		
	}

	@Override
	public void updateUser(UserVo vo) {
		
		userDao.updateUser(  vo  );
		
	}

	@Override
	public UserVo getLogin(HashMap<String, Object> map) {
		UserVo  userVo  =  userDao.getLoign(map);
		return userVo;
	}
	
}















