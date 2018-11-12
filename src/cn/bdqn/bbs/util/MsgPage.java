package cn.bdqn.bbs.util;

import java.util.List;

import cn.bdqn.bbs.entity.Msg;

/**
 * 消息的分页工具类
 * @author Jack
 *
 */
public class MsgPage {
	private int pageIndex=1;//页面
	private int pageSize=10;//页面尺寸
	private int totalCount;//总数量
	private int totalPageCount;//总页数
	
	private List<Msg> msgList;//分页后的数据集合

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
