package cn.edu.abtc.iem.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.edu.abtc.iem.dao.UserDao;
import cn.edu.abtc.iem.domain.User;
import cn.edu.abtc.iem.service.UserService;
//业务逻辑方法service
public class UserServiceImpl implements UserService<User>{
	private UserDao<User> userDao;//用到是业务组件
	//为业务组件设置setter、getter方法
	public UserDao<User> getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao<User> userDao) {
		this.userDao = userDao;
	}

	@Override/*查询所有实体User对象*/
	@Transactional//用注解启用事务,注解方式配置事务
	public List<User> findAll(Class<User> entityClazz) {
		return userDao.findAll(entityClazz);
	}

	@Override/*插入实体*/
	@Transactional
	public void insert(User entity) {
		userDao.save(entity);
	}

	@Override/*根据id删除实体*/
	@Transactional
	public void delete(Serializable id) {
		userDao.delete(User.class, id);
	}
	
	@Override/*修改实体属性*/
	@Transactional
	public void update(User user, Serializable id, String userName, String password) {
		user = userDao.get(User.class, id);//获取到指定id的user
		//确定使用新旧名字密码，传过来的参数是已经确定的名字
		user.setUserName(userName);//对指定id的user进行属性赋值
		user.setPassword(password);
		userDao.update(user);//重新更新对应user
	}

	@Override/*根据用户名查询用户（模糊查询）*/
	@Transactional
	public List<User> qurey(String userName) {
		return userDao.queryByName(userName);
	}
	
	@Override/*根据用户名查询用户（准确查询）*/
	@Transactional
	public List<User> findByName(String userName) {
		return userDao.findByName(userName);
	}
	
	@Override
	@Transactional/*判断是否存在该用户名*/
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
	@Transactional/*判断是否存在该用户名*/
	public User findUserById(Serializable id)
	{
		List<User> list = userDao.findById(id);
		User user = list.get(0);
		return user;
	}
	
	@Override
	@Transactional/*判断是否存在该用户ID*/
	public boolean isExist(Serializable id)
	{
		if(userDao.get(User.class, id)!=null)//空对象不能调用方法，不能用.equals判断null
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
