package cn.edu.abtc.iem.dao;

import java.io.Serializable;
import java.util.List;

import cn.edu.abtc.iem.domain.Book;

public interface BookDao<T> extends BaseDao<T> {
	//ͨ��insert������
	public void insert(T book);
	//���ݹؼ��ʲ���
	public List<Book> findByKey(String key,Serializable vlue);
	//ģ����ѯ����
	public List<Book> queryBookName(String bookName);
	

}
