package cn.edu.abtc.iem.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.edu.abtc.iem.dao.BookDao;
import cn.edu.abtc.iem.domain.Book;

public class BookDaoImpl extends HibernateDaoSupport implements BookDao<Book>,HibernateCallback<Book> {
	@Override/*����id�õ�ʵ��*/
	public Book get(Class<Book> entityClazz, Serializable id) {
		return getHibernateTemplate().get(entityClazz, id);
	}

	@Override/*ʵ����ʵ��*/
	public Serializable save(Book entity) {
		return getHibernateTemplate().save(entity);
	}
	
	@Override/*ʵ����ʵ������*/
	public void insert(Book book) {
		String sql="INSERT INTO ssh_book(book_name,book_price,book_author) VALUES('"+book.getBookName()+"','"+book.getBookPrice()+"','"+book.getAuthor()+"')";
		getSessionFactory().getCurrentSession().createSQLQuery(sql).executeUpdate();
	}
	
	@Override/*�޸�ʵ��*/
	public void update(Book entity) {
		getHibernateTemplate().update(entity);
	}

	@Override/*ɾ��ʵ��*/
	public void delete(Book entity) {
		getHibernateTemplate().delete(entity);
	}

	@Override/*����idɾ��ʵ��*/
	public void delete(Class<Book> entityClazz, Serializable id) {
		Book book = this.get(entityClazz, id);
		getHibernateTemplate().delete(book);
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override/*��ѯ����ʵ����Ϣ*/
	public List<Book> findAll(Class<Book> entityClazz) {
		String queryString="from "+entityClazz.getSimpleName()+" book";
		return (List<Book>) getHibernateTemplate().find(queryString);
		
	}

	@Override/*��ѯʵ������*/
	public long findCount(Class<Book> entityClazz) {
		List<Long> list =getHibernateTemplate().execute(new HibernateCallback<List<Long>>() {
			@SuppressWarnings("unchecked")
			public List<Long> doInHibernate(Session session) throws HibernateException {
				//�����ӣ�û���ұ�ƴ��NULL
				String queryString="SELECT count(?) FROM Book :a";
				Query<Long> query = session.createQuery(queryString).setParameter("a","book").setParameter(0,"*");
				return (List<Long>) query;
			}
		});
		return list.get(0);
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override/*���ݹؼ��ʲ���*/
	public List<Book> findByKey(String key,Serializable vlue){
		String queryString="from Book b where "+key+" = '"+vlue+"'";
		return (List<Book>) getHibernateTemplate().find(queryString);
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override/*���ݹؼ��ʲ���*/
	public List<Book> queryBookName(String bookName){
		String queryString="from Book b where b.bookName like '%"+bookName+"%'";
		return (List<Book>) getHibernateTemplate().find(queryString);
	}
	
	@Override/*ԭ��hibernate����~HibernateCallback*/
	public Book doInHibernate(Session arg0) throws HibernateException {
		return null;
	}

}
