package cn.edu.abtc.iem.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.edu.abtc.iem.dao.UserDao;
import cn.edu.abtc.iem.domain.User;

//数据库接口（方法）Dao
public class UserDaoImpl extends HibernateDaoSupport implements UserDao<User>,HibernateCallback<User> {
	@Override/*使用原生态的hibernate方法*/
	public User doInHibernate(Session session) throws HibernateException {
		return null;
	}

	@Override
	public User get(Class<User> entityClazz, Serializable id) {
		// 根据ID加载实体
		return getHibernateTemplate().get(entityClazz, id);
	}

	@Override
	public Serializable save(User entity) {
		// 保存实体
		return getHibernateTemplate().save(entity);
	}

	@Override
	public void update(User entity) {
		// 更新实体
		getHibernateTemplate().saveOrUpdate(entity);
	}

	@Override
	public void delete(User entity) {
		// 删除实体
		getHibernateTemplate().delete(entity);
	}

	@Override
	public void delete(Class<User> entityClazz, Serializable id) {
		// 根据ID删除实体
		getHibernateTemplate().delete(get(entityClazz, id));
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override/*查询所有用户信息*/
	public List<User> findAll(Class<User> entityClazz) {
		// 获取类的所有实体
		String queryString="from "+entityClazz.getSimpleName()+"";//HQL语句的查询，面向对象，entityClazz.getSimpleName()获取对象User
//		System.out.println(queryString);
		return (List<User>) getHibernateTemplate().find(queryString);
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override/*获取用户信息总数*/
	public long findCount(Class<User> entityClazz) {
		// 获取实体总数
		String queryString="select count(*) from "+entityClazz.getSimpleName()+"";
		List<Long> list=(List<Long>)getHibernateTemplate().find(queryString);
		return list.get(0);
	}

	@Override/*用户校验*/
	public boolean check(String userName, String password) {
		String hql = "from User where user_name='"+userName+"' and user_password='"+password+"'";//准备hql语句
		Session se=getSessionFactory().getCurrentSession();//得到session对象
		@SuppressWarnings("rawtypes")
		Query query = se.createQuery(hql);//创建Query对象
		@SuppressWarnings("unchecked")
		List<User> list = query.list();//调用Query方法进行查询,得到userName与password的结果集（就两个）
//	    System.out.print("list大小"+list.size()+"null?"+list.isEmpty());
	    //没有查询到的结果集list为空，list的大小为0，即没有存入任何数据到结果集list
	    //list.isEmpty()=true，list.size()=0
	    if(list.isEmpty()&&list.size()==0)
	    	return false;
	    else 
	    	return true;
	}

	@Override/*根据用户名查询用户信息（模糊查询）*/
	public List<User> queryByName(String userName) {
//		String hql = "from User u where u.userName='"+userName+"'";//准备hql语句
		String hql = "from User u where u.userName like '%"+userName+"%'";//准备hql语句
		Session se=getSessionFactory().openSession();//得到session对象
		@SuppressWarnings("unchecked")
		Query<User> query = se.createQuery(hql);//创建Query对象
		List<User> list = query.list();//调用Query方法进行查询,得到userName与password的结果集（就两个）
		return list;
	}

	@Override/*根据用户名查询用户信息（准确查询）*/
	public List<User> findByName(String userName) {
		String hql = "from User u where u.userName='"+userName+"'";//准备hql语句
		Session se=getSessionFactory().openSession();//得到session对象
		@SuppressWarnings("unchecked")
		Query<User> query = se.createQuery(hql);//创建Query对象
		List<User> list = query.list();//调用Query方法进行查询,得到userName与password的结果集（就两个）
		return list;
	}
	
	@Override/*根据用户id查询用户信息*/
	public List<User> findById(Serializable id) {
		String hql = "from User u where u.id='"+id+"'";//准备hql语句
		Session se=getSessionFactory().openSession();//得到session对象
		@SuppressWarnings("unchecked")
		Query<User> query = se.createQuery(hql);//创建Query对象
		List<User> list = query.list();//调用Query方法进行查询,得到userName与password的结果集（就两个）
		return list;
	}
}
