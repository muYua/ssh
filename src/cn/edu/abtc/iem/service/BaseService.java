package cn.edu.abtc.iem.service;

import java.io.Serializable;

public interface BaseService<T> {
	//�������
	public void insert(T entity);
	//ɾ������
	public void delete(Serializable id);
}
