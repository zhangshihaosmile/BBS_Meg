package cn.bdqn.bbs.dao;

import java.util.List;

import cn.bdqn.bbs.entity.Msg;

/**
 * 消息数据访问接口
 * @author Jack
 *
 */
public interface MsgDao {
	/**
	 * 分页查询所有消息
	 * @param pageIndex 页码
	 * @param pageSize 数据行数
	 * @return
	 */
	List<Msg> listAll(int pageIndex,int pageSize);
	/**
	 * 根据id查询消息详情
	 * @param id 消息编号
	 * @return
	 */
	Msg findById(int id,String username);
	/**
	 * 根据id修改消息状态
	 * @param msg
	 * @return
	 */
	int updateMsg(Msg msg);
	/**
	 * 向数据库中添加消息（相当于给别人发消息）
	 * @param msg
	 * @return
	 */
	int addMsg(Msg msg);
	/**
	 * 统计所有消息的数量
	 * @return
	 */
	int count();
	List<Msg> listAll(int pageIndex, int pageSize, String username);
	int del(int id, String username);
}
