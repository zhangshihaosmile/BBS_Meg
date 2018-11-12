package cn.bdqn.bbs.biz.impl;

import java.util.List;

import cn.bdqn.bbs.biz.MsgBiz;
import cn.bdqn.bbs.dao.MsgDao;
import cn.bdqn.bbs.dao.impl.MsgDaoImpl;
import cn.bdqn.bbs.entity.Msg;
import cn.bdqn.bbs.util.MsgPage;

public class MsgBizImpl implements MsgBiz{
	private MsgDao msgDao=new MsgDaoImpl();
	@Override
	public void list(MsgPage page) {
		int count=msgDao.count();//��ȡ������Ϣ������
		page.setTotalCount(count);
		//�жϴ����ҳ���Ƿ�Ϸ�
		if(page.getPageIndex()>page.getTotalPageCount()){
			page.setPageIndex(page.getTotalPageCount());
			//ȷ��ҳ�治�ᳬ����ҳ��
		}
		List<Msg> msgList=msgDao.listAll(page.getPageIndex(), page.getPageSize());
		page.setMsgList(msgList);
	}
	@Override
	public void list(MsgPage msgPage, String username) {
		int count=msgDao.count();//��ȡ������Ϣ������
		msgPage.setTotalCount(count);
		//�жϴ����ҳ���Ƿ�Ϸ�
		if(msgPage.getPageIndex()>msgPage.getTotalPageCount()){
			msgPage.setPageIndex(msgPage.getTotalPageCount());
			//ȷ��ҳ�治�ᳬ����ҳ��
		}
		List<Msg> msgList=msgDao.listAll(msgPage.getPageIndex(), msgPage.getPageSize(),username);
		msgPage.setMsgList(msgList);
	}
	@Override
	public int updateRead(int id) {
		Msg msg=new Msg();
		msg.setMsgid(id);
		msg.setState(1);//�Ѷ�
		int result=msgDao.updateMsg(msg);
		return result;
	}
	@Override
	public Msg findById(int id,String username) {
		return msgDao.findById(id,username);
	}
	@Override
	public int del(int id,String username) {
		return msgDao.del(id,username);
	}
	@Override
	public int addMsg(Msg msg) {
		return msgDao.addMsg(msg);
	}

}
