package cn.edu.abtc.iem.service;

import java.io.Serializable;
import java.util.List;

public interface AdminService<T> extends BaseService<T> {
	//��¼�û�У��
	public boolean check(String userName,String password);
	//��ѯ��������
	public List<T> findAll(Class<T> entityClazz);
	//�޸�����
	public void update(T entity,Serializable id,String adminName,String password);
	//ģ����ѯ
	public List<T> qurey(String adminName);
	//׼ȷ��ѯ
	public List<T> findByName(String adminName);
	//�ж��Ƿ���ڸù���Ա�û���
	public boolean isExist(String adminName);
}
