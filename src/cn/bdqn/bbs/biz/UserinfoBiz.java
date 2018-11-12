package cn.bdqn.bbs.biz;

import java.util.List;

import cn.bdqn.bbs.entity.Userinfo;

public interface UserinfoBiz {
	
	/**
	 * ��¼����
	 * @param user
	 * @return
	 */
	Userinfo login(Userinfo user);
	/**
	 * ע��һ���û�
	 * @param user
	 * @return
	 */
	boolean regist(Userinfo user);
	/**
	 * ��ѯ�����û�
	 * @return
	 */
	List<Userinfo> listAll();
}
