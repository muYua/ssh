package cn.edu.abtc.iem.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.edu.abtc.iem.dao.BookDao;
import cn.edu.abtc.iem.domain.Book;
import cn.edu.abtc.iem.service.BookService;

public class BookServiceImpl implements BookService<Book> {
	private BookDao<Book> bookDao;
	public BookDao<Book> getBookDao() {
		return bookDao;
	}

	public void setBookDao(BookDao<Book> bookDao) {
		this.bookDao = bookDao;
	}

	@Override/*添加数据*/
	@Transactional
	public void save(Book entity) {
		bookDao.save(entity);
	}
	
	@Override/*sql语句添加数据*/
	@Transactional
	public void insert(Book entity) {
		bookDao.insert(entity);
	}
	
	@Override/*删除数据*/
	@Transactional
	public void delete(Serializable id) {
		bookDao.delete(Book.class, id);
		System.out.println(id+"---id delete service");
	}

	@Override/*查询所有数据*/
	@Transactional
	public List<Book> findAll(Class<Book> entityClazz) {
		return bookDao.findAll(entityClazz);
	}

	@Override/*判断该图书信息是否存在*/
	@Transactional
	public boolean isExist(int id) {
		Book book = bookDao.get(Book.class, id);
		if(book==null)
			return false;
		else
			return true;
	}

	@Override/*修改数据*/
	@Transactional
	public void update(Book entity) {
		bookDao.update(entity);
	}

	@Override/*根据关键词查询图书信息*/
	@Transactional
	public List<Book> findByKey(String key,Serializable vlue) {
		
		return bookDao.findByKey(key, vlue);
	}

	@Override/*模糊查询图书名*/
	@Transactional
	public List<Book> queryBookName(String bookName) {
		return bookDao.queryBookName(bookName);
	}

}
