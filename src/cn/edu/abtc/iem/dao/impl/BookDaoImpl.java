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
	@Override/*根据id得到实体*/
	public Book get(Class<Book> entityClazz, Serializable id) {
		return getHibernateTemplate().get(entityClazz, id);
	}

	@Override/*实例化实体*/
	public Serializable save(Book entity) {
		return getHibernateTemplate().save(entity);
	}
	
	@Override/*实例化实体属性*/
	public void insert(Book book) {
		String sql="INSERT INTO ssh_book(book_name,book_price,book_author) VALUES('"+book.getBookName()+"','"+book.getBookPrice()+"','"+book.getAuthor()+"')";
		getSessionFactory().getCurrentSession().createSQLQuery(sql).executeUpdate();
	}
	
	@Override/*修改实体*/
	public void update(Book entity) {
		getHibernateTemplate().update(entity);
	}

	@Override/*删除实体*/
	public void delete(Book entity) {
		getHibernateTemplate().delete(entity);
	}

	@Override/*根据id删除实体*/
	public void delete(Class<Book> entityClazz, Serializable id) {
		Book book = this.get(entityClazz, id);
		getHibernateTemplate().delete(book);
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override/*查询所有实体信息*/
	public List<Book> findAll(Class<Book> entityClazz) {
		String queryString="from "+entityClazz.getSimpleName()+" book";
		return (List<Book>) getHibernateTemplate().find(queryString);
		
	}

	@Override/*查询实体总数*/
	public long findCount(Class<Book> entityClazz) {
		List<Long> list =getHibernateTemplate().execute(new HibernateCallback<List<Long>>() {
			@SuppressWarnings("unchecked")
			public List<Long> doInHibernate(Session session) throws HibernateException {
				//左连接，没有右边拼接NULL
				String queryString="SELECT count(?) FROM Book :a";
				Query<Long> query = session.createQuery(queryString).setParameter("a","book").setParameter(0,"*");
				return (List<Long>) query;
			}
		});
		return list.get(0);
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override/*根据关键词查找*/
	public List<Book> findByKey(String key,Serializable vlue){
		String queryString="from Book b where "+key+" = '"+vlue+"'";
		return (List<Book>) getHibernateTemplate().find(queryString);
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override/*根据关键词查找*/
	public List<Book> queryBookName(String bookName){
		String queryString="from Book b where b.bookName like '%"+bookName+"%'";
		return (List<Book>) getHibernateTemplate().find(queryString);
	}
	
	@Override/*原生hibernate操作~HibernateCallback*/
	public Book doInHibernate(Session arg0) throws HibernateException {
		return null;
	}

}
