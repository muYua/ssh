package cn.edu.abtc.iem.service;

import java.io.Serializable;
import java.util.List;

public interface AdminService<T> extends BaseService<T> {
	//登录用户校验
	public boolean check(String userName,String password);
	//查询所有数据
	public List<T> findAll(Class<T> entityClazz);
	//修改数据
	public void update(T entity,Serializable id,String adminName,String password);
	//模糊查询
	public List<T> qurey(String adminName);
	//准确查询
	public List<T> findByName(String adminName);
	//判断是否存在该管理员用户名
	public boolean isExist(String adminName);
}
