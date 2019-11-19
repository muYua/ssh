package cn.edu.abtc.iem.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.edu.abtc.iem.dao.UserDao;
import cn.edu.abtc.iem.domain.User;
import cn.edu.abtc.iem.service.UserService;
//ҵ���߼�����service
public class UserServiceImpl implements UserService<User>{
	private UserDao<User> userDao;//�õ���ҵ�����
	//Ϊҵ���������setter��getter����
	public UserDao<User> getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao<User> userDao) {
		this.userDao = userDao;
	}

	@Override/*��ѯ����ʵ��User����*/
	@Transactional//��ע����������,ע�ⷽʽ��������
	public List<User> findAll(Class<User> entityClazz) {
		return userDao.findAll(entityClazz);
	}

	@Override/*����ʵ��*/
	@Transactional
	public void insert(User entity) {
		userDao.save(entity);
	}

	@Override/*����idɾ��ʵ��*/
	@Transactional
	public void delete(Serializable id) {
		userDao.delete(User.class, id);
	}
	
	@Override/*�޸�ʵ������*/
	@Transactional
	public void update(User user, Serializable id, String userName, String password) {
		user = userDao.get(User.class, id);//��ȡ��ָ��id��user
		//ȷ��ʹ���¾��������룬�������Ĳ������Ѿ�ȷ��������
		user.setUserName(userName);//��ָ��id��user�������Ը�ֵ
		user.setPassword(password);
		userDao.update(user);//���¸��¶�Ӧuser
	}

	@Override/*�����û�����ѯ�û���ģ����ѯ��*/
	@Transactional
	public List<User> qurey(String userName) {
		return userDao.queryByName(userName);
	}
	
	@Override/*�����û�����ѯ�û���׼ȷ��ѯ��*/
	@Transactional
	public List<User> findByName(String userName) {
		return userDao.findByName(userName);
	}
	
	@Override
	@Transactional/*�ж��Ƿ���ڸ��û���*/
	public boolean isExist(String userName)
	{
		if(findByName(userName).size()>0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	@Override
	@Transactional/*�ж��Ƿ���ڸ��û���*/
	public User findUserById(Serializable id)
	{
		List<User> list = userDao.findById(id);
		User user = list.get(0);
		return user;
	}
	
	@Override
	@Transactional/*�ж��Ƿ���ڸ��û�ID*/
	public boolean isExist(Serializable id)
	{
		if(userDao.get(User.class, id)!=null)//�ն����ܵ��÷�����������.equals�ж�null
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
