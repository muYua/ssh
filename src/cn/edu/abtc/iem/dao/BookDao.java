package cn.edu.abtc.iem.dao;

import java.io.Serializable;
import java.util.List;

import cn.edu.abtc.iem.domain.Book;

public interface BookDao<T> extends BaseDao<T> {
	//通过insert语句插入
	public void insert(T book);
	//根据关键词查找
	public List<Book> findByKey(String key,Serializable vlue);
	//模糊查询书名
	public List<Book> queryBookName(String bookName);
	

}
