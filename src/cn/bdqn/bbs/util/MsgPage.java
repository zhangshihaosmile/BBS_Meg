package cn.bdqn.bbs.util;

import java.util.List;

import cn.bdqn.bbs.entity.Msg;

/**
 * ��Ϣ�ķ�ҳ������
 * @author Jack
 *
 */
public class MsgPage {
	private int pageIndex=1;//ҳ��
	private int pageSize=10;//ҳ��ߴ�
	private int totalCount;//������
	private int totalPageCount;//��ҳ��
	
	private List<Msg> msgList;//��ҳ������ݼ���

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		if (pageIndex>0) {
			this.pageIndex = pageIndex;
		}
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		if (pageSize>0) {
			this.pageSize = pageSize;
		}
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		if (totalCount>0) {
			this.totalCount = totalCount;
			totalPageCount=
					totalCount%pageSize==0?
							totalCount/pageSize:
								totalCount/pageSize+1;
		}
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public List<Msg> getMsgList() {
		return msgList;
	}

	public void setMsgList(List<Msg> msgList) {
		this.msgList = msgList;
	}
	
	
}
