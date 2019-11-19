package cn.edu.abtc.iem.service;

import java.io.Serializable;
import java.util.List;

public interface BookService<T> extends BaseService<T> {
	//��ѯ��������
	public List<T> findAll(Class<T> entityClazz);
	//�ж��Ƿ���ڸ�ͼ����Ϣ
	public boolean isExist(int bookId);
	//�޸�ͼ����Ϣ
	public void update(T entity);
	//����������ѯ
	public List<T> findByKey(String key, Serializable vlue);
	//ģ����ѯͼ����
	public List<T> queryBookName(String bookName);
	//ʵ��������
	public void save(T entity);
	
}
