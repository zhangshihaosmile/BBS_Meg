package cn.bdqn.bbs.biz;

import java.util.List;

import cn.bdqn.bbs.entity.Userinfo;

public interface UserinfoBiz {
	
	/**
	 * 登录操作
	 * @param user
	 * @return
	 */
	Userinfo login(Userinfo user);
	/**
	 * 注册一个用户
	 * @param user
	 * @return
	 */
	boolean regist(Userinfo user);
	/**
	 * 查询所有用户
	 * @return
	 */
	List<Userinfo> listAll();
}
