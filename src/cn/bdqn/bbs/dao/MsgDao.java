package cn.bdqn.bbs.dao;

import java.util.List;

import cn.bdqn.bbs.entity.Msg;

/**
 * ��Ϣ���ݷ��ʽӿ�
 * @author Jack
 *
 */
public interface MsgDao {
	/**
	 * ��ҳ��ѯ������Ϣ
	 * @param pageIndex ҳ��
	 * @param pageSize ��������
	 * @return
	 */
	List<Msg> listAll(int pageIndex,int pageSize);
	/**
	 * ����id��ѯ��Ϣ����
	 * @param id ��Ϣ���
	 * @return
	 */
	Msg findById(int id,String username);
	/**
	 * ����id�޸���Ϣ״̬
	 * @param msg
	 * @return
	 */
	int updateMsg(Msg msg);
	/**
	 * �����ݿ��������Ϣ���൱�ڸ����˷���Ϣ��
	 * @param msg
	 * @return
	 */
	int addMsg(Msg msg);
	/**
	 * ͳ��������Ϣ������
	 * @return
	 */
	int count();
	List<Msg> listAll(int pageIndex, int pageSize, String username);
	int del(int id, String username);
}
