package cn.edu.abtc.iem.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.edu.abtc.iem.dao.AdminDao;
import cn.edu.abtc.iem.domain.Admin;
import cn.edu.abtc.iem.service.AdminService;
//ҵ���߼�����service
public class AdminServiceImpl implements AdminService<Admin>{
	private AdminDao<Admin> adminDao;
	
	public AdminDao<Admin> getAdminDao() {
		return adminDao;
	}

	public void setAdminDao(AdminDao<Admin> adminDao) {
		this.adminDao = adminDao;
	}

	@Override
	public boolean check(String adminName, String password) {
		List<Admin> list = adminDao.check(adminName, password);
		if(list.size()>0)
		{
			return true;
		}
		else
		{
			return false;
		}

	}

	@Override
	public List<Admin> findAll(Class<Admin> entityClazz) {
		return adminDao.findAll(entityClazz);
	}

	@Override
	@Transactional
	public void insert(Admin entity) {
		adminDao.save(entity);
	}

	@Override
	@Transactional
	public void delete(Serializable id) {
		adminDao.delete(Admin.class, id);
	}

	@Override
	@Transactional
	public void update(Admin admin, Serializable id, String adminName, String adminPassword) {
		admin = adminDao.get(Admin.class, id);//ͨ��idȷ��Adminʵ��
		//ȷ��ʹ���¾��������룬�������Ĳ������Ѿ�ȷ��������
		admin.setAdminName(adminName);
		admin.setAdminPassword(adminPassword);
		adminDao.update(admin);
	}

	@Override//ģ����ѯ
	public List<Admin> qurey(String adminName) {
		return adminDao.queryByName(adminName);
	}

	@Override//��ȷ��ѯ
	public List<Admin> findByName(String adminName) {
		return adminDao.findByName(adminName);
	}

	@Override
	public boolean isExist(String adminName) {
		if(findByName(adminName).size()>0)
		{
			return true;
		}
		else
			return false;
	}
	
	
}
