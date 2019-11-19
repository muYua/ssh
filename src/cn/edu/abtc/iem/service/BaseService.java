package cn.edu.abtc.iem.service;

import java.io.Serializable;

public interface BaseService<T> {
	//添加数据
	public void insert(T entity);
	//删除数据
	public void delete(Serializable id);
}
