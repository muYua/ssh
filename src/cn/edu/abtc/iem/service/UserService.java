package cn.edu.abtc.iem.service;

import java.io.Serializable;
import java.util.List;

public interface UserService<T> extends BaseService<T> {
	//查询所有数据
	public List<T> findAll(Class<T> entityClazz);
	//修改数据
	public void update(T entity,Serializable id,String userName,String password);
	//根据用户名查询用户(模糊查询)
	public List<T> qurey(String userName);
	//根据用户名查询用户(准确查询)
	public List<T> findByName(String userName);
	//判断是否存在该用户名
	public boolean isExist(String userName);
	//判断是否存在该用户
	public boolean isExist(Serializable id);
	//根据id查询用户信息
	public T findUserById(Serializable id);
}
