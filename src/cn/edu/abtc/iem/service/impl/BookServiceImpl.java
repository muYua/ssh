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

	@Override/*�������*/
	@Transactional
	public void save(Book entity) {
		bookDao.save(entity);
	}
	
	@Override/*sql����������*/
	@Transactional
	public void insert(Book entity) {
		bookDao.insert(entity);
	}
	
	@Override/*ɾ������*/
	@Transactional
	public void delete(Serializable id) {
		bookDao.delete(Book.class, id);
		System.out.println(id+"---id delete service");
	}

	@Override/*��ѯ��������*/
	@Transactional
	public List<Book> findAll(Class<Book> entityClazz) {
		return bookDao.findAll(entityClazz);
	}

	@Override/*�жϸ�ͼ����Ϣ�Ƿ����*/
	@Transactional
	public boolean isExist(int id) {
		Book book = bookDao.get(Book.class, id);
		if(book==null)
			return false;
		else
			return true;
	}

	@Override/*�޸�����*/
	@Transactional
	public void update(Book entity) {
		bookDao.update(entity);
	}

	@Override/*���ݹؼ��ʲ�ѯͼ����Ϣ*/
	@Transactional
	public List<Book> findByKey(String key,Serializable vlue) {
		
		return bookDao.findByKey(key, vlue);
	}

	@Override/*ģ����ѯͼ����*/
	@Transactional
	public List<Book> queryBookName(String bookName) {
		return bookDao.queryBookName(bookName);
	}

}
