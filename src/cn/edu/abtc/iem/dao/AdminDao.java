package cn.edu.abtc.iem.dao;

import java.util.List;
public interface AdminDao<T> extends BaseDao<T>{
	//用户校验
	public List<T> check(String userName,String password);
	//根据用户名查询用户（模糊查询）
	public List<T> queryByName(String userName);
	//根据用户名查询用户（准确查询）
	public List<T> findByName(String userName);
	
}
