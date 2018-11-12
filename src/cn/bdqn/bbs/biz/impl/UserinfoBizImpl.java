package cn.bdqn.bbs.biz.impl;

import java.util.List;

import cn.bdqn.bbs.biz.UserinfoBiz;
import cn.bdqn.bbs.dao.UserinfoDao;
import cn.bdqn.bbs.dao.impl.UserinfoDaoImpl;
import cn.bdqn.bbs.entity.Userinfo;

public class UserinfoBizImpl implements UserinfoBiz{
	private UserinfoDao userinfoDao=new UserinfoDaoImpl();
	@Override
	public Userinfo login(Userinfo user) {
		return userinfoDao.findUser(user);
	}

	@Override
	public boolean regist(Userinfo user) {
		int result=userinfoDao.addUser(user);
		if (result==1) {
			return true;
		}
		return false;
	}

	@Override
	public List<Userinfo> listAll() {
		return userinfoDao.findAll();
	}

}
