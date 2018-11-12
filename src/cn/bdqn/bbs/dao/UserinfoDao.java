package cn.bdqn.bbs.dao;

import java.util.List;

import cn.bdqn.bbs.entity.Userinfo;

public interface UserinfoDao {
	//登录、注册、查询所有用户列表
	/**
	 * 根据用户名和密码查询用户对象
	 * @param userParam 用户对象中包含用户名和密码做为查询条件
	 * @return
	 */
	Userinfo findUser(Userinfo userParam);
	/**
	 * 查询所有用户
	 * @return
	 */
	List<Userinfo> findAll();
	/**
	 * 向数据库中添加一条用户数据（注册功能）
	 * @param userinfo
	 * @return
	 */
	int addUser(Userinfo userinfo);
}
