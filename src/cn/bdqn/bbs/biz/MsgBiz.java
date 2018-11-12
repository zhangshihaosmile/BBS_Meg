package cn.bdqn.bbs.biz;

import cn.bdqn.bbs.entity.Msg;
import cn.bdqn.bbs.util.MsgPage;

public interface MsgBiz {
	void list(MsgPage page);

	/**
	 * ���ݵ�ǰ�û���ҳ��ѯ����
	 * @param msgPage
	 * @param username
	 */
	void list(MsgPage msgPage, String username);

	int updateRead(int id);

	Msg findById(int id,String username);

	int del(int id, String username);

	int addMsg(Msg msg);
}
