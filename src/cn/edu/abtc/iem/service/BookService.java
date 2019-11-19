package cn.edu.abtc.iem.service;

import java.io.Serializable;
import java.util.List;

public interface BookService<T> extends BaseService<T> {
	//查询所有数据
	public List<T> findAll(Class<T> entityClazz);
	//判断是否存在该图书信息
	public boolean isExist(int bookId);
	//修改图书信息
	public void update(T entity);
	//根据条件查询
	public List<T> findByKey(String key, Serializable vlue);
	//模糊查询图书名
	public List<T> queryBookName(String bookName);
	//实例化数据
	public void save(T entity);
	
}
