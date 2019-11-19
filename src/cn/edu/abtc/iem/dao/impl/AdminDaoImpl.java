package cn.edu.abtc.iem.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import cn.edu.abtc.iem.dao.AdminDao;
import cn.edu.abtc.iem.domain.Admin;

public class AdminDaoImpl extends HibernateDaoSupport implements AdminDao<Admin> {

	@Override
	public Admin get(Class<Admin> entityClazz, Serializable id) {
		return getHibernateTemplate().get(entityClazz, id);
	}

	@Override
	public Serializable save(Admin entity) {
		return getHibernateTemplate().save(entity);
	}

	@Override
	public void update(Admin entity) {
		getHibernateTemplate().update(entity);
	}

	@Override
	public void delete(Admin entity) {
		getHibernateTemplate().delete(entity);
	}

	@Override
	public void delete(Class<Admin> entityClazz, Serializable id) {
		getHibernateTemplate().delete(this.get(entityClazz,id));
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<Admin> findAll(Class<Admin> entityClazz) {
		String queryString="from "+entityClazz.getSimpleName()+"";
		return (List<Admin>) getHibernateTemplate().find(queryString);
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public long findCount(Class<Admin> entityClazz) {
		String queryString="select count(*) from "+entityClazz.getSimpleName()+"";
		List<Long> list=(List<Long>)getHibernateTemplate().find(queryString);
		return list.get(0);
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Admin> check(String adminName, String adminPassword) {
		String hql = "from Admin a where a.adminName ='"+adminName+"' and a.adminPassword='"+adminPassword+"'";//准备hql语句
		List<Admin> list=(List<Admin>) getHibernateTemplate().find(hql);
		return list;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Admin> queryByName(String adminName) {
		String hql = "from Admin a where a.adminName like '%"+adminName+"%'";//准备hql语句
		List<Admin> list=(List<Admin>) getHibernateTemplate().find(hql);
		return list;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Admin> findByName(String adminName) {
		String hql = "from Admin a where a.adminName='"+adminName+"'";//准备hql语句
		List<Admin> list=(List<Admin>) getHibernateTemplate().find(hql);
		return list;
	}
	
}
