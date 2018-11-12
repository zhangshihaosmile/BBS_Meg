package cn.bdqn.bbs.dao;

import java.util.List;

import cn.bdqn.bbs.entity.Userinfo;

public interface UserinfoDao {
	//��¼��ע�ᡢ��ѯ�����û��б�
	/**
	 * �����û����������ѯ�û�����
	 * @param userParam �û������а����û�����������Ϊ��ѯ����
	 * @return
	 */
	Userinfo findUser(Userinfo userParam);
	/**
	 * ��ѯ�����û�
	 * @return
	 */
	List<Userinfo> findAll();
	/**
	 * �����ݿ������һ���û����ݣ�ע�Ṧ�ܣ�
	 * @param userinfo
	 * @return
	 */
	int addUser(Userinfo userinfo);
}
