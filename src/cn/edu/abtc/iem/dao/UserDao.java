package cn.edu.abtc.iem.dao;

import java.io.Serializable;
import java.util.List;

public interface UserDao<T> extends BaseDao<T>{
	//用户校验
	public boolean check(String userName,String password);
	//根据用户名查询用户（模糊查询）
	public List<T> queryByName(String userName);
	//根据用户名查询用户（准确查询）
	public List<T> findByName(String userName);
	//根据用户id查询用户信息
	public List<T> findById(Serializable id);
	
}
