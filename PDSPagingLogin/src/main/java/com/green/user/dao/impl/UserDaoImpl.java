package com.green.user.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.user.dao.UserDao;
import com.green.user.vo.UserVo;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

	@Autowired
	private SqlSession  sqlSession;
	
	@Override
	public void insertUser(UserVo user) {
		
		// sqlSession  :  MyBatis 
		sqlSession.insert("User.UserInsert", user);
		
	}

	@Override
	public List getUserList() {
		
		List    userList  =  sqlSession.selectList("User.UserList"); 
		
		return  userList;
	}

	@Override
	public UserVo getUser(String userid) {
		
		UserVo  vo  =  sqlSession.selectOne("User.GetUser", userid );
		
		return  vo;
	}

	@Override
	public void deleteUser(String userid) {
		
		sqlSession.delete("User.UserDelete", userid );
		
	}

	@Override
	public void updateUser(UserVo vo) {
		
		sqlSession.update("User.UserUpdate", vo);
		
	}

	@Override
	public UserVo getLoign(HashMap<String, Object> map) {
		UserVo userVo  =  sqlSession.selectOne("User.GetLogin",map);
		return userVo;
	}
	
	
	/*
	@Autowired	
	private BasicDataSource  dataSource;
	
	@Override
	public void insertUser(UserVo user) {
		
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		
		try {
			conn        = dataSource.getConnection();
			String sql  = "INSERT INTO TUSER (USERID, PASSWD, USERNAME, EMAIL) ";
			sql        += " VALUES           (?,      ?,      ?,        ?) ";
			pstmt       = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserid()   );
			pstmt.setString(2, user.getPasswd()   );
			pstmt.setString(3, user.getUsername() );
			pstmt.setString(4, user.getEmail()    );
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	*/

}
