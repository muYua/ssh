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

//���ݿ�ӿڣ�������Dao
public class UserDaoImpl extends HibernateDaoSupport implements UserDao<User>,HibernateCallback<User> {
	@Override/*ʹ��ԭ��̬��hibernate����*/
	public User doInHibernate(Session session) throws HibernateException {
		return null;
	}

	@Override
	public User get(Class<User> entityClazz, Serializable id) {
		// ����ID����ʵ��
		return getHibernateTemplate().get(entityClazz, id);
	}

	@Override
	public Serializable save(User entity) {
		// ����ʵ��
		return getHibernateTemplate().save(entity);
	}

	@Override
	public void update(User entity) {
		// ����ʵ��
		getHibernateTemplate().saveOrUpdate(entity);
	}

	@Override
	public void delete(User entity) {
		// ɾ��ʵ��
		getHibernateTemplate().delete(entity);
	}

	@Override
	public void delete(Class<User> entityClazz, Serializable id) {
		// ����IDɾ��ʵ��
		getHibernateTemplate().delete(get(entityClazz, id));
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override/*��ѯ�����û���Ϣ*/
	public List<User> findAll(Class<User> entityClazz) {
		// ��ȡ�������ʵ��
		String queryString="from "+entityClazz.getSimpleName()+"";//HQL���Ĳ�ѯ���������entityClazz.getSimpleName()��ȡ����User
//		System.out.println(queryString);
		return (List<User>) getHibernateTemplate().find(queryString);
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override/*��ȡ�û���Ϣ����*/
	public long findCount(Class<User> entityClazz) {
		// ��ȡʵ������
		String queryString="select count(*) from "+entityClazz.getSimpleName()+"";
		List<Long> list=(List<Long>)getHibernateTemplate().find(queryString);
		return list.get(0);
	}

	@Override/*�û�У��*/
	public boolean check(String userName, String password) {
		String hql = "from User where user_name='"+userName+"' and user_password='"+password+"'";//׼��hql���
		Session se=getSessionFactory().getCurrentSession();//�õ�session����
		@SuppressWarnings("rawtypes")
		Query query = se.createQuery(hql);//����Query����
		@SuppressWarnings("unchecked")
		List<User> list = query.list();//����Query�������в�ѯ,�õ�userName��password�Ľ��������������
//	    System.out.print("list��С"+list.size()+"null?"+list.isEmpty());
	    //û�в�ѯ���Ľ����listΪ�գ�list�Ĵ�СΪ0����û�д����κ����ݵ������list
	    //list.isEmpty()=true��list.size()=0
	    if(list.isEmpty()&&list.size()==0)
	    	return false;
	    else 
	    	return true;
	}

	@Override/*�����û�����ѯ�û���Ϣ��ģ����ѯ��*/
	public List<User> queryByName(String userName) {
//		String hql = "from User u where u.userName='"+userName+"'";//׼��hql���
		String hql = "from User u where u.userName like '%"+userName+"%'";//׼��hql���
		Session se=getSessionFactory().openSession();//�õ�session����
		@SuppressWarnings("unchecked")
		Query<User> query = se.createQuery(hql);//����Query����
		List<User> list = query.list();//����Query�������в�ѯ,�õ�userName��password�Ľ��������������
		return list;
	}

	@Override/*�����û�����ѯ�û���Ϣ��׼ȷ��ѯ��*/
	public List<User> findByName(String userName) {
		String hql = "from User u where u.userName='"+userName+"'";//׼��hql���
		Session se=getSessionFactory().openSession();//�õ�session����
		@SuppressWarnings("unchecked")
		Query<User> query = se.createQuery(hql);//����Query����
		List<User> list = query.list();//����Query�������в�ѯ,�õ�userName��password�Ľ��������������
		return list;
	}
	
	@Override/*�����û�id��ѯ�û���Ϣ*/
	public List<User> findById(Serializable id) {
		String hql = "from User u where u.id='"+id+"'";//׼��hql���
		Session se=getSessionFactory().openSession();//�õ�session����
		@SuppressWarnings("unchecked")
		Query<User> query = se.createQuery(hql);//����Query����
		List<User> list = query.list();//����Query�������в�ѯ,�õ�userName��password�Ľ��������������
		return list;
	}
}
